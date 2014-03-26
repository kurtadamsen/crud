(defproject crud "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"


  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [org.clojure/java.jdbc "0.3.0-alpha5"]
                 [mysql/mysql-connector-java "5.1.25"]
                 [ring-basic-authentication "1.0.2"]
                 [hiccup "1.0.5"]
                 [clj-tagsoup "0.3.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :user {:plugins [[org.clojars.hozumi/hiccup-bridge "1.0.0-SNAPSHOT"]]}
  :ring {:handler crud.handler/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}})
