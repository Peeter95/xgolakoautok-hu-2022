
(ns site.xgo.frontend.main-page.sections.models.effects
    (:require [re-frame.api  :as r]
              [normalize.api :as normalize]))

(r/reg-event-fx
  :models/select! 
 (fn [{:keys [db]} [_ model-name]]
   (let [model-name (normalize/clean-text model-name "-+")
         category   (-> db (get-in [:filters :category]) (normalize/clean-text "-+"))]
     {:dispatch-n   [[:x.db/set-item! [:filters :model] model-name]]
      :scroll/scroll-into ["xgo-categories--container" {:behavior "smooth"}]
      :url/set-url! (str "/jarmuvek/" category "/" model-name)})))
