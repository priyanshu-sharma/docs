# SQL 

1.	Types of commands and their examples.

```
1. Data Definition Language (DDL)
DDL commands are used to define the structure of the database objects such as tables, indexes, views, etc.

CREATE TABLE Employees (
    EmployeeID int,
    FirstName varchar(255),
    LastName varchar(255),
    BirthDate date,
    Position varchar(255)
);

ALTER TABLE Employees
ADD COLUMN Salary decimal(10, 2);

DROP TABLE Employees;

TRUNCATE TABLE Employees;

2. Data Manipulation Language (DML)
DML commands are used for managing data within schema objects.

INSERT INTO Employees (EmployeeID, FirstName, LastName, BirthDate, Position)
VALUES (1, 'John', 'Doe', '1980-01-01', 'Manager');

UPDATE Employees
SET Salary = 60000
WHERE EmployeeID = 1;

DELETE FROM Employees
WHERE EmployeeID = 1;

3. Data Control Language (DCL)
DCL commands are used to control access to data stored in the database.

GRANT SELECT, INSERT ON Employees TO user1;

REVOKE SELECT, INSERT ON Employees FROM user1;

4. Transaction Control Language (TCL)
TCL commands are used to manage transactions in the database.

COMMIT;

ROLLBACK;

SAVEPOINT savepoint1;

RELEASE SAVEPOINT savepoint1;

5. Data Query Language (DQL)
DQL commands are used to query the database for information.

SELECT FirstName, LastName
FROM Employees
WHERE Position = 'Manager';
```

2.	What is Normalization and denormalization?  

```
Normalization 

Normalization is the process of organizing the data in a database to reduce redundancy and improve data integrity. The primary objectives of normalization are to:

Eliminate redundant data: Ensure that each piece of data is stored only once.
Ensure data dependencies: Store data in such a way that dependencies make sense, ensuring data integrity.

Reduces data redundancy.
Ensures data integrity and consistency.
Simplifies the maintenance of the database.

Denormalization

Denormalization is the process of combining normalized tables to improve database read performance by reducing the number of joins. This process increases redundancy and may compromise data integrity but can significantly speed up read operations.

Denormalization involves intentionally introducing redundancy into the database by combining tables or including redundant data.

Improves read performance by reducing the number of joins.
Simplifies query structure for certain types of queries.
```

3.	Explain 1NF, 2NF, 3NF.

```
1NF

A table is in 1NF if:

Each column contains only atomic (indivisible) values.
Each column contains values of a single type.
Each column contains unique values or values that are part of a key.


OrderID	Customer	Items
1	Alice	Apple, Banana
2	Bob	Orange
3	Charlie	Apple, Grape

1NF


OrderID	Customer	Item
1	Alice	Apple
1	Alice	Banana
2	Bob	Orange
3	Charlie	Apple
3	Charlie	Grape

2NF

Second Normal Form (2NF) builds on 1NF. A table is in 2NF if:

It is in 1NF.
All non-key attributes are fully functionally dependent on the entire primary key.
This means that there should be no partial dependency, where a non-key attribute depends only on a part of a composite primary key.

OrderID	ProductID	ProductName
1	101	Apple
1	102	Banana
2	103	Orange
3	101	Apple

Orders table:

OrderID	ProductID
1	101
1	102
2	103
3	101

Products table:

ProductID	ProductName
101	Apple
102	Banana
103	Orange

3NF

Third Normal Form (3NF) builds on 2NF. A table is in 3NF if:

It is in 2NF.
All non-key attributes are not only fully functionally dependent on the primary key but also non-transitively dependent on the primary key. This means there should be no transitive dependency, where a non-key attribute depends on another non-key attribute.

Consider the following table that is in 2NF but not in 3NF:

EmployeeID	DepartmentID	DepartmentName
1	10	Sales
2	20	Marketing
3	10	Sales

Employees table:

EmployeeID	DepartmentID
1	10
2	20
3	10

Departments table:

DepartmentID	DepartmentName
10	Sales
20	Marketing

1NF: Eliminate repeating groups; ensure each column has atomic values.
2NF: Eliminate partial dependency; ensure all non-key attributes are fully dependent on the entire primary key.
3NF: Eliminate transitive dependency; ensure all non-key attributes are dependent only on the primary key and not on other non-key attributes.
```

4.	Share use case where you had to do denormalization in database.

```
Data Warehousing

Improved Read Performance: By reducing the number of joins, denormalized tables provide faster read operations, which is critical for generating product pages and customer order history quickly.
Simplified Queries: Queries become simpler and more straightforward, as they no longer require complex joins.
Better User Experience: Faster data retrieval enhances the overall user experience, especially in read-heavy operations.

Issues

Increased Redundancy: Denormalization introduces data redundancy, which can lead to data anomalies and inconsistencies.
More Complex Updates: Maintaining data consistency during insert, update, and delete operations becomes more complex, as redundant data needs to be updated in multiple places.
```

5.	What is primary key and foreign key?

```
A primary key is a unique identifier for a record in a database table. It ensures that each record in the table can be uniquely identified. Primary keys have the following characteristics:

Uniqueness: Each value in the primary key column(s) must be unique across the table.
Non-null: Primary key columns cannot contain null values.
Immutability: The value of a primary key should not change over time.

CREATE TABLE Employees (
    EmployeeID int PRIMARY KEY,
    FirstName varchar(255),
    LastName varchar(255),
    BirthDate date,
    Position varchar(255)
);

A foreign key is a column or a set of columns in a table that establishes a link between the data in two tables. The foreign key in the child table refers to the primary key in the parent table, creating a relationship between the two tables. Foreign keys enforce referential integrity, ensuring that a record in the child table cannot exist unless the corresponding record in the parent table exists.

Example
Consider two tables: Orders and Customers. The CustomerID in the Orders table is a foreign key that references the CustomerID in the Customers table:

CREATE TABLE Customers (
    CustomerID int PRIMARY KEY,
    CustomerName varchar(255),
    Email varchar(255)
);

CREATE TABLE Orders (
    OrderID int PRIMARY KEY,
    CustomerID int,
    OrderDate date,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

Establishes a relationship between two tables.
Ensures referential integrity by linking to a primary key in another table.
Can have duplicate values and can be null.
```

6.	what is alternate and candidate key?

```
Candidate Key

A candidate key is a column, or a set of columns, in a table that can uniquely identify any record in that table. Candidate keys must have the following properties:

Uniqueness: Each value in the candidate key column(s) must be unique.
Non-null: Candidate key columns cannot contain null values.
Irreducibility: No subset of the candidate key can uniquely identify a record.
There can be multiple candidate keys in a table, and one of these is chosen as the primary key.

CREATE TABLE Students (
    StudentID int,
    Email varchar(255),
    PassportNumber varchar(255),
    FirstName varchar(255),
    LastName varchar(255),
    BirthDate date,
    PRIMARY KEY (StudentID)
);

In this example:

StudentID is the primary key and also a candidate key.
Email and PassportNumber can also be candidate keys if they are unique and non-null.

Alternate Key
An alternate key is any candidate key that is not chosen as the primary key. Alternate keys are unique and non-null, just like candidate keys.

Example
Continuing with the Students table, if StudentID is chosen as the primary key, then:

Email is an alternate key.
PassportNumber is an alternate key.
These alternate keys can still be used to uniquely identify records, but they are not the primary method of identification.
```

7.	What are window functions?

```
Window functions perform calculations across a set of table rows that are somehow related to the current row. Unlike aggregate functions, which return a single value for each group of rows, window functions can return multiple values for each group, making them useful for running totals, ranking, moving averages, and more.

Window functions are often used with the OVER clause, which defines the window or the set of rows the function should operate on.

Common Window Functions
Ranking Functions: Assign a rank to each row within a partition.

ROW_NUMBER(): Assigns a unique sequential integer to rows within a partition of the result set.
RANK(): Assigns a rank to each row within a partition of the result set, with gaps in ranking values for tied rows.
DENSE_RANK(): Similar to RANK(), but without gaps in ranking values for tied rows.
Aggregate Functions: Perform aggregate calculations on a set of rows.

SUM(): Calculates the sum of values.
AVG(): Calculates the average of values.
MAX(), MIN(): Find the maximum and minimum values.
Value Functions: Return a value from the set of rows.

FIRST_VALUE(): Returns the first value in an ordered set of values.
LAST_VALUE(): Returns the last value in an ordered set of values.
LAG(): Provides access to a row at a given physical offset that comes before the current row.
LEAD(): Provides access to a row at a given physical offset that comes after the current row.

window_function() OVER (
    [PARTITION BY partition_expression]
    [ORDER BY sort_expression]
    [ROWS|RANGE between_expression]
)

SELECT 
    EmployeeID, 
    FirstName, 
    LastName, 
    RANK() OVER (ORDER BY Salary DESC) AS Rank
FROM Employees;

SELECT 
    DepartmentID, 
    EmployeeID, 
    Salary, 
    SUM(Salary) OVER (PARTITION BY DepartmentID ORDER BY EmployeeID) AS RunningTotal
FROM Employees;
```

8.	Explain Ranking Functions? GIven a small table, write the output.

```
Ranking functions are a type of window function that assign a rank or a unique sequential number to rows within a partition of the result set. The most commonly used ranking functions are:

ROW_NUMBER(): Assigns a unique sequential integer to rows within a partition.
RANK(): Assigns a rank to each row within a partition, with gaps in ranking values for tied rows.
DENSE_RANK(): Similar to RANK(), but without gaps in ranking values for tied rows.
NTILE(n): Distributes the rows in an ordered partition into a specified number of roughly equal groups.

Example Table
Consider the following table Employees:

EmployeeID	Name	Department	Salary
1	Alice	HR	50000
2	Bob	HR	60000
3	Carol	IT	75000
4	Dave	IT	50000
5	Eve	IT	75000

1. ROW_NUMBER()
Assigns a unique sequential integer to each row within a partition of the result set.

SELECT 
    EmployeeID, 
    Name, 
    Department, 
    Salary, 
    ROW_NUMBER() OVER (PARTITION BY Department ORDER BY Salary DESC) AS RowNum
FROM Employees;

EmployeeID	Name	Department	Salary	RowNum
2	Bob	HR	60000	1
1	Alice	HR	50000	2
3	Carol	IT	75000	1
5	Eve	IT	75000	2
4	Dave	IT	50000	3

2. RANK()
Assigns a rank to each row within a partition of the result set, with gaps in ranking values for tied rows.

SELECT 
    EmployeeID, 
    Name, 
    Department, 
    Salary, 
    RANK() OVER (PARTITION BY Department ORDER BY Salary DESC) AS Rank
FROM Employees;

EmployeeID	Name	Department	Salary	Rank
2	Bob	HR	60000	1
1	Alice	HR	50000	2
3	Carol	IT	75000	1
5	Eve	IT	75000	1
4	Dave	IT	50000	3

3. DENSE_RANK()

Assigns a rank to each row within a partition of the result set, without gaps in ranking values for tied rows.

SELECT 
    EmployeeID, 
    Name, 
    Department, 
    Salary, 
    DENSE_RANK() OVER (PARTITION BY Department ORDER BY Salary DESC) AS DenseRank
FROM Employees;

EmployeeID	Name	Department	Salary	DenseRank
2	Bob	HR	60000	1
1	Alice	HR	50000	2
3	Carol	IT	75000	1
5	Eve	IT	75000	1
4	Dave	IT	50000	2

4. NTILE(n)
Distributes the rows in an ordered partition into a specified number of roughly equal groups.

SELECT 
    EmployeeID, 
    Name, 
    Department, 
    Salary, 
    NTILE(2) OVER (ORDER BY Salary DESC) AS Ntile
FROM Employees;

EmployeeID	Name	Department	Salary	Ntile
3	Carol	IT	75000	1
5	Eve	IT	75000	1
2	Bob	HR	60000	1
1	Alice	HR	50000	2
4	Dave	IT	50000	2

ROW_NUMBER(): Provides a unique number for each row within a partition.
RANK(): Assigns ranks with gaps for ties.
DENSE_RANK(): Assigns ranks without gaps for ties.
NTILE(n): Distributes rows into a specified number of roughly equal groups.
```

9.	Types of Joins? With example and usecase. All the number of records return and exact records.

```
1. Inner Join
Definition: Returns only the rows that have matching values in both tables.

SELECT Employees.EmployeeID, Employees.Name, Departments.DepartmentName
FROM Employees
INNER JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;

Employees:

EmployeeID	Name	DepartmentID
1	Alice	10
2	Bob	20
3	Carol	30

Departments:

DepartmentID	DepartmentName
10	HR
20	IT

EmployeeID	Name	DepartmentName
1	Alice	HR
2	Bob	IT

2. Left (Outer) Join
Returns all the rows from the left table and the matched rows from the right table. Unmatched rows from the left table will have NULLs for columns of the right table.

SELECT Employees.EmployeeID, Employees.Name, Departments.DepartmentName
FROM Employees
LEFT JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;


EmployeeID	Name	DepartmentName
1	Alice	HR
2	Bob	IT
3	Carol	NULL

3. Right (Outer) Join
Returns all the rows from the right table and the matched rows from the left table. Unmatched rows from the right table will have NULLs for columns of the left table.

SELECT Employees.EmployeeID, Employees.Name, Departments.DepartmentName
FROM Employees
RIGHT JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;

EmployeeID	Name	DepartmentName
1	Alice	HR
2	Bob	IT
NULL	NULL	Marketing

4. Full (Outer) Join
Returns all the rows when there is a match in either left or right table. Rows that do not have a match in one of the tables will have NULLs for columns of that table.

SELECT Employees.EmployeeID, Employees.Name, Departments.DepartmentName
FROM Employees
FULL OUTER JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;

EmployeeID	Name	DepartmentName
1	Alice	HR
2	Bob	IT
3	Carol	NULL
NULL	NULL	Marketing


5. Cross Join
Returns the Cartesian product of both tables, i.e., all possible combinations of rows.

SELECT Employees.EmployeeID, Employees.Name, Departments.DepartmentName
FROM Employees
CROSS JOIN Departments;


EmployeeID	Name	DepartmentName
1	Alice	HR
1	Alice	IT
2	Bob	HR
2	Bob	IT
3	Carol	HR
3	Carol	IT

6. Seft Join 
A self join is a regular join, but the table is joined with itself.

SELECT e1.EmployeeID AS Employee1, e1.Name AS EmployeeName1, e2.EmployeeID AS Employee2, e2.Name AS EmployeeName2
FROM Employees e1
JOIN Employees e2 ON e1.ManagerID = e2.EmployeeID;

EmployeeID	Name	ManagerID
1	Alice	NULL
2	Bob	1
3	Carol	1
4	Dave	2


Employee1	EmployeeName1	Employee2	EmployeeName2
2	Bob	1	Alice
3	Carol	1	Alice
4	Dave	2	Bob
```


10.	Use case when self join is required.

```
Hierarchical Data Representation
Scenario: You have a table of employees where each employee might have a manager who is also an employee in the same table. You want to create a report showing each employee alongside their manager.

EmployeeID	Name	ManagerID
1	Alice	NULL
2	Bob	1
3	Carol	1
4	Dave	2

Objective: Create a report that lists employees along with their respective managers. This requires a self join because the manager is also an employee in the same table.

SELECT e1.EmployeeID AS EmployeeID, 
       e1.Name AS EmployeeName, 
       e2.EmployeeID AS ManagerID, 
       e2.Name AS ManagerName
FROM Employees e1
LEFT JOIN Employees e2 ON e1.ManagerID = e2.EmployeeID;


EmployeeID	EmployeeName	ManagerID	ManagerName
1	Alice	NULL	NULL
2	Bob	1	Alice
3	Carol	1	Alice
4	Dave	2	Bob

Use case 

Hierarchical Data
Finding Relationships
Comparing Rows
Detecting Duplicates
```

11.	What is subquery?

```
A subquery is a query nested inside another query. It is used to perform operations or obtain data that is then used by the outer query. Subqueries can be used in various parts of a SQL statement, including the SELECT, WHERE, FROM, and HAVING clauses.

SELECT NAME, LOCATION, PHONE_NUMBER 
FROM DATABASE 
WHERE ROLL_NO IN (SELECT ROLL_NO 
                  FROM STUDENT 
                  WHERE SECTION='A');

SELECT EmployeeID, Name, Salary
FROM Employees
WHERE Salary > (SELECT AVG(Salary) FROM Employees);
```

12.	What is corelated subquery?

```
A correlated subquery is a type of subquery that references columns from the outer query. Unlike a regular subquery, which is executed once and used by the outer query, a correlated subquery is evaluated repeatedly, once for each row processed by the outer query. This makes it dynamic and dependent on the values of the outer query.

How It Works
In a correlated subquery:

The subquery contains a reference to a column from the outer query.
For each row in the outer query, the subquery is executed using values from that row.

SELECT column1, column2
FROM OuterTable o
WHERE column1 = (SELECT column3
                 FROM InnerTable i
                 WHERE o.columnX = i.columnY);


SELECT e1.EmployeeID, e1.Name, e1.Salary
FROM Employees e1
WHERE e1.Salary > (SELECT AVG(e2.Salary)
                   FROM Employees e2
                   WHERE e1.DepartmentID = e2.DepartmentID);
```

13.	What is CTE? 

```
It is a temperaory result set that is defined within the execution scope of single SELECT, INSERT, UPDATE or DELETE statement. CTEs provide a way to write modular, readable SQL queries by breaking down complex queries into simpler parts. They can be particularly useful for recursive queries and improving query organization

WITH CTEName AS (
    -- CTE query
    SELECT column1, column2
    FROM TableName
    WHERE condition
)
-- Main query using the CTE
SELECT column1, column2
FROM CTEName
WHERE another_condition;

Temporary Result Set
Readability
Recursive Queries
```


14.	What is derived table?

```
A derived table, also known as an inline view, is a temporary result set that is created within a SQL query. Unlike a Common Table Expression (CTE) which is defined using the WITH clause, a derived table is defined within the FROM clause of a query. Derived tables are used to simplify complex queries by allowing you to create intermediate result sets that can be referenced within the main query.

SELECT column1, column2
FROM (
    -- Derived table query
    SELECT column1, column2
    FROM TableName
    WHERE condition
) AS DerivedTableAlias
WHERE another_condition;

Temporary
Inline
Alias Required

SELECT e.EmployeeID, e.Name, e.Salary
FROM Employees e
JOIN (
    SELECT DepartmentID, AVG(Salary) AS AvgSalary
    FROM Employees
    GROUP BY DepartmentID
) AS DeptAvg
ON e.DepartmentID = DeptAvg.DepartmentID
WHERE e.Salary > DeptAvg.AvgSalary;
```

15.	Find third highest employee based on salary?

```
WITH SalaryRanks AS (
    SELECT Salary, RANK() OVER (ORDER BY Salary DESC) AS Rank
    FROM Employees
)
SELECT Salary
FROM SalaryRanks
WHERE Rank = 3;

```

16.	Find third highest employee based on salary  per department?

```
WITH RankedSalaries AS (
    SELECT 
        EmployeeID, 
        Name, 
        DepartmentID, 
        Salary,
        ROW_NUMBER() OVER (PARTITION BY DepartmentID ORDER BY Salary DESC) AS Rank
    FROM Employees
)
SELECT 
    EmployeeID, 
    Name, 
    DepartmentID, 
    Salary
FROM RankedSalaries
WHERE Rank = 3;

```

17.	How to find duplicate values in a single column?

```
SELECT column_name
FROM table_name
GROUP BY column_name
HAVING COUNT(*) > 1;

SELECT DISTINCT a.column_name
FROM table_name a
JOIN table_name b
ON a.column_name = b.column_name
AND a.rowid != b.rowid;

```

18.	How to find duplicate values in a multiple column?

```
SELECT OrderID, CustomerID, OrderDate
FROM Orders
GROUP BY OrderID, CustomerID, OrderDate
HAVING COUNT(*) > 1;

SELECT DISTINCT a.column1, a.column2, a.column3, ...
FROM table_name a
JOIN table_name b
ON a.column1 = b.column1
AND a.column2 = b.column2
AND a.column3 = b.column3
AND a.rowid != b.rowid;

```

19.	What are ACID properties?

```
ACID properties are a set of principles designed to ensure that database transactions are processed reliably and maintain database integrity. ACID stands for Atomicity, Consistency, Isolation, and Durability. Here’s a detailed explanation of each property:

1. Atomicity - Atomicity ensures that a transaction is treated as a single, indivisible unit of work. Either all operations within the transaction are executed successfully, or none of them are. This means that if a transaction fails or encounters an error, the database is rolled back to its previous state before the transaction began.

Example:

Consider a banking transaction where $100 is transferred from Account A to Account B. The transaction involves two operations:

Deducting $100 from Account A.
Adding $100 to Account B.

Atomicity ensures that both operations are completed successfully. If the second operation fails after the first one has been completed, the system will roll back the first operation, ensuring that the transaction does not leave the database in an inconsistent state.

2. Consistency - Consistency ensures that a transaction brings the database from one valid state to another valid state, preserving the integrity constraints defined in the database schema. After a transaction is completed, all data must be valid according to the database's rules.

Example:
If a database has a rule that the sum of balances in all accounts must equal the total money in the bank, consistency ensures that this rule is not violated by any transaction. If a transaction causes an inconsistency, the system will either roll back the transaction or prevent it from being committed.

3. Isolation - Isolation ensures that concurrent transactions are executed in isolation from each other. The intermediate state of a transaction is not visible to other transactions. Each transaction should appear to execute as if it were the only transaction in the system.

Example:
If two transactions are occurring simultaneously—one transferring $100 from Account A to Account B, and another transferring $50 from Account A to Account C—both transactions should not interfere with each other. Isolation ensures that each transaction will complete in a manner that is consistent with the database's rules, without affecting the other transaction’s results.

Isolation Levels:

Read Uncommitted: Transactions can read uncommitted changes from other transactions. This is the lowest isolation level and can lead to dirty reads.
Read Committed: Transactions can only read committed changes from other transactions. This prevents dirty reads but allows non-repeatable reads.
Repeatable Read: Transactions can read the same data multiple times within the same transaction, ensuring that no other transaction can modify the data between reads. This prevents both dirty reads and non-repeatable reads.
Serializable: The highest isolation level, ensuring complete isolation from other transactions. Transactions appear to execute in a serial order, one after the other.

4. Durability - Durability ensures that once a transaction is committed, its changes are permanent and will survive any subsequent system failures, such as power outages or crashes. Committed transactions are saved to non-volatile storage.

Example:
If a transaction is committed to add $100 to Account B, the change must be persisted even if the database system crashes immediately afterward. The system should be able to recover and reflect the committed changes upon restart.
```

20.	Diff between union and union all

```
UNION - UNION combines the results of two or more SELECT statements and removes duplicate rows from the final result set.

Key Points:

Duplicate Removal: Automatically removes duplicate rows from the combined result set.
Sorting: The process of removing duplicates typically requires sorting, which can impact performance, especially for large datasets.
Usage: Useful when you want to combine results and ensure that each row in the final result set is unique.

UNION ALL - UNION ALL combines the results of two or more SELECT statements and includes all rows, including duplicates, in the final result set.

Key Points:

Duplicates Included: Does not remove duplicates. All rows from all SELECT statements are included in the result set.
Performance: Typically faster than UNION because it does not need to perform the duplicate removal step.
Usage: Useful when you want to retain all rows, including duplicates, or when performance is a concern.

UNION:

Removes duplicate rows.
May be slower due to the need for sorting and deduplication.
Use when you need a result set with unique rows.

UNION ALL:

Includes all rows, including duplicates.
Generally faster because it doesn’t remove duplicates.
Use when you need all rows and duplicate records are acceptable or required.
```

21.	Diff between primary key and unique key

```
PK - A primary key is a column or a set of columns in a table that uniquely identifies each row in that table. A primary key must contain unique values and cannot contain NULL values.

Key Points:

Uniqueness: Ensures that each value in the primary key column(s) is unique across the entire table.
NULL Values: Cannot contain NULL values. Every row must have a valid, non-null value for the primary key.
Index: Automatically creates a unique clustered index on the primary key column(s), which helps in faster retrieval of records.
Constraint: A table can have only one primary key.
Usage: Typically used to uniquely identify a row in a table and establish relationships between tables.

UK - A unique key constraint ensures that all values in a column or a combination of columns are unique. Unlike a primary key, a unique key can contain NULL values (unless specified otherwise by the database).

Key Points:

Uniqueness: Ensures that each value in the unique key column(s) is unique across the table.
NULL Values: Can contain NULL values. Depending on the database, multiple NULLs may be allowed (e.g., in SQL Server, multiple NULLs are allowed; in Oracle, NULLs are treated as distinct).
Index: Automatically creates a unique non-clustered index on the unique key column(s).
Constraint: A table can have multiple unique key constraints.
Usage: Used to enforce uniqueness on columns that are not the primary key but need to be unique.

1. Primary Key:

Ensures the uniqueness of values in a column or set of columns.
Cannot contain NULL values.
Automatically creates a unique clustered index.
There can be only one primary key in a table.
Used to uniquely identify rows and establish relationships between tables.

2. Unique Key:

Ensures the uniqueness of values in a column or set of columns.
Can contain NULL values (multiple NULLs are allowed in some databases).
Automatically creates a unique non-clustered index.
A table can have multiple unique keys.
Used to enforce uniqueness on additional columns beyond the primary key.
```

22.	Diff between truncate and delete

```
DELETE - The DELETE statement is used to remove rows from a table based on a specified condition. It can be used to delete specific rows or all rows in the table.

Row-by-Row Operation: Deletes rows one at a time based on the condition specified in the WHERE clause.
Transaction Log: Each row deletion is logged in the transaction log, which can lead to higher overhead for large deletions.
WHERE Clause: Can delete specific rows if a WHERE clause is provided. Omitting the WHERE clause will delete all rows.
Triggers: Activates any DELETE triggers defined on the table.
Performance: Generally slower than TRUNCATE for large tables due to row-by-row logging and processing.
Rollback: Changes can be rolled back if within a transaction block.

DELETE FROM table_name WHERE condition;

TRUNCATE - The TRUNCATE statement is used to remove all rows from a table quickly and efficiently. It is typically used when you want to clear a table but keep its structure for future use.

Key Points:

Bulk Operation: Removes all rows from the table in one operation, which is generally faster than DELETE for large tables.
Transaction Log: Minimal logging; it only logs the page deallocations, not individual row deletions, which reduces overhead.
WHERE Clause: Cannot use a WHERE clause. It removes all rows in the table.
Triggers: Does not activate DELETE triggers.
Performance: Generally faster than DELETE because it deallocates entire data pages rather than deleting rows one by one.
Rollback: Changes can be rolled back if within a transaction block.
Table Structure: Resets any identity column values to their seed value if applicable.

TRUNCATE TABLE table_name;
```

23.	Diff between having and where

```
WHERE
Definition: The WHERE clause is used to filter records before any groupings or aggregations are applied. It operates on individual rows of data and is used in SELECT, UPDATE, DELETE, and INSERT statements.

Key Points:

Row Filtering: Filters rows before any grouping or aggregation occurs.
Used With: SELECT, UPDATE, DELETE, and INSERT statements.
Cannot Use Aggregate Functions: You cannot use aggregate functions (like SUM(), COUNT(), etc.) in the WHERE clause.
Placement: Appears before the GROUP BY clause in a SELECT statement.

SELECT column1, column2, ...
FROM table_name
WHERE condition;

HAVING
Definition: The HAVING clause is used to filter groups of records after the GROUP BY clause has been applied. It operates on aggregated data and can filter groups based on aggregate functions.

Key Points:

Group Filtering: Filters groups of rows after aggregation or grouping has occurred.
Used With: GROUP BY clause in SELECT statements.
Can Use Aggregate Functions: Allows the use of aggregate functions to filter groups.
Placement: Appears after the GROUP BY clause in a SELECT statement.

SELECT column1, aggregate_function(column2), ...
FROM table_name
GROUP BY column1
HAVING condition;
```

24.	SQL query execution order.

```

FROM: Specifies the tables or views from which to retrieve data. This is where the base tables are joined if needed. The tables are processed first before any filtering or aggregation.

JOIN: Joins are performed based on the JOIN conditions specified. This is where multiple tables are combined into a single dataset.

ON: Specifies the conditions for the JOIN operation. This is part of the JOIN clause and determines how tables are related.

WHERE: Filters rows based on specified conditions. This operation occurs after the FROM and JOIN clauses have been processed, and it eliminates rows that do not meet the criteria.

GROUP BY: Groups rows that have the same values in specified columns into summary rows. This operation is necessary for performing aggregate functions on groups of rows.

HAVING: Filters groups based on aggregate functions. This is similar to WHERE, but it applies to groups rather than individual rows.

SELECT: Specifies the columns to be returned in the result set. This step includes calculating any expressions or aggregations specified in the SELECT clause.

DISTINCT: Removes duplicate rows from the result set, if specified. This operation is applied after the SELECT clause has been processed.

ORDER BY: Sorts the result set based on specified columns. This is the final step in arranging the output in the desired order.

LIMIT / OFFSET: Limits the number of rows returned or skips a number of rows, respectively. This is applied last, after all other operations.

SELECT Department, COUNT(*)
FROM Employees
WHERE Age > 30
GROUP BY Department
HAVING COUNT(*) > 5
ORDER BY COUNT(*) DESC
LIMIT 10;

FROM Employees: Start by processing the Employees table.
WHERE Age > 30: Filter out rows where Age is not greater than 30.
GROUP BY Department: Group the remaining rows by the Department column.
HAVING COUNT(*) > 5: Filter groups where the count of rows in each group is greater than 5.
SELECT Department, COUNT(*): Select the columns to be returned and calculate the aggregate function COUNT(*).
ORDER BY COUNT(*) DESC: Sort the result set based on the count in descending order.
LIMIT 10: Return only the top 10 rows from the sorted result set.
```

25.	What are indexes? Types of Indexes and their differences.

```
Indexes are database objects that improve the speed of data retrieval operations on a table. They work by creating a data structure that allows the database to find rows more efficiently than by scanning the entire table. Indexes can be thought of as pointers to data in a table. They improve query performance by reducing the amount of data that needs to be scanned.

Types of Indexes

Primary Index
Unique Index
Clustered Index
Non-Clustered Index
Composite Index
Bitmap Index
Full-Text Index
Spatial Index
```

26.	What is surrogate key? Give example where you used it and how.

```
A surrogate key is an artificial or synthetic key that is used as a unique identifier for a record in a table, often in place of a natural key (a key derived from actual data). Surrogate keys are typically system-generated and have no business meaning outside of their role as identifiers. They are commonly used in data warehousing, ETL processes, and database design to maintain consistency and simplify joins and relationships.

Characteristics of Surrogate Keys
Uniqueness: Ensures each record has a unique identifier, usually generated by the system.
Non-Meaningful: Typically has no business meaning or relevance. It’s just a unique number or identifier.
Immutable: The value of a surrogate key does not change over time, even if other attributes of the record change.
Performance: Often used to improve performance and simplify complex relationships in database designs.

Sequential Numbers: An incrementing integer (e.g., 1, 2, 3, …).
GUIDs (Globally Unique Identifiers): A unique identifier generated using algorithms (e.g., UUIDs).

Consider a data warehouse where you are integrating customer data from multiple sources. Each source might have its own customer identifier, but these identifiers are not consistent across sources. To unify and manage the customer data, you use a surrogate key.

Consistency: Provides a stable and consistent way to uniquely identify records, regardless of changes in business attributes.
Performance: Often improves query performance, especially when using sequential numbers or integer-based keys.
Simplified Joins: Simplifies relationships between tables by using a single, consistent identifier.
Decoupling: Decouples the database schema from the business data, making schema changes less disruptive.
```

27.	Ways to optimize sql query?

```
1. Indexing - 

Create Appropriate Indexes: Index columns that are frequently used in WHERE clauses, JOIN conditions, and as part of ORDER BY statements.

Avoid Over-Indexing: While indexes speed up reads, they can slow down writes (e.g., INSERT, UPDATE, DELETE). Balance the need for read and write performance.

Use Composite Indexes: For queries that filter on multiple columns, use composite indexes.

2. Query Structure

Use Selective Queries: Retrieve only the columns you need rather than using SELECT *.

Filter Early: Apply filters (WHERE clauses) as early as possible to reduce the amount of data processed.

Optimize Joins: Ensure that joins are performed on indexed columns and that the join conditions are properly optimized.

3. Analyze Query Execution Plans

Use Execution Plans: Analyze execution plans to understand how queries are executed and identify performance bottlenecks.

Optimize Expensive Operations: Look for operations that consume significant resources and optimize them. Common issues include full table scans and inefficient joins.

4. Use Proper Data Types

Choose Appropriate Data Types: Use the most efficient data types for your columns. Smaller data types can improve performance and reduce storage requirements.

Avoid Type Conversions: Ensure that data types match between columns being compared to avoid implicit conversions.

5. Limit the Use of Subqueries

Use Joins Instead: In many cases, joins can be more efficient than subqueries. Analyze whether a join can replace a subquery to improve performance.

Optimize Correlated Subqueries: If you must use subqueries, ensure that they are optimized and that correlated subqueries are used efficiently.

6. Optimize Aggregations
Use Efficient Aggregations: Use efficient aggregate functions and minimize the number of rows processed by aggregations.

Group By Optimization: Ensure that GROUP BY operations are performed on indexed columns when possible.

7. Limit Result Sets

Use Pagination: For queries returning large result sets, use pagination techniques (e.g., LIMIT and OFFSET) to retrieve data in smaller chunks.

Avoid Large Result Sets: Avoid queries that return unnecessarily large result sets or data that is not required.

8. Optimize Database Schema
Normalize Data: Ensure that the database schema is normalized to avoid redundant data and improve efficiency.

Denormalize When Necessary: In some cases, denormalization can improve performance for read-heavy queries by reducing the need for complex joins.

9. Update Statistics
Maintain Up-to-Date Statistics: Regularly update database statistics to ensure the query optimizer has accurate information about data distribution.

10. Use Caching
Cache Frequent Queries: Utilize caching mechanisms to store the results of frequently executed queries to reduce the load on the database.

Implement Query Caching: If supported, enable query caching to reuse results of identical queries.

11. Avoid Common Pitfalls

Avoid Using Functions on Indexed Columns: Using functions on indexed columns (e.g., UPPER(column_name)) can prevent the index from being used effectively.

Avoid Unnecessary Data Manipulation: Avoid performing unnecessary operations or transformations on data that can be done more efficiently.
```
