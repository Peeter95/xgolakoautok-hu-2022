
(ns site.xgo.frontend.main-page.sections.categories.effects
  (:require [re-frame.api         :as r]
            [normalize.api :as normalize]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(r/reg-event-fx
  :categories/select!
  (fn [_ [_ name]]
    (let [category-name (normalize/clean-text name "-+")]
      {:dispatch     [:x.db/set-item! [:filters] {:category category-name}]
       :url/set-url! (str "/jarmuvek/" category-name)})))
