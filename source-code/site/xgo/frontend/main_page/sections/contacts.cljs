
(ns site.xgo.frontend.main-page.sections.contacts
  (:require [re-frame.api :as r]
            [string.api                   :as string]
            [uri.api                      :as uri]
            [site.components.frontend.api :as components]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(defn social-media-link
  [link icon-class label]
  [:a.xgo-social-media-link {:href (uri/valid-uri link) :target "_blank" :title label}
                           [:i {:class icon-class}]
                           [:span (-> link (string/not-starts-with! "https://")
                                           (string/not-starts-with! "http://")
                                           (string/not-starts-with! "www."))]])

(defn facebook-links
  []
  (let [facebook-links @(r/subscribe [:x.db/get-item [:website-contacts :handler/transfered-content :facebook-links]])]
       (letfn [(f [links link] (conj links [social-media-link link [:fab :fa-facebook] "Tovább a Facebook-ra"]))]
              (reduce f [:<>] facebook-links))))

(defn instagram-links
  []
  (let [instagram-links @(r/subscribe [:x.db/get-item [:website-contacts :handler/transfered-content :instagram-links]])]
    (letfn [(f [links link] (conj links [social-media-link link [:fab :fa-instagram] "Tovább a Instagram-ra"]))]
           (reduce f [:<>] instagram-links))))

(defn youtube-links
  []
  (let [youtube-links @(r/subscribe [:x.db/get-item [:website-contacts :handler/transfered-content :youtube-links]])]
    (letfn [(f [links link] (conj links [social-media-link link [:fab :fa-youtube] "Tovább a Youtube-ra"]))]
           (reduce f [:<>] youtube-links))))

(defn social-media-links
  []
  [:<> [facebook-links]
       [instagram-links]
       [youtube-links]])

(defn- contacts
  []
  [:<> [:p.xgo-section-title "Kapcsolat"]
       [components/contacts ::contacts {}]
       [:div#xgo-social-media-links [social-media-links]]])


(defn view
  []
  [:section {:id "xgo-contacts"}
            [contacts]
            [:div#xgo-contacts--background]])
