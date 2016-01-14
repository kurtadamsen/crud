(defproject crud "0.1.1"
  :description "Demo and sandbox"
  :url "http://example.com/FIXME"


  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.4"]
                 [org.clojure/java.jdbc "0.3.0-alpha5"]
                 [mysql/mysql-connector-java "5.1.35"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [ring-basic-authentication "1.0.5"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-defaults "0.1.5"]
                 [hiccup "1.0.5"]
                 [clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure]]]
  :plugins [[lein-ring "0.8.5"]]
  :user {:plugins [[org.clojars.hozumi/hiccup-bridge "1.0.0-SNAPSHOT"]]}
  :ring {:handler crud.handler/app}
  :main crud.handler
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}})
