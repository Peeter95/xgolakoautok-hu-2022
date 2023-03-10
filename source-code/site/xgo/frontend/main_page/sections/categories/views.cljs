
(ns site.xgo.frontend.main-page.sections.categories.views
  (:require [re-frame.api         :as r]
            [site.xgo.frontend.main-page.sections.categories.subs]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn- category-button [[id {:keys [name] :as category}]]
  [:button {:key           id
            :class         "xgo-category--button"
            :on-click      #(r/dispatch [:categories/select! name])
            :data-selected @(r/subscribe [:categories/selected? name])}
     name])

(defn- categories [{:keys [categories]}]
  [:div {:id "xgo-categories" :data-scrollable-x "true"}
      (doall (map category-button categories))])

(defn- category-description [{:keys [description]}]
  [:div {:id "xgo-categories--description"}
    description])

(defn category-thumbnail []
  (let [{:media/keys [uri]} @(r/subscribe [:categories.selected/thumbnail])]
    [:div {:id "xgo-categories--thumbnail-container"}
      [:div {:id "xgo-categories--thumbnail"}
        [:img {:src uri}]]]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn view []
  (let [view-props {:categories  @(r/subscribe [:categories/all])
                    :description @(r/subscribe [:categories.selected/description])}]
       [:section {:id "xgo-categories--container"}
                 [categories view-props]
                 [category-description view-props]]))
