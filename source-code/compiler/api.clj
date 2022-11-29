
(ns compiler.api
    (:require [compiler.core :as core])
    (:gen-class))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; compiler.core
(def compile-app! core/compile-app!)
