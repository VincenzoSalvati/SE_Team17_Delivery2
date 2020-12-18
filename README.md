# SE_Team17_Delivery2

## Componenti del Team 17
Alex Sorrentino: a.sorrentino120@studenti.unisa.it

Daniele Silvitielli: d.silvitelli@studenti.unisa.it

Flavia Martino: f.martino13@studenti.unisa.it

Vincenzo Salvati: v.salvati10@studenti.unisa.it

## Init

Per un corretto funzionamento del programma, è necessario eseguire come primo file 0_LoginPage.html.

## Login

È possibile effettuare l'accesso al sistema in qualità di Planner o Maintainer. 
In particolare, gli utenti registrati sono 2 Maintainer e 1 Planner e, per autenticarsi, è necessario inserire username e password (stringa maggiore di 5 caratteri). 

Maintainer:
  - maintainer_1:
    - username: 1 
    - password: pass1

  - maintainer_2:
    - username: 2 
    - password: pass2

Planner:
  - username: root
  - password: admin

## Start Page (only for Planner)
L'unica scelta della settimana possibile è 'week 1' poichè le altre non sono presenti nel database.

## Tomcat

Il contenuto della cartella Tomcat (in particolare la cartrella Esame) deve essere spostata nella propria directory "Tomcat/webapps" del prorpio server Tomcat.

## Indirizzo IP server

Va cambiata l'assegnazione della variabile JAVA_TOMCAT_HOST, nella directory "src/JAVASCRIPT/localhost.js", con l'indirizzo IP del proprio server.

## Database Initialization

Per utilizzare il proprio database è necessario compilare le variabili stringa nella directory "/src/JAVA/MySqlDbConnection.java" con il driver usato, l'indirizzo del database con la relativa porta, i campi di autenticazione dell'utente (username e password) e il nome associato al database. 
Inoltre, è necessario eseguire gli script presenti nel package "MySQL" per creare e inizializzare il database.

## Architecture

La cartella "Diagram and Model" contiene i diagrammi e i modelli utilizzati per implementare l'architettura del progetto
