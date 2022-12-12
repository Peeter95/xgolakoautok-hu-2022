
(ns app.sample.frontend.api
    (:require [app.sample.frontend.editor.effects]
              [app.sample.frontend.editor.lifecycles]
              [app.sample.frontend.lifecycles]
              [app.sample.frontend.editor.boxes :as editor.boxes]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; app.sample.frontend.editor.boxes
(def website-data-box editor.boxes/website-data-box)
(def website-logo-box editor.boxes/website-logo-box)
(def header-menu-box  editor.boxes/header-menu-box)
(def sidebar-menu-box editor.boxes/sidebar-menu-box)
(def footer-menu-box  editor.boxes/footer-menu-box)
