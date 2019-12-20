# Web-Crawler
Ein Web Crawler auf basis von Java, optimiert zur Analyse von Wordpress Webseiten.

## Verwendung:
Lege in der Crawl Klasse die zu untersuchende Webseite aus, starte dann die Main Methode.

Um den Crawler zu begrenzen, kann die MAX_DEPTH heruntergesetzt werden
-> Es wird nur ein Teil der Webseite gecrawlt.

## Derzeit verwendete Klassen
#### 1. Main
Startklasse

#### 2. Crawl:
Legt eine Liste mit allen relevanten ULR's an

#### 3. Coll_list:
Sammelt allgemeine Informationen

#### 4. Texts_un:
Speichert jeden mit <"p"> gekennzeichneten text in eine Zelle

#### 5. Texts_sum:
Speichert den Gesamten mit <"p"> gekennzeichneten text einer URl in eine Zelle

#### 6. Coll_date:
Speichert das Datum der URL

## Hilfsklassen:
#### 1. GetHTML:
Zieht das aktuelle HTML Dokument für eine URL

#### 2. Prepare_Excel:
legt das Excel Dokument an

## Nicht verwendete Klassen:
1. BasicWebCrawler
2. Coll_uniq (analysieren einer einzigen URL)
3. Bot
4. Crawl_iterative
5. Extractor
6. ALLE Excel Test Klassen