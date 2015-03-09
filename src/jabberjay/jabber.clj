(ns jabberjay.jabber
  (:require [xmpp-clj :as xmpp]
            [jabberjay.script :as script]
            [jabberjay.config :as config]
            [clojure.string :as string]
            [taoensso.timbre :as timbre]))


; Internal API

(defn- def-message []
  (str
   "Type the name of the specific module, ex: \"hi\"\n"
   "Available modules: " (string/join ", " (script/modules))))


(defn- process-message [msg]
  (or
    (try
      (when-let [text (string/trim (:body msg))]
        (if-not (string/blank? text)
          (do
            (timbre/debug "Received message:" msg)
            (script/execute msg))))
      (catch Exception e
        (timbre/error e)
        (.getMessage e)))
   (def-message)))


; External API

(defn init []
  (let [conf (config/config :jabber)]
    (xmpp/start-bot
     :username (:username conf)
     :password (:password conf)
     :host (:host conf)
     :domain (:domain conf)
     :handler process-message)))
