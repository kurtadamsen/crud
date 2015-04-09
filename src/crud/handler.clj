(ns crud.handler
  ;  (:use [compojure.core :only (GET POST defroutes)])
  (:require [compojure.route :as route]
            [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :as resp]
            [ring.middleware.basic-authentication :refer :all]
            [ring.util.response :refer [resource-response response]]
    ;            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [crud.posts :as posts]
            [crud.views :as views]))


(defn authenticated? [name pass]
  (and (= name "kurt")
       (= pass "mess")))

(defroutes public-routes
           (GET "/" [] (views/main-page))
           (GET "/show/:id" [id] (views/show-post id))
           (route/resources "/"))

(defroutes protected-routes
           (GET "/admin" [] (views/admin-blog-page))
           (GET "/admin/add" [] (views/add-post))
           (POST "/admin/create" [& params]
                 (do (posts/create params)
                     (resp/redirect "/admin")))
           (GET "/admin/:id/edit" [id] (views/edit-post id))
           (POST "/admin/:id/save" [& params]
                 (do (posts/save (:id params) params)
                     (resp/redirect "/admin")))
           (GET "/admin/:id/delete" [id]
                (do (posts/delete id)
                    (resp/redirect "/admin"))))

(defroutes app-routes
           public-routes
           (wrap-basic-authentication protected-routes authenticated?)
           (route/not-found "Not Found"))

(def app
  (-> app-routes
      ;      (middleware/wrap-json-body)
      ;      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))
