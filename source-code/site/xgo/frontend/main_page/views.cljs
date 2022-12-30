
(ns site.xgo.frontend.main-page.views
    (:require [re-frame.api                              :as r]
              [reagent.api                               :as reagent]
              [site.components.frontend.api              :as components]
              [site.xgo.frontend.main-page.sections.api  :as sections]
              [x.router.api                              :as router]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------


(r/reg-event-fx
 :init
 (fn [{:keys [db]} [_]]
   (if-not (empty? (router/get-current-route-path-params db))
      {:scroll/scroll-into ["xgo-categories"]}
      {})))

(defn view
  []
  (reagent/lifecycles 
   {:component-did-mount (fn [] (r/dispatch [:init]))
    :reagent-render 
    (fn [] 
      [:div#xgo
        [sections/hero]
        [sections/categories]
        (if @(r/subscribe [:filters/model])
          [sections/types]
          [sections/models])
        [sections/contacts]
        [:div {:style {:background-image "url(/xgo/img/footer.jpg)"
                       :background-size "cover"
                       :background-repeat "no-repeat"
                       :height  "100vh"
                       :width   "100%"}}]
        [:div {:style {:background "#2d2925" :padding "60px 0 15px 0"}}
          [components/credits {:theme :dark}]]])}))