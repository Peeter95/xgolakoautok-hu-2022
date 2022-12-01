
# project-template

...

### Overview

The <strong>project-template</strong> is our Clojure/ClojureScript web application
template based on the Monoset kit-framework & the x5 web application engine.

### How to clone this repository from GitHub?

```
git clone git@github.com:monotech-hq/project-template.git
```

### How to install node modules?

```
npm install
```

### How to start site development?

```
clj -X:site.dev
```
After the build is ready, open the browser on: `localhost:3000`

### How to start app development?

```
clj -X:app.dev
```

After the build is ready, open the browser on: `localhost:3000/app`

### How to compile a JAR executable version?

```
clj -X:prod
```

To run the JAR file use this: `java -jar my-project-0-0-1.jar 3000`

To connect to Clojure Nrepl with Atom + Chlorine use port: `5555`

To connect Shadow-CLJS Nrepl with Atom + Chlorine use build: `app`
