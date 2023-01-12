
(ns site.xgo.frontend.price-quote.subs
   (:require [re-frame.api :as r]))

(r/reg-sub 
 :step.one/valid?
 (fn [db [_]]
   (let [{:keys [name email]} (get db :price-quote)]
     (and (not-empty name) 
          (not-empty email)))))

(r/reg-sub 
 :price-quote/breadcrumbs
 :<- [:categories/selected]
 :<- [:models/selected]
 :<- [:types/selected]
 (fn [[category model type] [_]]
    {:category-name (:name category) 
     :model-name    (:name model)
     :type-name     (:name type)}))

(r/reg-sub 
 :price-quote.item.quantity/get
 (fn [db [_ [tab-id item-id]]]
   (get-in db [:price-quote tab-id item-id] 0)))

(r/reg-sub 
 :price-quote.overview.accessories/get-items
 (fn [db [_ [accessory-id items]]]
    (let [accessories (get-in db [:site accessory-id])]
      (letfn [(f [m [item-id item-count]]
                (let [accessory-item (assoc (get accessories item-id false) 
                                            :count item-count)]
                  (assoc m item-id accessory-item)))]
        (reduce f {} items)))))