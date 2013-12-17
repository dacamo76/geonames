(ns dacamo76.geonames.admin
  (:require [clojure.string :as string]
            [dacamo76.geonames.util :as util]))

(def admin-codes-url
  "http://download.geonames.org/export/dump/admin1CodesASCII.txt")

(def header
  [:fips :name :asciiname :geonameid])

(defn remove-empty-vals
  [m]
  (into {} (util/filter-vals (complement string/blank?) m)))

(defn admin-codes
  [x]
  (->> x
       util/slurp-tsv
       (map (partial zipmap header))
       (map remove-empty-vals)))

(defn code
  [country-code admin1-code]
  (util/dot-join country-code admin1-code))

(defn admin1-name
  [s]
  (if-let [name (:name s)]
    name
    (:asciiname s)))
