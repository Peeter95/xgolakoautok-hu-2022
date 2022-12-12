
(ns app.sample.frontend.editor.views
    (:require [app.common.frontend.api          :as common]
              [app.components.frontend.api      :as components]
              [app.contents.frontend.api        :as contents]
              [app.sample.frontend.editor.boxes :as editor.boxes]
              [elements.api                     :as elements]
              [layouts.surface-a.api            :as surface-a]
              [re-frame.api                     :as r]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- basic-data
  []
  [:<> [editor.boxes/sample-box]])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- view-selector
  []
  (let [current-view-id @(r/subscribe [:x.gestures/get-current-view-id :website-content.editor])]
       (case current-view-id :basic-data [basic-data])))

(defn- body
  []
  [common/file-editor-body :website-content.editor
                           {:content-path [:website-content :editor/edited-item]
                            :form-element [view-selector]}])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- header
  []
  [common/file-editor-header :website-content.editor
                             {:label      :website-content
                              :crumbs     [{:label :app-home :route "/@app-home"}
                                           {:label :website-content}]
                              :menu-items [{:label :basic-data :change-keys [:sample]}]}])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn view
  ; @param (keyword) surface-id
  [surface-id]
  [surface-a/layout surface-id
                    {:content [:<> [header]
                                   [body]]}])
