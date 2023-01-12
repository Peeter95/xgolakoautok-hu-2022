
(ns site.xgo.frontend.api
    (:require [site.xgo.frontend.main-page.api]
              [site.xgo.frontend.price-quote.api]
              [site.xgo.frontend.utils.api]
              [site.xgo.frontend.wrapper.api :as wrapper.views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; site.xgo.frontend.wrapper.views
(def wrapper wrapper.views/view)
