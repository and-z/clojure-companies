(defproject clojure-companies :lein-v
  :description "Companies using Clojure/ClojureScript"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :release-tasks [["vcs" "assert-committed"]
                  ["v" "update"]
                  #_["vcs" "push"]
                  #_["deploy"]]
  :v {:sign "--no-sign"}
  :plugins [[com.roomkey/lein-v "6.4.0"]
            [io.aviso/pretty "0.1.35"]]
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [io.aviso/pretty "0.1.35"]]
  :main ^:skip-aot clojure-companies.core
  :profiles {:uberjar {:aot :all}})
