(ns hi)

(defn init
  "Simple module for greating"
  [data]
  (str "Hello, " (or (:from-name data)
                     (:from data))))
