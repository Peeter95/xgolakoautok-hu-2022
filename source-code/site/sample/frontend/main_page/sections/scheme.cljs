
(ns site.sample.frontend.main-page.sections.scheme
    (:require [site.components.frontend.api :as components]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- scheme
  []
  [:div {:class :mt-section-body}
        [components/scheme-table {:placeholder :no-visible-data
                                  :scheme-id   :my-scheme
                                  :value-path  [:my-data]}]])

(defn view
  []
  [:section {:id :mt-scheme}
            [scheme]])
