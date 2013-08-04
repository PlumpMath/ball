(ns protosite.core
  (:require [ring.adapter.jetty :refer :all])
  (:require [compojure.route :as route])
  (:require [compojure.core :refer :all])
  (:require [protosite.models :refer :all]))


(defroutes app
  (GET "/" [] "<h1>Welcome to Fantasy Baseball!</h1>")
  (GET ["/team/:team/:year" :team #"\w{3}" :year #"\d{4}"] [team year]
         (ready-for-data-tables (c-team-record team (Integer/parseInt year))))
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))


;(run-jetty handler {:port 3000})

(def port 5000)

(defn -main []
  (println "Server Started")
  (run-jetty app {:port port}))
