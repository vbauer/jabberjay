(ns weather
  (:require [clojure.string :as string]
            [clojure.core.memoize :as memoize]
            [clj-http.client :as client]))


; Constants

(def ^:private YQL_QUERY
  (str
   "select * from weather.forecast where woeid in "
   "(select woeid from geo.places(1) "
   "where text=\"%s\") and u='c'"))

(def ^:private API_URL
  "https://query.yahooapis.com/v1/public/yql")


; Internal API

(defn- send-get [url q]
  (let [p {:as :json
           :insecure? true
           :follow-redirects true
           :max-redirects 10
           :socket-timeout 30000
           :conn-timeout 30000
           :query-params q}
        res (client/get url p)]
    (:body res)))

(defn- fetch-weather [location]
  (let [query (format YQL_QUERY (string/trim location))]
    (send-get API_URL {:q query :format "json"})))

(defn- report [data]
  (string/join
   "\n"
   (map
    #(string/join
      ", "
      [(str "Date: " (:date %))
       (str "High: " (:high %))
       (str "Low: " (:low %))])
    data)))

(defn error [msg]
  (str msg "\n Do not forget to specify city name, ex: \"weather Novosibirsk\""))

; External API

(defn init [location]
  (try
    (let [json (fetch-weather location)
          data (get-in json [:query :results :channel :item :forecast])]
      (report data))
    (catch Exception e
      (error (.getMessage e)))))
