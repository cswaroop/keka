{:logging {:loggers [{:type :stdout :level :debug}
                     ;; {:type :remote :host "beast.local" :level :debug}
                     ;; {:type :file :file "caribou-logging.out" :level :debug}
                     ]}
 :app {:use-database true}
 :database {:classname    "org.h2.Driver"
            :subprotocol  "h2"
            :protocol     "file"
            :path         "./"
            :database     "keka_test"
            :host         "localhost"
            :subname      "file:keka_test"
            :user         "h2"
            :password     ""}
 :controller {:namespace "keka.controllers" :reload :always}
 :nrepl {:port 44444}
 :cache-templates :never}
