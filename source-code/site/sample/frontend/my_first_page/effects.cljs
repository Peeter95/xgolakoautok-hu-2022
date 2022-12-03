
(ns site.sample.frontend.my-first-page.effects
    (:require [re-frame.api                             :as r]
              [site.sample.frontend.my-first-page.views :as views]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(r/reg-event-fx :my-first-page/render-page!
  [:x.ui/render-surface! :my-first-page/view {:content #'views/view}])

(r/reg-event-fx :my-first-page/load-page!
  [:main-page/render-page!])
