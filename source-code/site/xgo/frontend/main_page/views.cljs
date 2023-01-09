
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
 (fn [{:keys [db]} [_]]
   (if-not (empty? (router/get-current-route-path-params db))
      {:scroll/scroll-into ["xgo-categories"]}
      {})))

(defn credits
  []
  [:div {:style {:background "#2d2925" :padding "15px 0 15px 0" :color "#9ec3fb"}}
    [components/credits {:theme :dark}]])
  ;;  [components/crseated-by-link ::created-by-link {:theme :dark}]])

(defn- footer []
 (let [footer-menu @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :footer-menu]])]
    [:<> 
      ;; [components/menu ::footer-menu {:menu-link footer-menu}]
      [credits]]))
      
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
        [categories/thumbnail]
        [sections/contacts]
        [footer]])}))
