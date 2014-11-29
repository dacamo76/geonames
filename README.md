# geonames

Combines current city and country info obtained from [GeoNames] (http://geonames.org).
Currently only prints city, state, country, latitude and longitude as CSV to STDOUT.

```
$ lein run | head -n5
El Tarter,Canillo,Andorra,42.57952,1.65362
Sant Julià de Lòria,Sant Julià de Loria,Andorra,42.46372,1.49129
Pas de la Casa,Encamp,Andorra,42.54277,1.73361
Ordino,Ordino,Andorra,42.55623,1.53319
les Escaldes,Escaldes-Engordany,Andorra,42.50729,1.53414
```

As of November 28 2014, there are 143,356 cities in the database.

## Installation

    $ git clone https://github.com/dacamo76/geonames.git

## Usage

    $ lein run > cities.csv
    $ grep -i "winston-salem" cities.csv
    Winston-Salem,North Carolina,United States,36.09986,-80.24422

## Sources

Each time the program runs it downloads the latest export dumps from the [GeoNames Download Server] (http://download.geonames.org/export/dump/). The following files are used:

File name | Approx. size | Description
------------- | -------- | ------
cities1000.zip      | 6.7M |  Main geoname table. All cities with a population greater than 1000
admin1CodesASCII.txt  | 135K | Name of administrative divisions (In US this is state)
countryInfo.txt  | 31K | Name of countries

The admin1CodesASCII and countryInfo files are used to map the codes found in the main geoname table to their text representation.


### Bugs

Probably many

## License

Copyright © 2013 Daniel Alberto Cañas

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
