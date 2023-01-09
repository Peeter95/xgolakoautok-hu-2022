
(ns sample.frontend.site
    (:require ; monotech-hq/project-developer
              [project-developer.api]
              ; monotech-hq/project-kit
              [app.contents.frontend.api]
              [app.views.frontend.api]
              [site.website-menus.frontend.api]
              [site.website-pages.frontend.api]
              ; monotech-hq/x5
              [x.boot-loader.api]
              ; ...
              [project-developer.api :as project-developer]
              [x.boot-loader.api     :as x.boot-loader]
              [site.xgo.frontend.api :as xgo]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- site-structure
  ; @param (symbol) ui-structure
  [ui-structure]
  [:<> [xgo/wrapper ui-structure]
       [project-developer/magic-button]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-site!  [] (x.boot-loader/start-app!  #'site-structure))
(defn render-site! [] (x.boot-loader/render-app! #'site-structure))
