
(ns site.sample.frontend.wrapper.views
    (:require [re-frame.api                 :as r]
              [site.components.frontend.api :as components]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sidebar-menu
  []
  [:div#mt-sidebar--menu-items
    [:a.mt-sidebar--menu-item.si-effect--underline
      {:href "/my-first-page" :on-click #(r/dispatch [:components.sidebar/hide-sidebar!])}
      "My first page"]])

(defn sidebar
  []
  [components/sidebar {:content #'sidebar-menu}])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn company-name-and-slogan
  []
  (let [company-name   @(r/subscribe [:x.db/get-item [:site :website-config :company-name]])
        company-slogan @(r/subscribe [:x.db/get-item [:site :website-config :company-slogan]])]
       [:a {:href "/" :style {:text-decoration "none"}}
         [:div#mt-navbar--company-name-and-slogan
           [:div#mt-navbar--company-name   company-name]
           [:div#mt-navbar--company-slogan company-slogan]]]))

(defn header
  []
  [components/navbar {:logo       #'company-name-and-slogan
                      :menu-items [{:href "/my-first-page" :label "My first-page" :class :mt-effect--underline}]
                      :on-menu    [:components.sidebar/show-sidebar!]
                      :threshold  800}])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn wrapper
  ; @param (symbol) ui-structure
  [ui-structure]
  [:div#mt
    [ui-structure]
    [header]
    [sidebar]])

(defn view
  ; @param (symbol) ui-structure
  [ui-structure]
  [wrapper ui-structure])
