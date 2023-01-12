
(ns site.xgo.frontend.main-page.sections.hero
    (:require [re-frame.api                 :as r]
              [css.api                      :as css]
              [site.components.frontend.api :as components]
              [x.environment.api            :as x.environment]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn- logo []
  (let [logo-data @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :website-logo]])
        {:media/keys [uri]} logo-data]
    [:div {:id    "xgo-hero--logo"
           :style {:background-image (css/url uri)}}]))

(defn- slogan []
  (let [ website-slogan @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :website-slogan]])]
    [:div {:id "xgo-hero--slogan"}
      website-slogan]))

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
        (letfn [(scroll-f [intersecting?]
                        (x.environment/set-element-attribute! "xgo-hero--header" "data-hidden"      intersecting?)
                        (x.environment/set-element-attribute! "xgo-hero--header" "data-hidden" (not intersecting?)))]
              [components/scroll-sensor ::scroll-sensor {:callback-f scroll-f
                                                         :style      {:position "absolute" :top "0" :left "0"}}])
        [header]
        [components/scroll-icon {:style {:position "absolute" :bottom "45px" :left "0"}}]])
   
