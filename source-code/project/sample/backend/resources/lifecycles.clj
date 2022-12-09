
(ns project.sample.backend.resources.lifecycles
    (:require [x.core.api :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(x.core/reg-lifecycles! ::lifecycles
  {:on-server-init [:x.core/add-resources! {::public {:path "/" :root "/public"}
                                            ::sample {:path "/" :root "/sample"}}]})
