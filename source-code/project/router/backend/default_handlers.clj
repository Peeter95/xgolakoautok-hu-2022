
(ns project.router.backend.default-handlers
    (:require [http.api               :as http]
              [project.ui.backend.api :as ui]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @constant (function)
; No method matched
(def METHOD-NOT-ALLOWED #(http/html-wrap {:body (ui/main %) :status 404}))

; @constant (function)
; Handler returned nil
(def NOT-ACCEPTABLE #(http/html-wrap {:body (ui/main %) :status 404}))

; @constant (function)
; No route matched
(def NOT-FOUND #(http/html-wrap {:body (ui/main %) :status 404}))

; @constant (function)
; The current route has no handler function
(def NO-HANDLER-DEFINED #(http/html-wrap {:body (ui/main %) :status 200}))

; @constant (function)
; The current route is restricted and the user is not authenticated
(def AUTHENTICATION-REQUIRED #(http/html-wrap {:body (ui/main %) :status 401}))
