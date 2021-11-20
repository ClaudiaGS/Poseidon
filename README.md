#POSEIDON

##FINANCE Application


**Installation:**

>_Requirements_:
> > - MySQL 8.0
> > - JDK 11
> > - Maven 4.0
> 
> _Steps_:
> > - install previous requirements
> > - clone this repository
> > - create user and password and grant all privileges on the database demo
> > - open project in Java IDE and go to application.properties
> > - change the connection properties as follows:
> > > datasource.user=(your username for DB connection)
jasypt.encryptor.password=(a password you choose to encrypt password for DB connection)
datasource.password=DEC(the password of your username for DB connection)
datasource.url=jdbc:mysql://localhost:3306/demo
datasourcetest.url=jdbc:mysql://localhost:3306/demoTest
> > - in Java Terminal, run 
> mvn jasypt:encrypt -Djasypt.encryptor.password=(jasypt.encryptor.password you have chosen)
> > - in command line, when logged in with your SQL username/password, add source file Data.sql with command:
> > > SOURCE     (/YOUR PATH TO)/data.sql
