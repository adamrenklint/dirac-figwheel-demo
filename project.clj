(defproject adamrenklint/dirac-figwheel-demo "0.1.0-SNAPSHOT"
  :description "Example of Figwheel and Dirac"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/tools.nrepl "0.2.13"]
                 [binaryage/devtools "0.9.9"]
                 [binaryage/dirac "1.2.30"]
                 [figwheel "0.5.15"]]
  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-shell "0.5.0"]
            [lein-cooper "1.2.2"]
            [lein-figwheel "0.5.15"]]
  :source-paths ["src"]
  :clean-targets ^{:protect false} ["target"
                                    "resources/public/.compiled"
                                    "resources/demo-node/.compiled"]
  :cljsbuild {:builds {}}                                                                                                     ; prevent https://github.com/emezeske/lein-cljsbuild/issues/413

  :profiles {:repl
             {:repl-options {:port             8230
                             :nrepl-middleware [dirac.nrepl/middleware]
                             :init             (do
                                                 (require 'dirac.agent)
                                                 (dirac.agent/boot!))}}
             :figwheel-config
             {:figwheel  {:server-port    7111
                          :server-logfile ".figwheel/demo.log"
                          :repl           false}
              :cljsbuild {:builds
                          {:demo
                           {:figwheel true}}}}
             :figwheel-nrepl
             [:figwheel-config
              ; following https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl
              {:dependencies [[figwheel-sidecar "0.5.15"]]
               :repl-options {:init ^:replace (do
                                                (require 'dirac.agent)
                                                (use 'figwheel-sidecar.repl-api)
                                                (start-figwheel!
                                                  {:figwheel-options {:server-port 7111
                                                                      :css-dirs ["resources/public/styles"]}
                                                   :build-ids ["demo"]
                                                   :all-builds [{:id "demo"
                                                                 :figwheel {:on-jsload "dirac-figwheel-demo.core/on-load"}

                                                                 :source-paths ["src"]
                                                                 :compiler {:output-to "resources/public/.compiled/demo/demo.js"
                                                                                :output-dir "resources/public/.compiled/demo"
                                                                                :asset-path ".compiled/demo"
                                                                                :preloads ['devtools.preload 'dirac.runtime.preload]
                                                                                :main 'dirac-figwheel-demo.core
                                                                                :optimizations :none
                                                                                :source-map    true}}]})
                                                (dirac.agent/boot!)
                                                #_(cljs-repl))}}]
             :checkouts
             {:checkout-deps-shares ^:replace [:source-paths
                                               :test-paths
                                               :resource-paths
                                               :compile-path
                                               #=(eval leiningen.core.classpath/checkout-deps-paths)]
              :cljsbuild {:builds
                          {:democ
                           {:source-paths ["checkouts/cljs-devtools/src/lib"
                                           "checkouts/dirac/src/runtime"]}}}}}
  :aliases {"dev" ["with-profile" "+repl,+checkouts,+figwheel-nrepl" "repl"]})
