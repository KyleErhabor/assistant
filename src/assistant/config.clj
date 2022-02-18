(ns assistant.config
  (:require [clojure.edn :as edn]))

(def purge-timeout
  "The number of milliseconds to wait before deleting the purge success message."
  2000)

(defn read-config
  [files]
  (apply merge-with into (map (comp edn/read-string slurp) files)))
