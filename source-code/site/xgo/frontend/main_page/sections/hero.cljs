
(ns site.xgo.frontend.main-page.sections.hero
    (:require [site.components.frontend.api :as components]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn- logo []
  [:div {:id "xgo-hero--logo"}])

(defn- slogan []
  [:div {:id "xgo-hero--slogan"}
    "Örökké mozgásban"])

(defn- header []
 [:div {:id "xgo-hero--header"}
   [logo]
   [slogan]])

(defn- hero
  []
  [:div {:class "xgo-section--body"}])

(defn view
  []
  [:section {:id "xgo-hero"}
        [header]
        [components/scroll-icon {:style {:position "absolute" :bottom "15px" :left "0"}}]])
   
