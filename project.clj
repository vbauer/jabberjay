(defproject jabberjay "0.1.1"
  :description "Jabberjay - simple framework for creating Jabber bots"
  :url "https://github.com/vbauer/jabberjay"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.memoize "0.5.7"]
                 [uochan/watchtower "0.1.4"]
                 [me.raynes/fs "1.4.6"]
                 [xmpp-clj "0.3.1"]
                 [jivesoftware/smackx "3.1.0"]
                 [clj-http "1.0.1"]
                 [com.taoensso/timbre "3.4.0"]
                 [sonian/carica "1.1.0" :exclusions [cheshire]]]

  :plugins [[jonase/eastwood "0.2.1" :exclusions [org.clojure/clojure]]
            [lein-kibit "0.0.8" :exclusions [org.clojure/clojure]]
            [lein-bikeshed "0.2.0" :exclusions [org.clojure/clojure]]
            [lein-ancient "0.6.2"]]

  :min-lein-version "2.0.0"
  :global-vars {*warn-on-reflection* false}
  :uberjar-name "jabberjay.jar"
  :main jabberjay.app
  :aot [jabberjay.app])
