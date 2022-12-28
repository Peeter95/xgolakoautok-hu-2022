
(ns project.xgo.backend.resources.lifecycles
    (:require [x.core.api :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(x.core/reg-lifecycles! ::lifecycles
  {:on-server-init [:x.core/add-resource! ::resource {:path "/" :root "/xgo"}]})
