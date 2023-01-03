
(ns compiler.helpers
  (:require [io.api        :as io]
            [normalize.api :as normalize]
            [string.api    :as string]
            [x.core.api    :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn jar-filename
  ; @param (string) build-version
  ;
  ; @usage
  ; (jar-filename "0.0.0.1")
  ;
  ; @example
  ; (jar-filename "0.0.0.1")
  ; =>
  ; "my-app-0-0-0-1.jar"
  [build-version]
  ; The output JAR file's name is automatically generated from the application's
  ; title (what read from the 'app-config.edn' config file) and the current
  ; build version.
  ;
  ; Let's suppose you named your application as "My application" and the latest
  ; build-version is "0.4.2".
  ; The compiler ...
  ; ... increases the build-version and appends it to the name,
  ; ... normalizes the result.
  ; The output will look like this: "my-application-0-4-3.jar"
  (letfn [(get-app-title [] (-> x.core/APP-CONFIG-FILEPATH io/read-edn-file :app-title))]
         (-> (get-app-title)
             (string/use-placeholder "untitled")
             (str "-" build-version)
             (string/replace-part  "." "-")
             (normalize/clean-text)
             (str ".jar"))))