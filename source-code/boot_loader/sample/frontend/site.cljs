
(ns boot-loader.sample.frontend.site
    (:require ; monotech-hq/project-developer
              [project-developer.api :as project-developer]

              ; monotech-hq/project-kit
              [app.contents.frontend.api]
              [app.views.frontend.api]

              ; monotech-hq/x5
              [x.boot-loader.api :as x.boot-loader]

              ; sample
              [site.sample.frontend.api :as sample]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- site-structure
  ; @param (symbol) ui-structure
  [ui-structure]
  [:<> [sample/wrapper ui-structure]
       [project-developer/magic-button]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-site!  [] (x.boot-loader/start-app!  #'site-structure))
(defn render-site! [] (x.boot-loader/render-app! #'site-structure))
