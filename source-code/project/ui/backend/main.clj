
(ns project.ui.backend.main
    (:require [app.website-config.backend.api    :as website-config]
              [project.ui.backend.loading-screen :as loading-screen]
              [x.router.api                      :as x.router]
              [x.ui.api                          :as x.ui]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-default-js-build
  ; @param (map) request
  ;
  ; @return (keyword)
  [request]
  (if (x.router/request->app-route? request)
      :app :site))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn view
  ; @param (map) request
  [request]
  (let [website-config   (website-config/get-website-config)
        loading-screen   (loading-screen/view  request)
        default-js-build (get-default-js-build request)]
       (x.ui/html (x.ui/head request (assoc website-config           :default-js-build default-js-build))
                  (x.ui/body request {:loading-screen loading-screen :default-js-build default-js-build}))))
