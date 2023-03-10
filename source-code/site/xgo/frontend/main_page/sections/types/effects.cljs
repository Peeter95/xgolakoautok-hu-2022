
(ns site.xgo.frontend.main-page.sections.types.effects
    (:require [re-frame.api  :as r]
              [normalize.api :as normalize]))

(r/reg-event-fx
 :types/select!
 (fn [{:keys [db]} [_ name]]
  (let [type-name (normalize/clean-text name "-+")
        {:keys [category model]} (get db :filters)]
    {:dispatch [:x.db/set-item! [:filters :type] type-name]
     :url/set-url! (str "/jarmuvek/" category "/" model "/" type-name)})))

(r/reg-event-fx
 :types.view/back!
 (fn [{:keys []
       {:keys [filters]} :db} [_]]
   (let [category (select-keys filters [:category])]
     {:dispatch           [:x.db/set-item! [:filters] category]
      :url/set-url!       (str "/jarmuvek/" (:category category))
      :scroll/scroll-into ["xgo-categories--container" {:behavior "smooth"
                                                        :block    "start"
                                                        :inline   "start"}]})))

(r/reg-event-fx
 :type.view/get-price-qoute!
 (fn [{:keys [db]} [_]]
   (let [{:keys [category model type]} (get db :filters)
         link (str "/jarmuvek/" category "/" model "/" type "/arajanlat")]
     {:dispatch-n [[:x.router/go-to! link]]})))