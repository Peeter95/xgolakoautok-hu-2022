
(ns boot-loader.frontend.app
    (:require ;[project-developer.api :as project-developer]
              [x.boot-loader.api     :as x.boot-loader]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- app-structure
  ; @param (symbol) ui-structure
  ;
  ; @usage
  ;  [app-structure #'my-ui-structure]
  [ui-structure]
  [:<> [ui-structure]])
       ;[project-developer/magic-button]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn start-app!  [] (x.boot-loader/start-app!  #'app-structure))
(defn render-app! [] (x.boot-loader/render-app! #'app-structure))
