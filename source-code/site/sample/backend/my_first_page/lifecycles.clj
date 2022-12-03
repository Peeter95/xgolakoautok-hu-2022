
(ns site.sample.backend.my-first-page.lifecycles
    (:require [x.core.api :as x.core]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(x.core/reg-lifecycles! ::lifecycles
  {:on-server-boot [:x.router/add-route! :my-first-page/route
                                         {:client-event   [:my-first-page/load-page!]
                                          :js-build       :site
                                          :route-template "/my-first-page"}]})
