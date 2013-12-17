(ns dacamo76.geonames.country
  (:require [clojure.java.io :as io]
            [dacamo76.geonames.util :as util]))


(def country-codes-url
  "http://download.geonames.org/export/dump/countryInfo.txt")

(def header
  [:iso :iso3 :iso-numeric :fips :country :capital :area :population :continent :tld :currency-code :currency-name :phone :postal-code-format :postal-code-regex :languages :geonameid :neighbours :equivalent-fips-code])

(defn- comment?
  [coll]
  ((comp util/comment? first) coll))

(defn- skip-comments
  [coll]
  (drop-while comment? coll))

(defn- headerf
  [x]
  (let [comments (take-while comment? x)]
    (map #(keyword (clojure.string/replace % #"\s+" "-")) (last comments))))

(defn country-codes
  [x]
  (->> x
       util/slurp-tsv
       skip-comments
       (map (partial zipmap header))))

(defn country-name
  [s]
  (:country s))
