# crud

Sandkasse CRUD projekt der benytter clojure, ring, compojure, hiccup, rest, mysql, bootstrap, jQuery

Ideen er at have et web.project, hvor man bare kan trykke f5 i browseren efter en kodeændring. Ingen manuel compile eller build.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

Remeber to start the MySQLServer first

To start a web server for the application, run:

    lein ring server

or

    lein run <port>
    
To start server i repl

   (use 'crud.handler)
   (-main)

## License

Copyright © 2014 FIXME

used to learn github Actions