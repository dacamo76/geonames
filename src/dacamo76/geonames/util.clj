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

(defn comment?
  [s]
  (.startsWith s "#"))

(defn key-by
  [f coll]
  (into {} (map #(vector (f %) %) coll)))

(defn- input-stream->zip-input-stream
  [x]
  (let [zis (java.util.zip.ZipInputStream. x)
        _ (.getNextEntry zis)]
    zis))

(defn zip-reader
  [x]
  (let [is (io/input-stream x)
        zis (input-stream->zip-input-stream is)
        reader (io/reader zis)]
    reader))

(defn slurp-tsv
  [x]
  (->> x
       slurp
       string/split-lines
       (map string/trim)
       (map split-tabs)))

(defn read-tsv
  [reader]
  (let [lines (line-seq reader)]
    (map split-tabs lines)))

(defn write-csv
  ([s]
     (write-csv *out* s))
  ([writer s]
     (csv/write-csv writer s)))
