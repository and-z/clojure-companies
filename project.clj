(defproject clojure-companies :lein-v
  :description "Companies using Clojure/ClojureScript"
  :url "http://example.com/FIXME"
  :license
  {:name "Eclipse Public License"
   :url "http://www.eclipse.org/legal/epl-v10.html"}

  :main ^:skip-aot clojure-companies.core

  :v {:sign "--no-sign"}

  :release-tasks
  [["vcs" "assert-committed"]
   ["v" "update"]
   #_["vcs" "push"]
   #_["deploy"]]

  :plugins [[com.roomkey/lein-v "6.4.0"]
            [io.aviso/pretty "0.1.36"]]

  :dependencies
  [[aleph "0.4.6"]
   [byte-streams "0.2.4"]
   [hickory "0.7.1"]
   [org.clojure/clojure "1.10.0"]
   [io.aviso/pretty "0.1.36"]]

  :middleware
  [lein-v.plugin/middleware
   pretty.plugin/middleware]

  :profiles
  {:uberjar {:aot :all},
   :dev
   {:source-paths ["dev-src"],
    :repl-options {:init-ns user},
    :middleware
    [cider-nrepl.plugin/middleware
     refactor-nrepl.plugin/middleware],
    :dependencies
    [[nrepl "0.5.3"]],
    :plugins
    [[refactor-nrepl "2.4.0"]
     [cider/cider-nrepl "0.20.0-SNAPSHOT"]]}})
