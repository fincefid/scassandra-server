### Verifying Queries

To see the queries that have been executed by your application:

```
GET on http://[host]:[admin-port]/query
```

The response will look like this:

```json
 [{
   "query": "SELECT * FROM system.schema_columns",
   "consistency": "ONE"
 }, {
   "query": "SELECT * FROM system.peers",
   "consistency": "ONE"
 }, {
   "query": "SELECT * FROM system.local WHERE key='local'",
   "consistency": "ONE"
 }, {
   "query": "use people",
   "consistency": "ONE"
 }, {
   "query": "select * from people",
   "consistency": "TWO"
 }]
```

Where each object in the array contains the query text and the consistency.

To delete the recorded queries:

```
DELETE on http://[host]:[admin-port]/query
```

#### Verifying Connections

To see the connections that have been created by your application:

```
GET on http://[host]:[admin-port]/connection
```

The response will look like this:

```json
 [{
   "result": "success"
 }, {
   "result": "success"
 }, {
   "result": "success"
 }]
```
Where the length of the array is the number of connections. Be aware most drivers make multiple connections so there is rarely a single connection.

To delete the recorded connections:

```
DELETE on http://[host]:[admin-port]/connection

```

#### Verifying prepared statement preparations
To see the prepared statements your application has prepared (which may or may not have been executed):

```
GET on http://[host]:[admin-port]/prepared-statement-preparation
```

The response will look like this:

```json
[{
  "preparedStatementText": "select * from people where name = ?"
}]
```

To delete the recorded prepared statement preparations:

```
DELETE on http://[host]:[admin-port]/prepared-statement-preparation
```

#### Verifying prepared statement executions
To see the prepared statements your application has executed:

```
GET on http://[host]:[admin-port]/prepared-statement-execution
```

The response will look like this:

```json
[{
  "preparedStatementText": "select * from people where name = ?",
  "consistency": "ONE",
  "variables": ["Chris"]
}]
```

Where variables are the values your application bound to the ?s.
Be warned that if you haven't primed the prepared statement you won't get variable values.

To delete the recorded prepared statement executions:

```
DELETE on http://[host]:[admin-port]/prepared-statement-execution
```

#### Verifying batch executions
To see the prepared statements your application has executed:

```
GET on http://[host]:[admin-port]/batch-execution
```

The response will look like this:

```json
[{
  "batchStatements": [{
    "query": "select * from blah"
  }, {
    "query": "select * from blah2"
  }],
  "consistency": "ONE",
  "batchType": "UNLOGGED"
}]
```

Each object in the list is a batch execution that contains the statements, consistency and batch type. 

To delete the recorded batch executions:

```
DELETE on http://[host]:[admin-port]/batch-execution
```
