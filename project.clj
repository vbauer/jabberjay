(defproject jabberjay "0.1.0-SNAPSHOT"
  :description "JabberJay Bot"
  :url "https://github.com/vbauer/jabberjay"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.memoize "0.5.6"]
                 [xmpp-clj "0.3.1"]
                 [uochan/watchtower "0.1.4"]
                 [me.raynes/fs "1.4.4"]
                 [clj-http "1.0.1"]
                 [sonian/carica "1.1.0" :exclusions [cheshire]]]

  :min-lein-version "2.0.0"

  :main jabberjay.app
  :aot [jabberjay.app])
