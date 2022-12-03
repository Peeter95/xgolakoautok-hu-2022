
(ns boot-loader.sample.frontend.app
    (:require ; Extra modules
              [project-developer.api :as project-developer]
              [x.boot-loader.api     :as x.boot-loader]

              ; App modules
              [app.common.frontend.api]
              [app.common.iso.api]
              [app.components.frontend.api]
              [app.contents.frontend.api]
              [app.home.frontend.api]
              [app.settings.frontend.api]
              [app.storage.frontend.api]
              [app.user.frontend.api]
              [app.views.frontend.api]
              [app.website-config.frontend.api]
              [app.website-contacts.frontend.api]
              [app.website-content.frontend.api]
              [app.website-impressum.frontend.api]
              [app.website-link.frontend.api]
              [app.website-menus.frontend.api]
              [app.website-pages.frontend.api]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- app-structure
  [ui-structure]
  [:<> [ui-structure]
       [project-developer/magic-button]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-app!  [] (x.boot-loader/start-app!  #'app-structure))
(defn render-app! [] (x.boot-loader/render-app! #'app-structure))
