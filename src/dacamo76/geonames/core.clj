(ns dacamo76.geonames.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [dacamo76.geonames.util :as util]
            [dacamo76.geonames.country :as country]
            [dacamo76.geonames.admin :as admin]
            [dacamo76.geonames.city :as city])
  (:gen-class))


(defn admin-code-from-city
  [s]
  (admin/code
   (city/country-code s) (city/admin1-code s)))

(defn admin-name
  [codes s]
  (-> s
      admin-code-from-city
      codes
      admin/admin1-name))

(defn country-name
  [codes s]
  (-> s
      city/country-code
      codes
      country/country-name))

(defn city-fields
  [country-fn admin-fn s]
  (let [name (city/city-name s)
        lat (city/lat s)
        lon (city/lon s)
        country (country-fn s)
        state (admin-fn s)]
    [name, state, country, lat, lon]))

(defn -main
  [& args]
  (let [admin-codes (util/key-by :fips (admin/admin-codes admin/admin-codes-url))
        country-codes (util/key-by :iso (country/country-codes country/country-codes-url))
        admin-fn (partial admin-name admin-codes)
        country-fn (partial country-name country-codes)]
    (util/write-csv (map (partial city-fields country-fn admin-fn)
                         (city/cities city/cities-url)))))
