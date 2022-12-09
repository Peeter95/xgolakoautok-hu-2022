
(ns project.sample.backend.router.lifecycles
    (:require [project.sample.backend.router.default-handlers :as router.default-handlers]
              [project.sample.backend.router.default-routes   :as router.default-routes]
              [x.core.api                                     :as x.core]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(x.core/reg-lifecycles! ::lifecycles
  {:on-server-init {:dispatch-n [[:x.router/set-default-handler! :authentication-required router.default-handlers/AUTHENTICATION-REQUIRED]
                                 [:x.router/set-default-handler! :method-not-allowed      router.default-handlers/METHOD-NOT-ALLOWED]
                                 [:x.router/set-default-handler! :no-handler-defined      router.default-handlers/NO-HANDLER-DEFINED]
                                 [:x.router/set-default-handler! :not-acceptable          router.default-handlers/NOT-ACCEPTABLE]
                                 [:x.router/set-default-handler! :not-found               router.default-handlers/NOT-FOUND]]}
   :on-server-boot {:dispatch-n [[:x.router/add-routes!                                   router.default-routes/ROUTES]]}})
