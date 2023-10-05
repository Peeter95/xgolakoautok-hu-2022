
(ns site.xgo.frontend.main-page.effects
    (:require [re-frame.api                      :as r]
              [dom.api :as dom]
              [x.router.api                      :as router]
              [site.xgo.frontend.main-page.views :as views]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn- path-params->filters [db]
  (let [path-params (router/get-current-route-path-params db)
        filters     (if (empty? path-params) {:category "dynamic"} path-params)]
    filters))

(r/reg-event-fx :main-page/render-page!
  (fn [{:keys [db]} [_ & [scroll-target]]]
    (let [filters     (path-params->filters db)]
      (if (nil? scroll-target)
        {:dispatch-n     [[:x.db/set-item! [:filters] filters]
                          [:x.ui/render-surface! :main-page/view {:content [views/view scroll-target]}]]}
        {:dispatch-later [{:ms 0 :dispatch-n [[:x.db/set-item! [:filters] filters]
                                              [:x.ui/render-surface! :main-page/view {:content [views/view scroll-target]}]]}
                          {:ms 0 :fx         [:x.environment/scroll-to-element-top! (name scroll-target)]}]}))))

(r/reg-event-fx :main-page/load-page!
  (fn [_ [_ & [scroll-target]]]
    {:dispatch [:main-page/render-page! scroll-target]}))
