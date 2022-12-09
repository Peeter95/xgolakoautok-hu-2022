
(ns project.sample.backend.router.default-handlers
    (:require [http.api                       :as http]
              [project.sample.backend.ui.main :as ui.main]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @constant (function)
; No method matched
(def METHOD-NOT-ALLOWED #(http/html-wrap {:body (ui.main/view %) :status 404}))

; @constant (function)
; Handler returned nil
(def NOT-ACCEPTABLE #(http/html-wrap {:body (ui.main/view %) :status 404}))

; @constant (function)
; No route matched
(def NOT-FOUND #(http/html-wrap {:body (ui.main/view %) :status 404}))

; @constant (function)
; The current route has no handler function
(def NO-HANDLER-DEFINED #(http/html-wrap {:body (ui.main/view %) :status 200}))

; @constant (function)
; The current route is restricted and the user is not authenticated
(def AUTHENTICATION-REQUIRED #(http/html-wrap {:body (ui.main/view %) :status 401}))
