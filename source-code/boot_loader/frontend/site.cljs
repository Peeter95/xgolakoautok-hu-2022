
(ns boot-loader.frontend.site
    (:require ;[project-developer.api :as project-developer]
              [x.boot-loader.api     :as x.boot-loader]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- site-structure
  ; @param (symbol) ui-structure
  ;
  ; @usage
  ;  [site-structure #'my-ui-structure]
  [ui-structure]
  [:<> [:div "Hello"]])
       ;[project-developer/magic-button]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-site!  [] (x.boot-loader/start-app!  #'site-structure))
(defn render-site! [] (x.boot-loader/render-app! #'site-structure))
