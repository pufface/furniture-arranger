Furniture Arranger
==================

Usage
-----
    java -jar furniture-arranger-0.1.jar [-i <input_file>] [-o <output_file>]

Arguments
---------
    -i <input_file>   Read inputs from file. Optional, systemIn is used by default.
    -o <output_file>  Write outputs to file. Optional, systemOut is used by default.

Example input
-------------
    5,6
    ..###.
    .####.
    ######
    ######
    ...###

    A2####
    B3.#.###.#.

Shortcut
--------
    EOF   CTRL + D

Build
-----
    mvn eclipse:eclipse  //create Eclipse project
    mvn test             //run test
    mvn clean package    //create Jar


Assignment
----------
```

UVOD
""""

Zadanie vyzaduje zakladne znalosti Javy, konkretne balikov java.lang, java.util a java.io. Je vhodne riesenie aj testovat, ci uz pomocou vlastneho programu s main metodou, alebo pomocou kniznic JUnit alebo TestNG. Cielom zadania je zistit uroven kvality kodu, dodrziavanie konvencii, OOP, volbu vhodnych mien pre komponenty a premenne, citatelnost kodu...

IZBY A NABYTOK
""""""""""""""

Program riesi zistenie vsetkych moznych kombinacii, ako napratat zadany nabytok do zadanej izby. Rozmery izby aj nabytku su zadane v rovnakych jednotkach nazvanych "pole", cim je mysleny 1 stvorec (napr. 1m stvorcovy, ale nezalezi na tom).

FORMAT VSTUPOV
""""""""""""""

Tvary izby a nabytku su zadane dvomi roznymi formatmi (vystup z roznych externych informacnych systemov).

Izba moze mat nepravidelny tvar a bude zadana ako viacriadkovy text v nasledovnom formate:

1. riadok: dlzka,sirka (vzdy vacsie ako 0)
2. a dalsie riadky: kazdy riadok ma prave "sirka" znakov, pocet riadkov je rovny "dlzke", znak # reprezentuje pole vnutri izby (kam je mozne umiestnit nabytok), znak . pole mimo izby

Priklad izby:
5,6
..###.
.####.
######
######
...###

Nabytok sa vyraba v roznych vzoroch oznacenych prave jednym velkym pismenom, na vstupe nikdy nebude viacej ako 26 roznych typov nabytku. Vsetky typy nabytku budu zadane v ramci jedneho vstupu (retazec, subor), v ktorom jeden kus nabytku bude opisany jednym riadkom nasledovne:
1. znak oznacuje typ nabytku
2. znak je cislo 1-9, ktore oznacuje maximalnu sirku nabytku
3. a dalsie znaky (. a #, ako pri izbe) opisuju tvar nabytku po jednotlivych "riadkoch" o sirke urcenej znakom 2, pricom jednotlive riadky nie su od seba oddelene; pocet tychto znakov je delitelny sirkou nabytku, dlzka daneho nabytku vyplyva z celkovej dlzky takehoto retazca

Priklad nabytkov:
A2####
B3.#.###.#.

Nabytok by v plane vyzeral nasledovne:
A:
##
##
B:
.#.
###
.#.

PROBLEM NA RIESENIE
"""""""""""""""""""

Pri danych vstupoch je potrebne zostavit zoznam vsetkych rieseni, ako dostat zadany nabytok do zadanej izby, pricom kazdy typ nabytku je potrebne umiestnit prave raz. Nabytok sa nesmie prekryvat navzajom polami oznacenymi ako # a sucasne musia byt vsetky polia nabytku # vnutri izby (t.j. na poliach # danej izby).

Nabytok sa pri umiestnovani do izby neotaca.

Format vystupneho zoznamu:
1. na kazdom riadku sa nachadza prave jedno riesenie
2. v ramci riadku sa opakuje sekvencia:
  - pismeno oznacujuce typ nabytku
  - suradnice daneho nabytku na plane izby v zatvorkach, pricom vlavo hore je (0,0) - suradnice su vztiahnute k lavemu hornemu rohu zadania nabytku
  - medzera ako oddelovac (nesmie byt za poslednym umiestnenym nabytkom v ramci jedneho riesenia)

Priklad neuplneho vystupu pre predchadzajuce vstupy:
A(0,2) B(1,3)
A(0,3) B(1,0)
...atd

Poradie rieseni nie je dolezite. Na prvom riadku je navyse ukazka, ze zadanie nabytkov A a B sa na plane prekryva, ale ich polia # sa neprekryvaju, takze je to v poriadku.

Ak neexistuje riesenie, vystup bude prazdny.

POZIADAVKY NA RIESENIE
""""""""""""""""""""""

Pouzite Java SE 7 alebo 8, vysledny projekt by mal byt otvoritelny v IDE Eclipse (alebo IntelliJ IDEA Community Edition).

Struktura projektu je lubovolna (moze byt Maven Standard Directory Layout, ale nie je to vyzadovane).

V dodanom kode bude posudzovane pouzitie zrozumitelnych mien (slovenske alebo anglicke, podla vyberu), pomenovavacie konvencie jazyka Java, struktura kodu, rozumna dlzka metod, atd. Zvolte spravnu mieru objektovo-orientovaneho pristupu (OOP), najma pri volbe vhodnych struktur pre reprezentaciu izby, nabytku a jednotlivych stavov pocas hladania riesenia. Je odporucane napisat si jednoduche testy (JUnit, TestNG).

Okrem samotneho zdrojoveho kodu je potrebne, aby projekt bol zbuildovatelny a spustitelny bez IDE bud v OS Windows alebo Linux (staci jedno). Na testovacom prostredi bude nastavena premenna prostredia JAVA_HOME na pozadovane JDK 6 alebo 7 (podla vyberu dodavatela). Build moze byt realizovany cez Maven alebo Ant - v takom pripade by nemalo zalezat na konkretnom OS. Je mozne aj pouzit javac a prikazy OS, predpripravene ako BAT/BASH skript (podla OS). Pri Ant alebo skriptovanom builde je potrebne dodat potrebne JAR zavislosti (ak nejake treba) v podadresari projektu.

Build musi projekt vycistit od vysledkov predchadzajucich buildov a kompletne zostavit (clean/build). Idealne by mali byt vsetky class subory zabalene do JARu, najlepsie "spustitelneho" prikazom java s parametrom -jar.

Sposob komunikacie s programom je na dodavatelovi - moze ist o stdin/stdout (napr. po zadani izby nasleduje jeden prazdny riadok, potom nabytok), moze ist o subory, ktorych meno je fixne alebo zadavatelne cez argumenty programu. Aj ked sa pouzije standardny vstup (stdin), program nesmie byt interaktivny.

POUZITIE PROGRAMU MUSI BYT ZDOKUMENTOVANE v prilozenom README.txt subore.

Netreba pisat vela, len tolko, kolko by nam malo stacit na jeho zostavenie spustenie.
```
