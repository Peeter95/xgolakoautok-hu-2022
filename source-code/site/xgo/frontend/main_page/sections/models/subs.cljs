
(ns site.xgo.frontend.main-page.sections.models.subs
    (:require [re-frame.api :as r]
              [normalize.api :as normalize]))

;; -----------------------------------------------------------------------------
;; ----- Filters -----

(r/reg-sub
 :filters/model
 (fn [db [_]]
   (get-in db [:filters :model])))

;; ----- Filters -----
;; -----------------------------------------------------------------------------

;; -----------------------------------------------------------------------------
;; ----- Models -----

(r/reg-sub
  :models/all
  (fn [db [_]]
    (get-in db [:site :models])))

(r/reg-sub
  :models/by-category
  :<- [:models/all]
  :<- [:categories.selected/models]
  (fn [[models category-model-ids] [_]]
    (select-keys models category-model-ids)))

(r/reg-sub
  :models/selected
  :<- [:models/all]
  :<- [:filters/model]
  (fn [[models filters-model] [_]]
    (let [result  (->> models
                       (filter (fn [[_ v]]
                                  (= filters-model (normalize/clean-text (:name v) "-+")))))]
      (if (empty? result)
        {}
        (-> result first val)))))

(r/reg-sub
  :models.selected/name
  :<- [:models/selected]
  (fn [model [_]]
    (:name model)))

(r/reg-sub
  :models.selected/description
  :<- [:models/selected]
  (fn [model [_]]
    (:description model)))

(r/reg-sub
  :models.selected/types
  :<- [:models/selected]
  (fn [{:keys [types]} [_]]
    (mapv :type/id types)))

(r/reg-sub
  :models.type/dimension
  :<- [:types/all]
  (fn [types [_ type-ids]]
    (let [ids (mapv :type/id type-ids)
          [_ type] (first (select-keys types ids))]
      {:type/seat (get type :q68db95dd-892b-4a20-a275-7ae154c44a05 [0])
       :type/bed  (get type :qfdd4d5a4-0628-40a0-83c8-4e5752d71311 [0])}))) 
   
; seat :q68db95dd-892b-4a20-a275-7ae154c44a05
; bed :qfdd4d5a4-0628-40a0-83c8-4e5752d71311

;; ----- Models -----
;; -----------------------------------------------------------------------------