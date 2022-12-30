
(ns site.xgo.backend.price-quote.lifecycles
    (:require [x.core.api :as x.core]))

(x.core/reg-lifecycles! ::lifecycles
  {:on-server-boot 
    [:x.router/add-route! :price-quote/route
                          {:client-event   [:price-quote/load-page!]
                           :js-build       :site
                           :route-template "/jarmuvek/:category/:model/:type/arajanlat"}]})
