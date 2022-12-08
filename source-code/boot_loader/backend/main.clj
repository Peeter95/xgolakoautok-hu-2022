
(ns boot-loader.backend.main
    (:require [project.resources.backend.api]
              [project.router.backend.api]
              [pattern.api       :as p]
              [shadow-cljs.api   :as shadow-cljs]

              ; monotech-hq/x5
              [x.boot-loader.api :as x.boot-loader]
              [x.core.api        :as x.core]

              ; monotech-hq/monoset
              [elements.api]
              [fonts.api]
              [forms.api]
              [icons.api]
              [layouts.popup-a.api]
              [layouts.surface-a.api]

              ; monotech-hq/project-developer
              [project-developer.api]

              ; monotech-hq/project-kit
              [app.common.backend.api]
              [app.common.iso.api]
              [app.contents.backend.api]
              [app.home.backend.api]
              [app.settings.backend.api]
              [app.storage.backend.api]
              [app.user.backend.api]
              [app.views.backend.api]

              ; sample
              [app.sample.backend.api]
              [site.sample.backend.api])

    (:gen-class))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-server!
  ; @param (map) server-props
  ; {:port (integer or string)(opt)}
  [{:keys [port] :as server-props}]
  (println "boot-loader - Starting server on port:" (or port "@default"))
  (x.boot-loader/start-server! server-props))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn -main
  ; @param (list of *) args
  ; [(integer)(opt) port]
  ;
  ; @usage
  ; (-main 3000)
  ;
  ; @usage
  ; java -jar {{namespace}}.jar 3000
  [& [port :as args]]
  ; In product releases the pattern.api validator should be turned off.
  ; Excepts when you trying to debug the application.
  (p/ignore!) ; <- Turning off the pattern.api validator
  (start-server! {:port port}))

(defn dev
  ; @param (map) options
  ; {:port (integer)
  ;  :shadow-build (keyword)}
  ;
  ; @usage
  ; (dev {:shadow-build :my-build})
  [{:keys [port shadow-build]}]
  (letfn [(callback-f [] (shadow-cljs/use-build! shadow-build))]
         (start-server! {:callback-f callback-f
                         :port       port
                         :dev-mode?  true})))
