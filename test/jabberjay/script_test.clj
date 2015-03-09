(ns jabberjay.script-test
  (:require [clojure.test :refer :all]
            [jabberjay.script :as script]))

(deftest script-execute
  (testing "script: hi"
    (script/init false)
    (is (= "Hello, Bob" (script/execute {:body "hi" :from-name "Bob"})))
    (is (not (empty? (script/execute {:body "weather Novosibirsk"}))))))
