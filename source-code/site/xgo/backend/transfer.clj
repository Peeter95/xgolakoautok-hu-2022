
(ns site.xgo.backend.transfer
    (:require [io.api        :as io]
              [map.api       :as map]
              [x.core.api    :as x.core]
              [mongo-db.api  :as mongo-db]
              [normalize.api :as normalize]))

(defn convert [key-fn data]
  (letfn [(vec->map [m v] (assoc m (key-fn v) (map/remove-namespace v)))]
         (reduce vec->map {} data)))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(def categories-projection
     {:projection #:category{:name        1
                             :order       1
                             :models      1
                             :thumbnail   1
                             :description 1}})

(defn transfer-categories-f
  [_]
  (let [data (mongo-db/get-collection "vehicle_categories" categories-projection)]
    (convert #(-> % :category/name (normalize/clean-text "-+")) data)))
        

(x.core/reg-transfer! ::transfer-categories!
  {:data-f      transfer-categories-f
   :target-path [:site :categories]})

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(def models-projection
    {:projection #:model{:name      1
                         :types     1
                         :thumbnail 1}})

(defn transfer-models-f
  [request]
  (let [data (mongo-db/get-collection "vehicle_models" models-projection)]
    (convert #(-> % :model/id) data)))

(x.core/reg-transfer! ::transfer-models!
  {:data-f      transfer-models-f
   :target-path [:site :models]})

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(def types-projection
    {"type/added-at"    0
     "type/added-by"    0
     "type/modified-at" 0
     "type/modified-by" 0})

(def types-media-files-projection
     {"_id"               0
      "media/path"        0
      "media/added-at"    0
      "media/modified-at" 0
      "media/added-by"    0
      "media/modified-by" 0})

(def vehicle-types-pipeline-2
 [{:$unwind     {:path "$type/files"
                 :preserveNullAndEmptyArrays true}}
   
  {:$lookup     {"from"     "storage"
                 "let"      {"fileId" {"$toObjectId" "$type/files.media/id"}}
                 "pipeline" [{"$match"   {"$expr" {"$eq" ["$_id" "$$fileId"]}}}
                             {"$project" types-media-files-projection}]
                 "as"       "files_data"}}
  {:$addFields  {"files" {"$arrayElemAt" ["$files_data" 0]}}}
  {:$group      {:_id         "$_id"
                 :otherFields {"$first" "$$ROOT"}
                 :files       {"$push"  "$files"}}}
  {:$replaceRoot {"newRoot" {"$mergeObjects" ["$otherFields" {"type/files" "$files"}]}}}
  {:$project     {"otherFields" 0
                  "files_data"  0
                  "files"       0}}])
                  
(defn transfer-types-f
  [request]
  (time (let [documents (mongo-db/get-documents-by-pipeline "vehicle_types" vehicle-types-pipeline-2)]
          (convert #(-> % :type/id) documents))))

(x.core/reg-transfer! ::transfer-types!
  {:data-f      transfer-types-f
   :target-path [:site :types]})


;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(def products-projection
  {:projection #:product{:added-at    0
                         :added-by    0
                         :modified-at 0
                         :modified-by 0}})
            
(defn transfer-products-f
  [request]
  (let [data (mongo-db/get-collection "products" products-projection)]
       (convert #(-> % :product/id) data)))

(x.core/reg-transfer! ::transfer-products!
                      {:data-f      transfer-products-f
                       :target-path [:site :products]})

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(def packages-projection
  {:projection #:package{:added-at    0
                         :added-by    0
                         :modified-at 0
                         :modified-by 0}})

(defn transfer-packages-f
  [request]
  (let [data (mongo-db/get-collection "packages" packages-projection)]
       (convert #(-> % :package/id) data)))

(x.core/reg-transfer! ::transfer-packgaes!
                      {:data-f      transfer-packages-f
                       :target-path [:site :packages]})

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(def services-projection
  {:projection #:service{:added-at    0
                         :added-by    0
                         :modified-at 0
                         :modified-by 0}})

(defn transfer-services-f
  [request]
  (let [data (mongo-db/get-collection "services" services-projection)]
       (convert #(-> % :service/id) data)))

(x.core/reg-transfer! ::transfer-services!
                      {:data-f      transfer-services-f
                       :target-path [:site :services]})