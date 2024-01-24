
(ns site.xgo.frontend.main-page.sections.types.views
  (:require [re-frame.api                     :as r]
            [reagent.api                      :as reagent]
            [site.components.frontend.api     :as site.components]
            [site.xgo.components.frontend.api :as xgo.components]
            [io.api                           :as io]
            [format.api                       :as format]
            [elements.api                     :as elements]
            ["react"                          :as react]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn- model-name [name]
  [:div {:id "xgo-type--model-name"}
    name])

(defn- type-name [[id {:keys [name]}]]
  [:div {:key   id 
         :class "xgo-type--name"}
    (str name)]) 

(defn- type-name-button [[id {:keys [name]}]]
  [:button {:key           id 
            :class         "xgo-type--name-button xgo-type--name"
            :data-selected @(r/subscribe [:types/selected? name])
            :on-click      #(r/dispatch [:types/select! name])}
    name])

(defn- type-name-button-group [types-data]
  [:div {:id "xgo-type--name-button-group"}
      (doall (map type-name-button types-data))])

(defn- header [{:keys [types-data model-data]}]
  [:div {:id "xgo-type--header"}
    [model-name model-data]
    (if (= 1 (count types-data))
      [type-name (first (seq types-data))]
      [type-name-button-group types-data])])


(defn- fullscreen-event-listeners []
  (react/useEffect 
    (fn []
      ;; (r/dispatch [:x.environment/reg-keypress-event! :color-lab.fullscreen.event.open
      ;;                                                 {:key-code   70
      ;;                                                  :required?  false
      ;;                                                  :on-keydown (fn []
      ;;                                                                (when @(r/subscribe [:x.db/get-item [::focused]])
      ;;                                                                  (r/dispatch [:x.db/set-item! [::fullscreen?] true])))}])
      (.addEventListener (.getElementById js/document "xgo-type--images") 
                         "fullscreenchange" 
                         (fn [e]
                           (if (.-fullscreenElement js/document)
                              (r/dispatch [:x.db/set-item! [::fullscreen?] true])
                              (r/dispatch [:x.db/set-item! [::fullscreen?] false]))))
      
      (r/dispatch [:x.environment/reg-keypress-event! :fullscreen.event.exit
                                                      {:key-code   27
                                                       :required? false
                                                       :on-keydown (fn []
                                                                     (println "exit")
                                                                     (r/dispatch [:x.db/set-item! [::fullscreen?] false])
                                                                     (r/dispatch [:x.db/set-item! [::fullscreen?] false]))}])
                                                                    ;;  (.exitFullscreen (.getElementById js/document "xgo-type--images")))}])
      (fn []
        ;; (r/dispatch [:elements.combo-box/remove-keypress-events! :color-lab.fullscreen.event.open])
        (r/dispatch [:elements.combo-box/remove-keypress-events! :fullscreen.event.exit])))
    #js[]))

(defn- type-images [images]
  (fullscreen-event-listeners)

  (if (empty? images)
    [:div {:id "xgo-type--images"} [:p {:style {:height "400px"}} "No image"]]

    (let [fullscreen? @(r/subscribe [:x.db/get-item [::fullscreen?] false])]
      [:div {:id "xgo-type--images"
             "fullscreenchange" #(println "full screen change")}
            ;;  :on-click #(if fullscreen?
            ;;               (.exitFullscreen js/document)
            ;;               (.requestFullscreen (.getElementById js/document "xgo-type--images")))}
        [:button {:on-click #(if fullscreen?
                                (.exitFullscreen js/document)
                                (.requestFullscreen (.getElementById js/document "xgo-type--images")))
                  :style    (if fullscreen? 
                              {:position "absolute"
                               :top   "5px"
                               :right    "3px"
                               :color    "white"
                               :z-index  101}
                              {:position "absolute"
                               :bottom   "5px"
                               :right    "3px"
                               :color    "white"
                               :z-index  101})}
          [elements/icon {:icon (if fullscreen? :close :fullscreen)}]]
        [xgo.components/slider {};:showThumbs fullscreen?}
          (map (fn [{:media/keys [id uri]}]
                   [:img {:key id :src uri :style {:display "flex" 
                                                          :object-fit "contain"
                                                           :align-items "center"
                                                           :background-image (str "url(" uri ")")
                                                           :background-repeat "no-repeat"
                                                           :background-position "center"
                                                           :background-size "contain" 
                                                           :aspect-ratio "16/10" 
                                                           :width "100%"}}])
                  ;;  [:div {:key   id
                  ;;         :style {:display "flex" 
                  ;;                 :cursor "grab"
                  ;;                        :align-items "center"
                  ;;                        :background-image (str "url(" uri ")")
                  ;;                        :background-repeat "no-repeat"
                  ;;                        :background-position "center"
                  ;;                        :background-size "contain" 
                  ;;                        :aspect-ratio "16/10" 
                  ;;                        :height "100%"}}])
                     
               images)]])))

(defn- type-table [id]
  [:div {:class "mt-scheme-table--container" :style {:min-height "550px"}}
    [:h2.mt-scheme-table--header "Műszaki adatok"]
    [site.components/scheme-table {:placeholder :no-visible-data
                                   :scheme-id   :vehicle-types.technical-data
                                   :value-path  [:site :types id]}]])

(defn- type-file [{:media/keys [alias uri size] :as props}]
  (let [file-size (-> size io/B->MB format/decimals (str " MB"))]
    [:a {:class    "xgo-type--file-container"
         :key      uri
         :href     uri 
         :download alias} 
      [:i {:class "xgo-type--file-icon fas fa-file-pdf"}]
      [:div {:class "xgo-type--file-data"}
        [:span {:class "xgo-type--file-name"} alias]
        [:span {:class "xgo-type--file-size"} file-size]]])) 
    
(defn- type-files [{:media/keys [alias] :as files}]
  [:div {:class "xgo-type--files-container"}
    (map type-file files)])

(defn- type-back-button []
  [:div {:id "xgo-type--back-button-container"}
   [:button {:id       "xgo-type--back-button"
             :on-click #(r/dispatch [:types.view/back!])}
        "Vissza"]])    

(defn- get-price-quote-button [selected-type]
  [:div {:id "xgo-type--price-quote-button"}
    [:button {:on-click #(r/dispatch [:type.view/get-price-qoute! (:id selected-type)])}
      "Árajánlat kérése"]])

(defn- vehicle-type [{:keys [name] :as data}]
  (reagent/lifecycles 
   {:component-did-mount (fn [] (r/dispatch [:types/select! name]))
    :reagent-render 
    (fn [{:keys [id images files] :as data}]
      (let [description @(r/subscribe [:models.selected/description])]
        [:div {:key id
               :id  "xgo-type"}
          [:div {:id "xgo-type--layout"} 
            [:f> type-images images]
            [type-table id]]
          [:div {:id         "xgo-type--layout-desc-files"
                 :data-files (empty? files)
                 :data-desc  (empty? description)}
               
            [:div {:style {:display (when (empty? description) "none")}} 
              [:p.label "Leírás"]
              [:p description]]
            [:div  {:style {:display (when (empty? files) "none")}}
              [:p.label "Letölthető fájlok"]
              [type-files files]]]
          [type-back-button]]))}))

(defn- types [{:keys [types-data selected-type] :as view-props}]
  [:div {:id "xgo-type--container"}
    [header view-props]
    ;; [get-price-quote-button selected-type]
    [vehicle-type selected-type]])

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn view []
  (let [view-props {:types-data    @(r/subscribe [:types/by-model])
                    :selected-type @(r/subscribe [:types/selected])
                    :model-data    @(r/subscribe [:models.selected/name])}]
    [:section {:id "xgo-types--container"}
            [types view-props]]))
