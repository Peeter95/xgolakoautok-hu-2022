
(ns site.xgo.backend.main-page.lifecycles
    (:require [x.core.api   :as x.core]
              [re-frame.api :as re-frame :refer [r]]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn xyz [db [_ route-id route-props]]
  (let [route-props (r x.router.route-handler.prototypes/route-props-prototype db route-id route-props)]
    (r x.router.route-handler.events/store-route-props! db route-id route-props)))

(re-frame/reg-event-fx
  :debug.routes
  (fn [{:keys [db]} [_]]
    {:db (as-> db % 
           (r xyz % :main-page/route
                    {:client-event   [:main-page/load-page!]
                     :js-build       :site
                     :route-template "/"})
           (r xyz % :main-page.category/route
                    {:client-event   [:main-page/load-page!]
                     :js-build       :site
                     :route-template "/:category"})

           (r xyz % :main-page.model/route
                    {:client-event   [:main-page/load-page!]
                     :js-build       :site
                     :route-template "/:category/:model"})

           (r xyz % :main-page.type/route
                    {:client-event   [:main-page/load-page!]
                     :js-build       :site
                     :route-template "/:category/:model/:type"}))}))


(x.core/reg-lifecycles! ::lifecycles
  {:on-server-boot
    {:dispatch [:debug.routes]}}) 
    ;; {:dispatch-n [[:x.router/add-route! :main-page/route
    ;;                                     {:client-event   [:main-page/load-page!]
    ;;                                      :js-build       :site
    ;;                                      :route-template "/"}]
    ;;               [:x.router/add-route! :main-page.category/route
    ;;                                     {:client-event   [:main-page/load-page!]
    ;;                                      :js-build       :site
    ;;                                      :route-template "/:category"}]
    ;;               [:x.router/add-route! :main-page.model/route
    ;;                                     {:client-event   [:main-page/load-page!]
    ;;                                      :js-build       :site
    ;;                                      :route-template "/:category/:model"}]
    ;;               [:x.router/add-route! :main-page.type/route
    ;;                                     {:client-event   [:main-page/load-page!]
    ;;                                      :js-build       :site
    ;;                                      :route-template "/:category/:model/:type"}]]}})
