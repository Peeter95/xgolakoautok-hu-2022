
(ns site.xgo.frontend.wrapper.views
    (:require [re-frame.api                 :as r]
              [site.components.frontend.api :as components]
              [x.environment.api            :as x.environment]))

;; -----------------------------------------------------------------------------
;; ---- Components ----

;; --- Sidebar ---

(defn sidebar-menu
  []
  (let [sidebar-menu @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :sidebar-menu]])]
       [components/menu ::sidebar-menu
                        {:menu-link sidebar-menu}]))

(defn sidebar
  []
  [components/sidebar {:content #'sidebar-menu}])

;; --- Sidebar ---

;; --- Header ---

(defn website-logo
  []
  (let [website-name   @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :website-name]])
        website-slogan @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :website-slogan]])]
       [:a {:href "/" :id :xgo-navbar--logo :data-clickable true :on-mouse-up #(x.environment/blur-element!)}
           [:div#xgo-navbar--container
             [:div#xgo-navbar--website-name   website-name]
             [:div#xgo-navbar--slogan website-slogan]]]))

(defn header
  []
  (let [header-menu @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :header-menu]])]
       [components/navbar {:logo      #'website-logo
                           :menu-link header-menu
                           :on-menu   [:components.sidebar/show-sidebar!]
                           :threshold 800}]))

;; --- Header ---

;; --- Footer ---

(defn credits
  []
  [:div {:style {:background "#2d2925" :padding "15px 0 15px 0" :color "#9ec3fb"}}
    ;; [components/credits {:theme :dark}]
   [components/created-by-link ::created-by-link {:theme :dark}]])

(defn- footer []
  (let [footer-menu @(r/subscribe [:x.db/get-item [:website-content :handler/transfered-content :footer-menu]])]
     [:div {:id "xgo-footer"}
      [components/menu ::footer-menu {:menu-link footer-menu}]
      [credits]]))

;; --- Footer ---

;; ---- Components ----
;; -----------------------------------------------------------------------------

(defn wrapper
  ; @param (symbol) ui-structure
  [ui-structure]
  (let [website-page?  @(r/subscribe [:website-pages.handler/on-page?])]
    [:div#mt {:data-websitepage website-page?}
      [ui-structure]
      [footer]
      [header]
      [sidebar]]))

(defn view
  ; @param (symbol) ui-structure
  [ui-structure]
  [wrapper ui-structure])
