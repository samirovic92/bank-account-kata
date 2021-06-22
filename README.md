# Bank account kata

## What do you need
- Java 11

## Launch tests (unit test & integration test)
```bash
$ mvn clean test
```

## API
to make a deposit operation:
```bash
POST  http://localhost:8080/account/2500/operations
 
{
	"amount" : 300,
	"operationType": "DEPOSIT"
	
}
```

to make a withdrawal operation:
```bash
POST  http://localhost:8080/account/2500/operations
 
{
	"amount" : 100,
	"operationType": "WITHDRAWAL"
	
}
```

to see the history:
```bash
GET  http://localhost:8080/account/2500/account-statement
```
 