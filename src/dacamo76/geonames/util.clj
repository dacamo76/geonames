(ns dacamo76.geonames.util
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [clojure.string :as string]))

(defn split-tabs
  [s]
  (string/split s #"\t"))

(defn filter-vals
  [pred m]
  (filter (comp pred second) m))

(defn dot-join
  [& s]
  (string/join "." s))

(defn slurp-tsv
  [x]
  (->> x 
       slurp
       string/split-lines
       (map string/trim)
       (map split-tabs)))

(defn comment?
  [s]
  (.startsWith s "#"))

(defn key-by
  [f coll]
  (into {} (map #(vector (f %) %) coll)))

(defn read-tsv
  [reader]
;  (csv/read-csv input :separator \tab))
  (let [lines (line-seq reader)]
    (map split-tabs lines)))

(defn write-csv
  ([s]
     (write-csv *out* s))
  ([writer s]
     (csv/write-csv writer s)))
