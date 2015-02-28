(ns jabberjay.script
  (:require [jabberjay.config :as config]
            [clojure.string :as string]
            [watchtower.core :as wt]
            [me.raynes.fs :as fs]))


; Constants & options

(def ^:private SCRIPTS (atom nil))

(defn- conf-wd [k] ((get config/config :watchdog) k))
(defn- conf-delay [] (conf-wd :delay))
(defn- conf-folder [] (fs/file (conf-wd :folder)))


; Internal API

(defn- scan-scripts []
  (let [dir (conf-folder)
        fnames (fs/list-dir dir)
        files (map #(fs/file dir %) fnames)]
    (filter #(fs/file? %) files)))

(defn- reload-script [file]
  (load-file (.getAbsolutePath file)))

(defn- reload-scripts []
  (let [files (scan-scripts)
        names (map fs/name files)
        scripts (map reload-script files)
        res (zipmap names scripts)]
    (reset! SCRIPTS res)
    (println "Modules reloaded: " names)
    res))

(defn- fs-watcher [_] (reload-scripts))


; External API

(defn modules []
  (keys @SCRIPTS))

(defn execute [text]
  (let [args (string/split text #"\s")
        cmd (string/lower-case (first args))
        argv (string/trim (subs text (count cmd)))
        script (get @SCRIPTS cmd)]
    (if-not (nil? script)
      (script argv))))

(defn init []
  (reload-scripts)
  (wt/watcher
   [(conf-folder)]
   (wt/rate (conf-delay))
   (wt/file-filter (wt/extensions :clj))
   (wt/on-change fs-watcher)))

;(init)
;(reload-scripts)
;(execute "hi")
