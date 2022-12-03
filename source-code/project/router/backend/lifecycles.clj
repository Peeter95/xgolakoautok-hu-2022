
(ns project.router.backend.lifecycles
    (:require [project.router.backend.default-handlers :as default-handlers]
              [project.router.backend.default-routes   :as default-routes]
              [x.core.api                              :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(x.core/reg-lifecycles! ::lifecycles
  {:on-server-init {:dispatch-n [[:x.router/set-default-handler! :authentication-required default-handlers/AUTHENTICATION-REQUIRED]
                                 [:x.router/set-default-handler! :method-not-allowed      default-handlers/METHOD-NOT-ALLOWED]
                                 [:x.router/set-default-handler! :no-handler-defined      default-handlers/NO-HANDLER-DEFINED]
                                 [:x.router/set-default-handler! :not-acceptable          default-handlers/NOT-ACCEPTABLE]
                                 [:x.router/set-default-handler! :not-found               default-handlers/NOT-FOUND]
                                 [:x.router/add-routes!                                   default-routes/ROUTES]]}})
