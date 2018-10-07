(defproject mail "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.forward/clojure-mail "1.0.7"]
                 [rodnaph/cail "0.8.8"]]
  :main ^:skip-aot mail.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
