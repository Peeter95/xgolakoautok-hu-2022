
(ns site.xgo.frontend.main-page.views
    (:require [re-frame.api                              :as r]
              [reagent.api                               :as reagent]
              [site.components.frontend.api              :as components]
              [site.xgo.frontend.main-page.sections.api  :as sections]
              [x.router.api                              :as router]
              
              [site.xgo.frontend.main-page.sections.categories.api :as categories]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------


(r/reg-event-fx
 :init
 (fn [{:keys [db]} [_ scroll-target]]
   (if-not (nil? scroll-target);(empty? (router/get-current-route-path-params db))
     {:scroll/scroll-into [(name scroll-target)]}
     {})))
      
(defn view
  [& [scroll-target]]
  (reagent/lifecycles 
   {:component-did-mount (fn [] (r/dispatch [:init scroll-target]))
    :reagent-render 
    (fn [] 
      [:div#xgo
        [sections/hero]
        [sections/categories]
        (if @(r/subscribe [:filters/model])
          [sections/types]
          [sections/models])
        [categories/thumbnail]
        [sections/contacts]])}))
        ;; [footer]])}))
