
(ns site.xgo.frontend.main-page.sections.models.views
  (:require [re-frame.api :as r]
            [elements.api :as x.elements]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn dimension [icon num]
  [:div {:class "xgo-model-card--dimension"}
    [x.elements/icon {:icon  icon
                      :class "xgo-model-card--dimension-icon"}]
    [:div {:class "xgo-model-card--dimension-label"} (first num)]])

(defn model-dimensions [types]
 (if types
  (let [{:type/keys [seat bed]} @(r/subscribe [:models.type/dimension types])]
   [:div {:class "xgo-model-card--dimensions"}
     [dimension "airline_seat_recline_normal" seat]
     [dimension "airline_seat_flat" bed]]))) 

(defn model-name [name]
  [:p {:class "xgo-model-card--name"}
    name])

(defn model-thumbnail [{:media/keys [uri]}]
   [:img {:class "xgo-model-card--thumbnail"
          :src   uri}])

(defn- model [[id {:keys [name thumbnail types] :as model-data}]]
 [:button {:key      id
           :on-click #(r/dispatch [:models/select! name])}
   [:div {:class "xgo-model-card"}
     [model-name       name]
     [model-thumbnail  thumbnail]
     [model-dimensions types]]])
  
(defn- models [{:keys [models-data]}]
  [:div {:id "xgo-models"}
    (if (empty? models-data)
      [:p "Empty..."]
      (doall (map model models-data)))])

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn view []
  (let [view-props {:models-data @(r/subscribe [:models/by-category])}]
    [:section {:id "xgo-models--container"}
              [models view-props]]))
