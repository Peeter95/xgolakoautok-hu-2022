
(ns sample.frontend.app
    (:require ; monotech-hq/project-developer
              [project-developer.api]
              ; monotech-hq/project-kit
              [app.common.frontend.api]
              [app.common.iso.api]
              [app.components.frontend.api]
              [app.contents.frontend.api]
              [app.home.frontend.api]
              [app.settings.frontend.api]
              [app.storage.frontend.api]
              [app.user.frontend.api]
              [app.views.frontend.api]
              ; monotech-hq/x5
              [x.boot-loader.api]
              ; ...
              [project-developer.api :as project-developer]
              [x.boot-loader.api     :as x.boot-loader]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- app-structure
  ; @param (symbol) ui-structure
  [ui-structure]
  [:<> [ui-structure]
       [project-developer/magic-button]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-app!  [] (x.boot-loader/start-app!  #'app-structure))
(defn render-app! [] (x.boot-loader/render-app! #'app-structure))
