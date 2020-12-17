# SE_Team17_Delivery2

## Login

È possibile effettuare l'accesso al sistema in qualità di Planner o Maintainer. Per autenticarsi come Maintainer, è necessario inserire come Username l'id del Maintainer e come password, una stringa maggiore di 5 caratteri. Gli utenti registrati sono:
Maintainer:
Username: 1
Password: pass1
Username: 2
Password: pass2
Il Planner può accedere con le seguenti credenziali:
Username: root
Password: admin

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
