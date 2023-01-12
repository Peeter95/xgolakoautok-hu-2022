
(ns site.xgo.frontend.wrapper.subs
  (:require [re-frame.api :as r]))

(defn- use-wrap? [[website-page? current-route-id] [_]]
  (not (or website-page?
           (= :price-quote/route current-route-id))))

(r/reg-sub 
  :wrapper/use?
  :<- [:website-pages.handler/on-page?]
  :<- [:x.router/get-current-route-id]
  use-wrap?)