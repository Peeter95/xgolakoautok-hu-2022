
(ns site.xgo.components.frontend.api
    (:require [site.xgo.components.frontend.slider.views             :as slider.views]
              [site.xgo.components.frontend.stepper.api              :as stepper]
              [site.xgo.components.frontend.tabs.views               :as tabs]
              [site.xgo.components.frontend.table.views              :as table.views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; site.xgo.components.frontend.slider.views
(def slider slider.views/component)

; site.xgo.components.frontend.stepper.api
(def stepper stepper/component)

; site.xgo.components.frontend.tabs.views
(def tabs tabs/component)

; site.xgo.components.frontend.tabs.views
(def table table.views/component)
