(defproject protosite "0.1.0-SNAPSHOT"
  :description "Fantasy Baseball Prototype Site"
  :url nil
  :aot [protosite.example protosite.lahman-load protosite.lahman-delete]
  :main protosite.core
  :profiles {:dev {:dependencies [[midje "1.5.0"]
                                  [ring-mock "0.1.5"]]}}
  :license {:name "Not open source"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.csv "0.1.2"]
                 [org.clojure/data.json "0.2.2"]
                 [compojure "1.1.5"]
                 [ring "1.2.0"]
                 [com.datomic/datomic-free "0.8.4020.24"]
                 [com.cemerick/friend "0.1.5"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler protosite.core/app})
