# Jabberjay [![Build Status](https://travis-ci.org/vbauer/jabberjay.svg)](https://travis-ci.org/vbauer/jabberjay)

<img src="misc/jabberjay.png" width="300" height="300" align="right" style="margin-left: 15px" />

> Jabberjays are a type of muttation that consist of all male birds that were created in the Capitol labs to spy on enemies and rebels of the Capitol. Jabberjays had the ability to memorize and repeat entire human conversations, and were used as spies, to gather words and information. - The Hunger Games

**Jabberjay** is a Clojure micro-framework for creating [Jabber](http://www.jabber.org/faq.html#jabber) bots.

Programming your own Jabber bot can be helpful and fun.
You can use your Jabber bot to provide information about some running systems or to make your services interact with users.


## Configuration

Default configuration is stored in project resources (config.edn) in [EDN format](https://github.com/edn-format/edn):
```edn
{:jabber {:username "Fill me up!"
          :password "Fill me up!"
          :host "Fill me up!"
          :domain "Fill me up!"}
 :scripts {:folder "scripts"
           :watchdog 1000}}
```

Before using, it is necessary to configure the following parameters:
* :username (ex: "jabberjay@gmail.com")
* :password (ex: "iddqd")
* :host (ex: "talk.google.com")
* :domain (ex: "gmail.com")

It is also possbile to use user-base configuration file, which should be located in `~/jabberjay.edn`.
All parameters from this file override default config parameters.


## Development

To develop Jabber command, you need to create Clojure file in the script `:folder`.
When `:watchdog` parameter is defined, then FS watchdog will check changes and reload scripts in runtime each *N* milliseconds.
It could be useful in development mode, but you can switch if off in production mode (using `:watchdog` equals 0).

It is necessary to follow several rules during Jabber command development:

* Each command should be implemented in separate script file (with the same namespace).
* Command name is the file name (ignoring case).
* It is possible to use all artifacts from Jabberjay's classpath.
* Last function in the script file is the entry point.
* Result of the last function is the answer message, it will be sent to user.


### Script context

Each script receives context parameters, for example:

```clojure
{:subject nil
 :from "bauer.vlad@gmail.com/gmail.3167A379"
 :to "jabberjay@gmail.com"
 :thread nil
 :error nil
 :packet-id "5A19D18217BBD43_1"
 :type :chat
 :from-name "bauer.vlad@gmail.com"
 :body "Hi Bot"
 :text "Bot"}
```


### Example

This simple command always returns "Hello, `your jabber account`", when client sends "Hi" ("HI", "hi", "hI"):

```clojure
(ns hi)

(defn init
  "Simple module for greating"
  [data]
  (str "Hello, " (or (:from-name data)
                     (:from data))))
```

Another example: [scripts/weather.clj](scripts/weather.clj).


## Usage

To grab a JAR from [latest release](https://github.com/vbauer/jabberjay/releases/latest):
```bash
curl -L -O https://github.com/vbauer/jabberjay/releases/download/0.2.0/jabberjay.jar
```

To build executable JAR from sources:
```bash
lein do clean, uberjar
```

To run the bot, simply do:
```bash
java -jar target/jabberjay.jar &
```


## Might also like

* [lein-plantuml](https://github.com/vbauer/lein-plantuml) - a Leiningen plugin for generating UML diagrams using PlantUML.
* [lein-asciidoctor](https://github.com/asciidoctor/asciidoctor-lein-plugin) - a Leiningen plugin for generating documentation using Asciidoctor.
* [lein-jshint](https://github.com/vbauer/lein-jshint) - a Leiningen plugin for running javascript code through JSHint.
* [lein-jslint](https://github.com/vbauer/lein-jslint) - a Leiningen plugin for running javascript code through JSLint.
* [lein-coffeescript](https://github.com/vbauer/lein-coffeescript) - a Leiningen plugin for running CoffeeScript compiler.
* [lein-typescript](https://github.com/vbauer/lein-typescript) - a Leiningen plugin for running TypeScript compiler.
* [coderwall-clj](https://github.com/vbauer/coderwall-clj) - a tiny CoderWall client for Clojure.


## License

Copyright © 2015 Vladislav Bauer

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.


See [LICENSE](LICENSE) for more details.
