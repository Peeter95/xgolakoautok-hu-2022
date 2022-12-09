
(ns project.sample.backend.router.default-routes
    (:require [sente.api :as sente]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @constant (map)
(def ROUTES {:not-found         {:route-template "/not-found"
                                 :client-event   [:x.views/render-error-screen! :page-not-found]}
             :websocket/channel {:route-template "/chsk"
                                 :get  #(sente/ring-ajax-get-or-ws-handshake %)
                                 :post #(sente/ring-ajax-post                %)}})
