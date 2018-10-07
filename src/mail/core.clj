(ns mail.core
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message]
            [cail.core :as cail]
            [clojure.java.io :as io]
            )
  (:import [javax.mail.internet MimeUtility] )
  (:gen-class))


(def qq-store (clojure-mail.core/store "imap.qq.com" "li_zhaoyu@qq.com" "xxsjjeacdnfrcahf"))

(def my-inbox-messages (take 5 (all-messages qq-store "其他文件夹/发票")))

(def first-message (first my-inbox-messages))

(message/subject first-message)

(defn pdf-map [msg-map]
  (let [content-type (clojure.string/lower-case (:content-type msg-map))
        mail-part-array (:body msg-map)
        content-type-mixed "multipart/mixed"
        content-type-pdf "application/pdf"
        subject (:subject msg-map)
        ]
    (when (clojure.string/starts-with? content-type content-type-mixed)
      (prn subject)
      (for [part mail-part-array]
        (let [part-body (:body part)
              part-content-type (:content-type part)]
          (if (and
               (not (nil? part-body))
               (clojure.string/starts-with?
                (clojure.string/lower-case part-content-type)
                content-type-pdf))
            (io/copy part-body
                     (io/file (str (java.util.UUID/randomUUID)
                                   ".pdf")))
            ))

        )
      ))
  )

(map pdf-map
     (filter #(= (:multipart? %) true)
             (map #(message/read-message % :fields [:body :multipart? :content-type :subject])
                  (all-messages qq-store "其他文件夹/发票")
                  )))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
