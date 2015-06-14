(defproject jabberjay "0.2.0"
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
                 [clj-http "1.1.2"]
                 [com.taoensso/timbre "4.0.1"]
                 [sonian/carica "1.1.0" :exclusions [cheshire]]]

  :min-lein-version "2.0.0"
  :global-vars {*warn-on-reflection* false}
  :uberjar-name "jabberjay.jar"
  :main jabberjay.app
  :aot [jabberjay.app])
