# dirac-figwheel-demo

Example project of [Dirac](https://github.com/binaryage/dirac) and [Figwheel](https://github.com/bhauman/lein-figwheel) integration. Forked from https://github.com/binaryage/dirac-sample.

* Install Chrome Canary
* Install [Dirac DevTools extension](https://chrome.google.com/webstore/detail/dirac-devtools/kbkdngfljkchidcjpnfcgcokkbhlkogi)
* Start Chrome Canary with:
```
/Applications/Google\ Chrome\ Canary.app/Contents/MacOS/Google\ Chrome\ Canary \
  --remote-debugging-port=9222 \
  --no-first-run
```
* Run `lein dev`
* Open `http://0.0.0.0:7111` in browser
* Open Dirac Devtools with CMD-SHIFT-I
* In Atom, connect nREPL on port 8230
* Eval `(dirac :join)`
* Eval `(dirac :ls)`
* Eval `(dirac :switch 2)`
* Eval `(in-ns 'dirac-figwheel-demo.demo)`
* Eval `(+ 1 2)`
* Eval `(run-breakpoint-demo)`
