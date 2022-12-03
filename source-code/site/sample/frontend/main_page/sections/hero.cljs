
(ns site.sample.frontend.main-page.sections.hero
    (:require [site.components.frontend.api :as components]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn- hero
  []
  [:div {:class :mt-section-body}
        [components/scroll-icon {:style {:position "absolute" :bottom "0" :left "0"}}]])

(defn view
  []
  [:section {:id :mt-hero}
            [hero]])
