
(ns compiler.core
  (:require [hf.depstar]
            [compiler.helpers :as helpers]
            [shadow-cljs.api  :as shadow-cljs]
            [x.core.api       :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- compile-js!
  ; @param (map) compiler-props
  ; {:shadow-builds (keywords in vector)}
  ; @param (string) build-version
  [{:keys [shadow-builds]} _]
  (doseq [shadow-build shadow-builds]
         (shadow-cljs/init-output-directory! shadow-build))
  (doseq [shadow-build shadow-builds]
         (println "project-compiler - compiling JS build:" shadow-build)
         (shadow-cljs/release shadow-build)))

(defn- compile-jar!
  ; @param (map) compiler-props
  ; {:java-config (map)
  ;   {:aot (boolean)
  ;    :jar-type (keyword)
  ;    :main-class (class)}}
  ; @param (string) build-version
  [{:keys [java-config]} build-version]
  (let [output-filename (helpers/jar-filename build-version)]
    (println "project-compiler - compiling JAR file:" output-filename)
    (try (-> java-config (assoc :jar output-filename)
                         (hf.depstar/jar))
         (catch Exception e (println e)))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn compile-app!
  ; @param (map) compiler-props
  ; {:java-config (map)
  ;   {:aot (boolean)
  ;    :jar-type (keyword)
  ;    :main-class (class)}
  ;  :shadow-builds (keywords in vector)}
  ;
  ; @usage
  ; (compile-app! {:java-config {...}
  ;                :shadow-builds [:my-buld]})
  [compiler-props]
  (let [build-version (x.core/update-build-version! :auto)]
    (x.core/check-installation!)
    (compile-js!  compiler-props build-version)
    (compile-jar! compiler-props build-version)
    (let [output-filename (helpers/jar-filename build-version)]
         (println "project-compiler -" output-filename "compiled")
         (println "project-compiler - exiting ..."))))