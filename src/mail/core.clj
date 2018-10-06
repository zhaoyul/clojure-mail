(ns mail.core
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn qq-store [imap-url user-name password]
  {:test }
  (clojure-mail.core/store imap-url user-name password))

(def qq-store (clojure-mail.core/store "imap.qq.com" "li_zhaoyu@qq.com" "xxsjjeacdnfrcahf"))

(def my-inbox-messages (take 5 (all-messages qq-store "inbox")))

(def first-message (first my-inbox-messages))

(message/subject first-message)
