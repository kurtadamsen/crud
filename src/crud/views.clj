(ns crud.views
  (:use [hiccup.page :refer :all])
  (:require [hiccup.core :refer (html)]
            [hiccup.form :as f]
            [crud.posts :as posts]))

(defn layout [title & content]
  (html5
    [:head [:meta {:charset "utf-8"}] [:meta {:http-equiv "X-UA-Compatible", :content "IE=edge"}]
     [:meta {:name "viewport", :content "width=device-width, initial-scale=1"}]
     [:meta {:name "description", :content ""}] [:meta {:name "author", :content ""}]
     [:title title]
     (include-css "/css/bootstrap.min.css" "/css/crud.css")
     (include-js "/js/json2.js" "/js/xpath.js")]
    [:body content
     [:div {:class "container"}
      [:hr {}] [:footer {} [:p {} "© YouSee 2014"]]]
     (include-js "/js/jquery.js" "/js/bootstrap.min.js" "/js/crud.js")])

  )

(defn nav-bar []
[:div
  [:br] [:br] [:br]

  [:div {:class "navbar navbar-inverse navbar-fixed-top", :role "navigation"}
   [:div {:class "container"}
    [:div {:class "navbar-header"}
     [:button {:type "button", :class "navbar-toggle", :data-toggle "collapse", :data-target ".navbar-collapse"}
      [:span {:class "sr-only"} "Toggle navigation"] [:span {:class "icon-bar"}] [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]] [:a {:shape "rect", :class "navbar-brand", :href "/"} "CRUD eksempel"]]
    [:div {:class "collapse navbar-collapse"}
     [:ul {:class "nav navbar-nav"}
      [:li {} [:a {:shape "rect", :href "/"} "Hjem"]]
      [:li {} [:a {:shape "rect", :href "/admin"} "Admin"]]]]]]
 ]
  )




; Post is a map corresponding to a record from the database
(defn post-summary [post]
  (let [id (:id post)
        title (:title post)
        body (:body post)
        created_at (:created_at post)]
    [:div {:class "col-6 col-sm-6 col-lg-4"}
     [:h3 {} title]
     [:div {:class "post-div"}
      [:p {} body]]
     [:p {}]
     [:p [:small (str "oprettet: " created_at)]]
     [:div {:class "controls"}
      [:section.actions [:a {:class "btn btn-primary" :id "editpost" :href (str "/admin/" id "/edit")} "Ret"] " "
       [:a {:class "btn btn-danger" :id "delpost" :href (str "/admin/" id "/delete")} "Slet"] [:hr]]]]))

(defn post-summary-show [post]
  (let [id (:id post)
        title (:title post)
        body (:body post)
        created_at (:created_at post)]
    [:div {:class "col-6 col-sm-6 col-lg-4"}
     [:h2 {} title]
     [:div {:class "post-div"}
      [:p {} body]]
     [:p {}]
     [:p [:small (str "oprettet: " created_at)]]
     [:p {} [:a {:shape "rect", :class "btn btn-default", :href (str "/show/" id), :role "button"} "Vis mere »"]]
     ]))



(defn main-page []
  (layout "Min Blog"
          (nav-bar)
          [:div {:class "container"}
           ]
          [:div {:class "container"}
           [:div {:class "starter-template"}
            [:h1 {} "Blog eksempel"]
            [:p {:class "lead"} "Dette er et mini eksempel på brug af Clojure, Hiccup, rest, Boorstrap og mysql."
             [:br {:clear "none"}] "Kan bruges som start på et nyt webprojekt, hvor webserver ikke skal genstartes efter kodeændringer"]]]
          [:div {:class "container"}
           [:div {:class "row"}
            (map #(post-summary-show %) (posts/all))]]))

(defn admin-blog-page []
  (layout "Min Blog - Administrer Blog"
          (nav-bar)
          [:div {:class "container"}
           [:div {:class "content"}
            [:h1 "Administrer Blog"]
            [:h2 "Mine blogs"]
            [:a {:class "btn btn-success" :id "addpost" :href "/admin/add"} [:span {:class "glyphicon glyphicon-plus" :aria-hidden "true"}] " Tilføj ny"
             ]
            [:div {:class "row"}
             (map #(post-summary %) (posts/all))]]]))

(defn add-post []
  (layout "My Blog - Add Post"
          (nav-bar)
          [:div {:class "container"}
           [:div {:class "content"}
            [:div {:class "row"} [:br] [:br] [:br]]
            [:div {:class "row"}
             [:h2 "Ny post"]]
            [:div {:class "row"}
             [:div {:class "col-lg-6"}
              (list
                (f/form-to {:role "form"} [:post "/admin/create"]
                           (f/label {} "title" "Title")
                           (f/text-field {:class "form-control"} "title") [:br]
                           (f/label {} "body" "Indhold") [:br]
                           (f/text-area {:rows 20 :class "form-control"} "body") [:br]
                           (f/submit-button {:class "btn btn-primary"} "Gem")))]]
            [:div {:class "row"} [:br] [:br] [:br]]
            [:div {:class "row"}
             [:div {:class "col-lg-6"}
              [:a {:class "btn btn-default" :id "tilbage" :href "/admin"} "Tilbage"]]]]]))

(defn edit-post [id]
  (layout "My Blog - Edit Post"
          (nav-bar)
          [:div {:class "container"}
           [:div {:class "content"}
            [:div {:class "row"} [:br] [:br] [:br]]
            [:div {:class "row"}
             [:div {:class "col-lg-6"}
              (list
                (let [post (posts/get id)]
                  (f/form-to {:role "form"} [:post "save"]
                             [:h2 (str "Edit Post " id)]
                             [:div {:class "form-group"}
                              (f/label "title" "Titel")
                              (f/text-field {:class "form-control"} "title" (:title post))]
                             [:div {:class "form-group"}
                              (f/label {} "body" "Indhold")
                              (f/text-area {:rows 20 :class "form-control"} "body" (:body post))]
                             (f/submit-button {:class "btn btn-primary"} "Gem"))))]]
            [:div {:class "row"} [:br] [:br] [:br]]
            [:div {:class "row"}
             [:div {:class "col-lg-6"}
              [:a {:class "btn btn-default" :id "tilbage" :href "/admin"} "Tilbage"]]]]]))

(defn show-post [id]
  (layout "Min Blog - Show Post"
          (nav-bar)
          [:div {:class "container"}
           [:div {:class "content"}
            [:div {:class "row"} [:br] [:br] [:br]]
            [:div {:class "row"}
             (list
               (let [post (posts/get id)]
                 (f/form-to {:role "form"} [:post "save"]
                            [:div {:class "panel panel-info"}
                             [:div {:class "panel-heading"}
                              [:h3 {:class "panel-title"} (:title post)]]
                             [:div {:class "panel-body"}
                              [:p {} (:body post)]]]
                            )))]
            [:div {:class "row"}
             [:div {:class "col-lg-6"}
              [:a {:class "btn btn-default" :id "tilbage" :href "/"} "Tilbage"]]]]]))



