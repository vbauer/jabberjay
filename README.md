# JabberJay

<img src="misc/jabberjay.png" width="300" height="300" align="right" style="margin-left: 15px" />

**JabberJay** is a Clojure micro-framework for creating Jabber bots.

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

TODO


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
