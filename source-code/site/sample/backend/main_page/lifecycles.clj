
(ns site.sample.backend.main-page.lifecycles
    (:require [x.core.api :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(x.core/reg-lifecycles! ::lifecycles
  {:on-server-boot [:x.router/add-route! :sample.main-page/route
                                         {:client-event   [:main-page/load-page!]
                                          :js-build       :site
                                          :route-template "/sample"}]})
