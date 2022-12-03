
(ns site.sample.frontend.main-page.effects
    (:require [re-frame.api                         :as r]
              [site.sample.frontend.main-page.views :as views]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(r/reg-event-fx :main-page/render-page!
  [:x.ui/render-surface! :main-page/view {:content #'views/view}])

(r/reg-event-fx :main-page/load-page!
  [:main-page/render-page!])
