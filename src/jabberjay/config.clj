(ns jabberjay.config
  (:require [carica.core :as carica]
            [me.raynes.fs :as fs]))


; Internal API

(defn- read-config []
  (let [user-config (fs/file (.getAbsolutePath (fs/home)) "jabberjay.edn")
        def-config (carica/resources"config.edn")
        configs (if (fs/exists? user-config)
                  [user-config def-config]
                  [def-config])]
    (carica/get-configs configs)))


; External API

(def config (read-config))
