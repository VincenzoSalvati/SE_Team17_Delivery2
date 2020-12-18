# SE_Team17_Delivery2

## Componenti del gruppo
Alex Sorrentino: a.sorrentino120@studenti.unisa.it

Daniele Silvitielli: d.silvitelli@studenti.unisa.it

Flavia Martino: f.martino13@studenti.unisa.it

Vincenzo Salvati: v.salvati10@studenti.unisa.it

## Init

Per un corretto funzionamento del programma, è necessario eseguire come primo file 0_LoginPage.html.

## Login

È possibile effettuare l'accesso al sistema in qualità di Planner o Maintainer. Per autenticarsi come Maintainer, è necessario inserire come Username l'id del Maintainer e come password, una stringa maggiore di 5 caratteri. Gli utenti registrati sono 2 Maintainer e 1 Planner. 

Maintainer:
  - username_1: 1 
  - password_1: pass1

  - username_2: 2
  - password_2: pass2

Planner:
  - username: root
  - password: admin

## Start Page (only for Planner)
L'unica scelta della settimana possibile è 'week 1' poichè le altre non sono presenti nel database e potrebbero verificarsi degli errori.

## Tomcat

Il contenuto della cartella Tomcat (in particolare la cartrella Esame) deve essere spostata nella propria directory "
Tomcat/webapps" del prorpio server Tomcat.

## Indirizzo IP server

Va cambiata l'assegnazione della variabile JAVA_TOMCAT_HOST, nella directory "src/JAVASCRIPT/localhost.js", con
l'indirizzo IP del proprio server.

## Database Initialization

Per utilizzare il proprio database è necessario compilare le variabili stringa nella directory "
/src/JAVA/MySqlDbConnection.java" con il driver usato, l'indirizzo del database con la relativa porta, i campi di
autenticazione dell'utente (username e password) e il nome associato al database. È necessario eseguire gli script presenti nel package "MySQL" per creare e inizializzare il database.
