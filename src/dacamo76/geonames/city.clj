(ns dacamo76.geonames.city
  (:require [clojure.java.io :as io]
            [dacamo76.geonames.util :as util]))

(def cities-url
  "http://download.geonames.org/export/dump/cities1000.zip")

(defn cities
  [x]
  (util/read-tsv (util/zip-reader x)))

(defn admin1-code
  [s]
  (nth s 10))

(defn country-code
  [s]
  (nth s 8))

(defn lat
  [s]
  (nth s 4))

(defn lon
  [s]
  (nth s 5))

(defn city-name
  [s]
  (second s))
