(ns hi)

(defn init
  "Simple module for greating"
  [data]
  (let [user (:from-name data)]
    (str "Hello, " user)))
