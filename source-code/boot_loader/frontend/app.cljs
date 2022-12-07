
(ns boot-loader.frontend.app
    (:require ; monotech-hq/project-developer
              [project-developer.api :as project-developer]

              ; monotech-hq/x5
              [x.boot-loader.api :as x.boot-loader]

              ; monotech-hq/project-kit
              [app.common.frontend.api]
              [app.common.iso.api]
              [app.components.frontend.api]
              [app.contents.frontend.api]
              [app.home.frontend.api]
              [app.settings.frontend.api]
              [app.storage.frontend.api]
              [app.user.frontend.api]
              [app.views.frontend.api]))

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
