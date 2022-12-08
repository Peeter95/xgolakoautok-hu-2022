
(ns site.sample.frontend.main-page.sections.contacts)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- contacts
  []
  [:div {:class :mt-section-body}])

(defn view
  []
  [:section {:id :mt-contacts}
            [contacts]])
