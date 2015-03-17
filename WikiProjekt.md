# Wprowadzenie #

Zalecanym środowiskiem do pracy z kodem źródłowym projektu jest Netbeans 6.1.

Poniższy dokument został napisany i przetestowany w oparciu o środowisko Netbeans 6.1 i
aplikację Cytoscape 2.6.1.

## Pobranie źródeł projektu ##

Źródła projektu można pobrać przy pomocy klienta SVN w Netbeans "Subversion".
Po pobraniu źródeł dodaj następujące biblioteki Cytoscape do aplikacji:
CYTOSCAPE\_HOME/Cytoscape.jar
CYTOSCAPE\_HOME/lib/giny.jar

Usuń biblioteki, do których są niepoprawne odwołania.

## Kompilacja i uruchomienie projektu ##

Przed kompilacją projektu, otwórz plik build-tools/build.xml i zmień w nim
wartości stałych property na własne stałe (położenie Cytoscape)

Aby skompilować i uruchomić projekt z menu kontekstowego, po kliknieciu prawym
przyciskiem myszy na projekcie build-tools wybierz "Run".

Uruchami sie Cytoscape z załadowanym Pluginem.