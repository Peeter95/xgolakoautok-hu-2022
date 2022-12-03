
(ns boot-loader.sample.frontend.site
    (:require ; Extra modules
              [project-developer.api :as project-developer]
              [x.boot-loader.api     :as x.boot-loader]

              ; App modules
              [app.contents.frontend.api]
              [app.views.frontend.api]

              ; Site modules
              [site.sample.frontend.api :as sample]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- site-structure
  [ui-structure]
  [:<> [sample/wrapper ui-structure]
       [project-developer/magic-button]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-site!  [] (x.boot-loader/start-app!  #'site-structure))
(defn render-site! [] (x.boot-loader/render-app! #'site-structure))
