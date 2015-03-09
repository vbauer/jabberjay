(ns jabberjay.script
  (:require [jabberjay.config :as config]
            [clojure.string :as string]
            [watchtower.core :as wt]
            [me.raynes.fs :as fs]
            [taoensso.timbre :as timbre]))


; Constants & options

(def ^:private SCRIPTS (atom nil))

(defn- conf-scripts [k] ((get config/config :scripts) k))
(defn- conf-delay [] (conf-scripts :watchdog))
(defn- conf-folder [] (fs/file (conf-scripts :folder)))


; Internal API: Script engine

(defn- scan-scripts []
  (let [dir (conf-folder)
        files (fs/list-dir dir)]
    (filter #(fs/file? %) files)))

(defn- reload-script [file]
  (load-file (.getAbsolutePath file)))

(defn- reload-scripts []
  (let [files (scan-scripts)
        names (map fs/name files)
        scripts (map reload-script files)
        res (zipmap names scripts)]
    (reset! SCRIPTS res)
    (timbre/info "Modules reloaded:" names)
    res))


; Internal API: Watchdog

(defn- fs-watcher [_] (reload-scripts))

(defn- init-fs-watcher []
  (let [f (conf-folder)
        d (conf-delay)]
    (if (and
         (not (nil? f))
         (not (nil? d))
         (> d 0))
      (wt/watcher
       [f]
       (wt/rate d)
       (wt/file-filter (wt/extensions :clj))
       (wt/on-change fs-watcher)))))


; External API

(defn modules []
  (keys @SCRIPTS))

(defn execute [data]
  (let [text (string/trim (:body data))
        args (string/split text #"\s")
        cmd (string/lower-case (first args))
        argv (string/trim (subs text (count cmd)))
        script (get @SCRIPTS cmd)
        context (assoc data :text argv)]
    (if-not (nil? script)
      (script context))))

(defn init [wd]
  (reload-scripts)
  (if wd (init-fs-watcher)))

;(init true)
;(reload-scripts)
;(execute {:body "Hi Bot!" :from-name "Bob"})
