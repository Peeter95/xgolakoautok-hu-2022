
(ns site.sample.frontend.main-page.views
    (:require [site.components.frontend.api                     :as components]
              [site.sample.frontend.main-page.sections.contacts :as contacts]
              [site.sample.frontend.main-page.sections.hero     :as hero]
              [site.sample.frontend.main-page.sections.scheme   :as scheme]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn view
  ; @param (keyword) surface-id
  []
  [:div#mt-main-page
    [hero/view]
    [scheme/view]
    [contacts/view]
    [:div {:style {:background "#2d2925" :padding "60px 0 15px 0"}}
          [components/credits {:theme :dark}]]])
