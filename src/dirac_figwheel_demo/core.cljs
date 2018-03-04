(ns dirac-figwheel-demo.core
  (:require [dirac.runtime :as dirac]))

(enable-console-print!)

(println "tag:" (dirac/get-tag))

(defn hello! [greetings]
  (println (str "Hello, " (or greetings "there") "!")))

(defn breakpoint-demo [count]
  (let [numbers (range count)]
    (doseq [number numbers]
      (let [is-odd? (odd? number)
            line (str "number " number " is " (if is-odd? "odd" "even"))]
        (js-debugger)
        (println line)))))

(defn ^:export run-breakpoint-demo []
  (println "calling breakpoint-demo:")
  (breakpoint-demo 3))

(comment
 (hello! "world")
 (run-breakpoint-demo))
