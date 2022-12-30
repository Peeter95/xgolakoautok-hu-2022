
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
     
              [app.website-config.frontend.api]
              [app.website-contacts.frontend.api]
              [app.website-content.frontend.api]
              [app.website-menus.frontend.api]
              [app.website-pages.frontend.api]
              [app.website-link.frontend.api]
     
              [app.vehicle-categories.frontend.api]
              [app.vehicle-models.frontend.api]
              [app.vehicle-types.frontend.api]
              
              [app.products.frontend.api]
              [app.packages.frontend.api]
              [app.services.frontend.api]
              
              [app.price-quotes.frontend.api]
              [app.price-quote-inquiries.frontend.api]
              [app.price-quote-templates.frontend.api]
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
