
(ns site.xgo.frontend.main-page.sections.api
    (:require [site.xgo.frontend.main-page.sections.contacts       :as contacts]
              [site.xgo.frontend.main-page.sections.hero           :as hero]
              [site.xgo.frontend.main-page.sections.categories.api :as categories]
              [site.xgo.frontend.main-page.sections.models.api     :as models]
              [site.xgo.frontend.main-page.sections.types.api      :as types]))

;; -----------------------------------------------------------------------------
;; -----------------------------------------------------------------------------

(def hero       hero/view)

(def categories categories/view)

(def models     models/view)

(def types      types/view)

(def contacts   contacts/view)
