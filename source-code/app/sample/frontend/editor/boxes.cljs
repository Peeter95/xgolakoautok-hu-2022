
(ns app.sample.frontend.editor.boxes
    (:require [app.components.frontend.api    :as components]
              [app.contents.frontend.api      :as contents]
              [app.storage.frontend.api       :as storage]
              [app.website-menus.frontend.api :as website-menus]
              [elements.api                   :as elements]
              [forms.api                      :as forms]
              [re-frame.api                   :as r]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- website-slogan-field
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [elements/text-field ::website-slogan-field
                            {:disabled?   editor-disabled?
                             :indent      {:top :m :vertical :s}
                             :label       :slogan
                             :placeholder :website-slogan-placeholder
                             :value-path  [:website-content :editor/edited-item :website-slogan]}]))

(defn- website-name-field
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [elements/text-field ::website-name-field
                            {:disabled?   editor-disabled?
                             :indent      {:top :m :vertical :s}
                             :label       :name
                             :placeholder :website-name-placeholder
                             :value-path  [:website-content :editor/edited-item :website-name]}]))

(defn- website-data-box
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [components/surface-box ::website-data-box
                               {:content [:<> [:div (forms/form-row-attributes)
                                                    [:div (forms/form-block-attributes {:ratio 100})
                                                          [website-name-field]]]
                                              [:div (forms/form-row-attributes)
                                                    [:div (forms/form-block-attributes {:ratio 100})
                                                          [website-slogan-field]]]
                                              [elements/horizontal-separator {:height :s}]]
                                :disabled? editor-disabled?
                                :indent    {:top :m}
                                :label     :website-data}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- website-logo-picker
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [storage/media-picker ::website-logo-picker
                             {:autosave?     true
                              :disabled?     editor-disabled?
                              :extensions    ["bmp" "jpg" "jpeg" "png" "webp"]
                              :indent        {:vertical :s}
                              :multi-select? false
                              :placeholder   "n/a"
                              :toggle-label  :select-image!
                              :value-path    [:website-content :editor/edited-item :website-logo]}]))

(defn- website-logo-box
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [components/surface-box ::website-logo-box
                               {:content [:<> [website-logo-picker]
                                              [elements/horizontal-separator {:height :s}]]
                                :disabled? editor-disabled?
                                :label     :website-logo}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- sidebar-menu-picker
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [website-menus/menu-picker ::sidebar-menu-picker
                                  {:autosave?     true
                                   :disabled?     editor-disabled?
                                   :indent        {:vertical :s}
                                   :multi-select? false
                                   :placeholder   "n/a"
                                   :value-path    [:website-content :editor/edited-item :sidebar-menu]}]))

(defn- sidebar-menu-box
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [components/surface-box ::sidebar-menu-box
                               {:content [:<> [:div (forms/form-row-attributes)
                                                    [:div (forms/form-block-attributes {:ratio 100})
                                                          [sidebar-menu-picker]]]
                                              [elements/horizontal-separator {:height :s}]]
                                :disabled? editor-disabled?
                                :indent    {:top :m}
                                :label     :sidebar-menu}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- header-menu-picker
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [website-menus/menu-picker ::header-menu-picker
                                  {:autosave?     true
                                   :disabled?     editor-disabled?
                                   :indent        {:vertical :s}
                                   :multi-select? false
                                   :placeholder   "n/a"
                                   :value-path    [:website-content :editor/edited-item :header-menu]}]))

(defn- header-menu-box
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [components/surface-box ::header-menu-box
                               {:content [:<> [:div (forms/form-row-attributes)
                                                    [:div (forms/form-block-attributes {:ratio 100})
                                                          [header-menu-picker]]]
                                              [elements/horizontal-separator {:height :s}]]
                                :disabled? editor-disabled?
                                :label     :header-menu}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- footer-menu-picker
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [website-menus/menu-picker ::footer-menu-picker
                                  {:autosave?     true
                                   :disabled?     editor-disabled?
                                   :indent        {:vertical :s}
                                   :multi-select? false
                                   :placeholder   "n/a"
                                   :value-path    [:website-content :editor/edited-item :footer-menu]}]))

(defn- footer-menu-box
  []
  (let [editor-disabled? @(r/subscribe [:file-editor/editor-disabled? :website-content.editor])]
       [components/surface-box ::footer-menu-box
                               {:content [:<> [:div (forms/form-row-attributes)
                                                    [:div (forms/form-block-attributes {:ratio 100})
                                                          [footer-menu-picker]]]
                                              [elements/horizontal-separator {:height :s}]]
                                :disabled? editor-disabled?
                                :indent    {:top :m}
                                :label     :footer-menu}]))
