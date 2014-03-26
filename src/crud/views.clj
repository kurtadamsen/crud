(ns crud.views
  (:use [hiccup.page])
  (:require [hiccup.core :refer (html)]
            [hiccup.form :as f]
            [crud.posts :as posts]))

(defn layout [title & content]
  (html5
    [:head [:meta {:charset "utf-8"}] [:meta {:http-equiv "X-UA-Compatible", :content "IE=edge"}]
     [:meta {:name "viewport", :content "width=device-width, initial-scale=1"}]
     [:meta {:name "description", :content ""}] [:meta {:name "author", :content ""}]
     [:title title]
     (include-css "/css/bootstrap.min.css")
     (include-js "/js/json2.js" "/js/xpath.js")]
    [:body content
     (include-js "/js/jquery.js" "/js/bootstrap.js")])

  )

(defn nav-bar []
  [:div {:class "navbar navbar-inverse navbar-fixed-top", :role "navigation"}
   [:div {:class "container"}
    [:div {:class "navbar-header"}
     [:button {:type "button", :class "navbar-toggle", :data-toggle "collapse", :data-target ".navbar-collapse"}
      [:span {:class "sr-only"} "Toggle navigation"] [:span {:class "icon-bar"}] [:span {:class "icon-bar"}]
      [:span {:class "icon-bar"}]] [:a {:shape "rect", :class "navbar-brand", :href "#"} "Project name"]]
    [:div {:class "collapse navbar-collapse"}
     [:ul {:class "nav navbar-nav"}
      [:li {:class "active"} [:a {:shape "rect", :href "#"} "Home"]]
      [:li {} [:a {:shape "rect", :href "#about"} "About"]]
      [:li {} [:a {:shape "rect", :href "#contact"} "Contact"]]]]]]

  )
(defn main-page []
  (layout "My Blog"
    (nav-bar)
    [:div {:class "container"}
     [:p [:br] "&nbsp;"]]
    [:div {:class "container"}
     [:div {:class "starter-template"}
      [:h1 {} "Bootstrap starter template"]
      [:p {:class "lead"} "Use this document as a way to quickly start any new project."
       [:br {:clear "none"}] " All you get is this text and a mostly barebones HTML document."]]]
    ))



; Post is a map corresponding to a record from the database
(defn post-summary [post]
  (let [id (:id post)
        title (:title post)
        body (:body post)
        created_at (:created_at post)]

    [:div {:class "row"}
     [:div {:class "col-lg-6"}
      [:section [:h3 title]
       [:p [:small  (str "oprettet: " created_at)]]
       [:section body]
       [:div {:class "controls"}
        [:section.actions [:a {:class "btn btn-primary" :id "editpost" :href (str "/admin/" id "/edit")} "Ret"] " "
         [:a {:class "btn btn-danger" :id "delpost" :href (str "/admin/" id "/delete")} "Slet"] [:hr]]]]]]))



(defn admin-blog-page []
  (layout "My Blog - Administer Blog"
    (nav-bar)
    [:div {:class "container"}
     [:div {:class "content"}
      [:h1 "Administrer Blog"]
      [:h2 "Mine blogs"]
      [:a {:class "btn btn-primary" :id "addpost" :href "/admin/add"} "Tilføj ny"]
      (map #(post-summary %) (posts/all))]]))

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
            [:h2 (str "Edit Post " id)]
            (f/form-to {:role "form"} [:post "save"]
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

