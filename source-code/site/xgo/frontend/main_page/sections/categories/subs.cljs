
(ns site.xgo.frontend.main-page.sections.categories.subs
  (:require [re-frame.api         :as r]
            [normalize.api :as normalize]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------
;; ----- Filters -----

(r/reg-sub 
  :filters/category 
  (fn [db [_]]
    (get-in db [:filters :category] "dynamic")))

;; ----- Filters -----
;; -----------------------------------------------------------------------------

;; -----------------------------------------------------------------------------
;; ----- Categories -----

(r/reg-sub
  :categories/selected
  :<- [:categories/all]
  :<- [:filters/category]
  (fn [[categories filters-category] [_]]
    (get categories filters-category)))
                             
(r/reg-sub
  :categories.selected/description
  (fn [db [_]]
    (let [category-name (get-in db [:filters :category] "dynamic")]
      (get-in db [:site :categories category-name :description]))))

(r/reg-sub
  :categories.selected/models
  :<- [:categories/all]
  :<- [:filters/category]
  (fn [[categories filters-category] [_]]
    (->> (get-in categories [filters-category :models])
         (mapv :model/id))))

(r/reg-sub 
  :categories/all 
  (fn [db [_]]
    (get-in db [:site :categories])))

(r/reg-sub
  :categories/selected?
  :<- [:filters/category]
  (fn [filters-category [_ category-name]]
    (= filters-category (normalize/clean-text category-name "-+"))))

;; ----- Categories -----
;; -----------------------------------------------------------------------------