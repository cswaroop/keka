(ns keka.boot
  (:require [caribou.core :as caribou]
            [caribou.config :as config]
            [caribou.app.config :as app-config]))

(def local-config
  {:app {:use-database true
         :public-dir "public"
         :default-locale "global"
         :localize-routes ""}
   :assets {:dir "app/"
            :prefix nil
            :root ""}
   :aws {:bucket nil
         :credentials nil}
   :cljs {:root "resources/cljs"
          :reload false
          :options {:output-to "resources/public/js/app/keka.js"
                    :output-dir "resources/public/js/app/out"
                    :pretty-print true}
          :brepl {:listen false
                  :port 44994
                  :path "repl"}}
   :controller {:namespace "keka.controllers"
                :reload true}
   :database {:classname    "org.postgresql.Driver"
              :subprotocol  "postgresql"
              :host         "localhost"
              :database     "keka_production"
              :user         "postgres_user"
              :password     "postgres_password"}
   :error {:show-stacktrace false
           :catch-exceptions true}
   :field {:namespace "keka.fields"
           :slug-transform [[#"['\"]+" ""]
                            [#"[_ \\/?%:#^\[\]<>@!|$&*+;,.()]+" "-"]
                            [#"^-+|-+$" ""]]}
   :hooks {:namespace "keka.hooks"}
   :index {:path "caribou-index"
           :default-limit 1000}
   :logging {:loggers [{:type :stdout :level :debug}]}
   :nrepl {:port nil}
   :query {:enable-query-cache  false
           :query-defaults {}}
   :template {:cache-strategy :never}})

(defn boot
  []
  (-> (app-config/default-config)
      (config/merge-config local-config)
      (config/config-from-environment)
      (config/merge-db-connection {:connection "DATABASE_URL"})
      (config/process-config)
      (caribou/init)))
