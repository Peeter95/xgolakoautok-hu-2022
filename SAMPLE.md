
# sample-com-20xx

Sample.com website and CMS application written in Clojure/ClojureScript.

### To get this repo:

```
git clone git@github.com:monotech-projects/sample-com-20xx.git
```

### To install node modules:

```
npm install
```

### To start site development:

```
clj -X:site.dev
```

### To start app development:

```
clj -X:app.dev
```

### After the build is ready:

Open the browser on `localhost:3000` or `localhost:3000/app`

### To compile executable version:

```
clj -X:prod
```

### To run executable version:

```
java -jar sample-com-0-0-0-1.jar 3000
```

### To connect Nrepl:

To connect Clojure Nrepl with Atom + Chlorine use port `5555`

To connect Shadow-CLJS Nrepl with Atom + Chlorine use build `app`
