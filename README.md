# Jabberjay [![Build Status](https://travis-ci.org/vbauer/jabberjay.svg)](https://travis-ci.org/vbauer/jabberjay) [![Dependencies Status](http://jarkeeper.com/vbauer/jabberjay/status.png)](http://jarkeeper.com/vbauer/jabberjay)

<img src="misc/jabberjay.png" width="300" height="300" align="right" style="margin-left: 15px" />

> Jabberjays are a type of muttation that consist of all male birds that were created in the Capitol labs to spy on enemies and rebels of the Capitol. Jabberjays had the ability to memorize and repeat entire human conversations, and were used as spies, to gather words and information. - The Hunger Games

**Jabberjay** is a Clojure micro-framework for creating Jabber bots.

Programming your own Jabber bot can be helpful and fun.
You can use your Jabber bot to provide information about some running systems or to make your services interact with users.


## Configuration

Default configuration is stored in project resources (config.edn) in [EDN format](https://github.com/edn-format/edn):
```edn
{:jabber {:username "Fill me up!"
          :password "Fill me up!"
          :host "Fill me up!"
          :domain "Fill me up!"}
 :watchdog {:folder "scripts"
            :delay 1000}}
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
When `:delay` parameter is defined, then FS watchdog will check changes and reload scripts in runtime.
It could be useful in development mode, but you can switch if off in production mode (using `:delay` equals 0).

It is necessary to follow several rules during Jabber command development:

* Each command should be implemented in separate file with namespace.
* Command name is the file name (ignoring case).
* It is possible to use all artifacts from Jabberjay's classpath.
* Last function in script file is the entry point.


### Example

This simple command always returns "Hello!", when client sends "Hi" ("HI", "hi", "hI"):

```clojure
(ns hi)

(defn init
  "Simple module for greating"
  [msg] "Hello!")
```


## Building from source

To build executable JAR from sources:
```bash
lein do clean, uberjar
```

To run the bot, simply do:
```bash
java -jar target/jabberjay.jar &
```


## License

Copyright © 2015 Vladislav Bauer

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.


See [LICENSE](LICENSE) for more details.
