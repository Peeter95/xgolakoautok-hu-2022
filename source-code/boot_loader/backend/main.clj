
(ns boot-loader.backend.main
    (:require [pattern.api       :as p]
              [shadow-cljs.api   :as shadow-cljs]
              [x.boot-loader.api :as x.boot-loader]
              [x.core.api        :as x.core])
    (:gen-class))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-server!
  ; @param (map) server-props
  ;  {:port (integer or string)(opt)}
  [{:keys [port] :as server-props}]
  (println "boot-loader - Starting server on port:" (or port "@default"))
  (x.boot-loader/start-server! server-props))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn -main
  ; @param (list of *) args
  ;  [(integer)(opt) port]
  ;
  ; @usage
  ;  (-main 3000)
  ;
  ; @usage
  ;  java -jar {{namespace}}.jar 3000
  [& [port :as args]]
  (p/ignore!) ; <- Turning off the pattern.api validator in production release
  (start-server! {:port port}))

(defn dev
  ; @param (map) options
  ;  {:port (integer)
  ;   :shadow-build (keyword)}
  ;
  ; @usage
  ;  (dev {:shadow-build :my-build})
  [{:keys [port shadow-build]}]
  (start-server! {:port port :dev-mode? true})
  (shadow-cljs/empty-output-directory! shadow-build)
  (shadow-cljs/stop!)
  (shadow-cljs/start!)
  (shadow-cljs/watch shadow-build)
  (println "boot-loader - Development mode started")
  (x.core/print-state!))
