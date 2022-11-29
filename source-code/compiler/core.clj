
(ns compiler.core
    (:require [compiler.config             :as config]
              [hf.depstar                  :as hf]
              [io.api                      :as io]
             ;[shadow.cljs.devtools.server :as server]
              [shadow.cljs.devtools.api    :as shadow]
              [x.core.api                  :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- init-compiler!
  ; @param (map) compiler-props
  [_]
  ; Cleaning the JS output directory ...
  (io/empty-directory! config/JS-CORE-PATH))

(defn- finish-compiler!
  ; @param (map) compiler-props
  [_]
  ; Increasing the build version number ...
  (x.core/update-build-version! :auto))

(defn- compile-js!
  ; @param (map) compiler-props
  ; {:js-builds (keywords in vector)}
  [{:keys [js-builds]}]
  (doseq [js-build js-builds]
         (println "Compiling:" js-build)
         (shadow/release js-build)))

(defn- compile-jar!
  ; @param (map) compiler-props
  ; {:java-config (map)
  ;   {:aot (boolean)
  ;    :jar (string)
  ;    :jar-type (keyword)
  ;    :main-class (class)}}
  [{:keys [java-config]}]
  (hf/jar java-config))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compile-app!
  ; @param (map) compiler-props
  ; {:java-config (map)
  ;   {:aot (boolean)
  ;    :jar (string)
  ;    :jar-type (keyword)
  ;    :main-class (class)}
  ;  :js-builds (keywords in vector)}
  ;
  ; @usage
  ; (compile-app! {:java-config {...}
  ;                :js-builds   [:my-buld]})
  [compiler-props]
  (init-compiler!   compiler-props)
  (compile-js       compiler-props)
  (compile-jar      compiler-props)
  (finish-compiler! compiler-props))
