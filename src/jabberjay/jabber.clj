(ns jabberjay.jabber
  (:require [xmpp-clj :as xmpp]
            [jabberjay.script :as script]
            [clojure.string :as string]
            [carica.core :as carica]))


; Internal API

(defn- def-message []
  (str
   "Type the name of the specific module, ex: \"hi\"\n"
   "Available modules: " (string/join ", " (script/modules)) "\n"))


(defn- process-message [msg]
  (try
    (when-let [text (:body msg)]
      (or
       (if-not (string/blank? text)
         (do
           (println "Received message: " text)
           (script/execute (string/trim text))))
       (def-message)))
    (catch Exception e
      (.printStackTrace e)
      (def-message))))


; External API

(defn init []
  (let [conf (carica/config :jabber)]
    (xmpp/start-bot
     :username (:username conf)
     :password (:password conf)
     :host (:host conf)
     :domain (:domain conf)
     :handler process-message)))
