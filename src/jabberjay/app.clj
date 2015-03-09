(ns jabberjay.app
  (:require [jabberjay.jabber :as jabber]
            [jabberjay.script :as script])
  (:gen-class))


(defn -main [& args]
  (script/init true)
  (jabber/init))

;(-main)
