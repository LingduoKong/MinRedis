# MinRedis

### How to run

* $ mvn exec:java -Dexec.mainClass="com.LingduoKong.app.Main"

### How to test

* $ mvn test

### Description

- SET name value – Set the variable name to the value value. Neither variable names nor values will contain spaces.

- GET name – Print out the value of the variable name, or NULL if that variable is not set.

- UNSET name – Unset the variable name, making it just like that variable was never set.

- NUMEQUALTO value – Print out the number of variables that are currently set to value. <br>
    If no variables equal that value, print 0.
    
- END – Exit the program. Your program will always receive this as its last command.


### OverView

- App class is the entrance of the database

- DataControl is the controller of whole data pipeline, dealing with transactions

- TransactionTask represents a single transaction

- Counter helps us count the number of each value









