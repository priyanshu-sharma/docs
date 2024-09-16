# Hive Merge

1. create customer table with cid PK,name,email,lastChange Timestamp columns.
Note : whenever the sql table is updated or deleted, the lastChange timestamp column should update to current timestamp.

1. Create a customer table store it as ORC with transactional properties.

```
CREATE TABLE priyanshu_customer (
    cid INT,
    name STRING,
    email STRING,
    lastChange TIMESTAMP
) STORED AS ORC TBLPROPERTIES ('transactional' = 'true');
```

2. insert 3 records in customers table with id 1,2,3.

```
INSERT INTO priyanshu_customer
  (cid, name, email, lastChange)
VALUES
  (1, 'Priyanshu', 'psharma@gmail.com', current_timestamp()),
  (2, 'Gabe', 'gabe@gmail.com', current_timestamp()),
  (3, 'Edwin', 'edwin@gmail.com', current_timestamp());
```

3. Create customer_backup table same as customer table with same data.

```
CREATE TABLE priyanshu_customer_backup LIKE priyanshu_customer;

MERGE INTO priyanshu_customer_backup AS pcb
USING priyanshu_customer AS pc ON pcb.cid = pc.cid
WHEN NOT MATCHED THEN
   INSERT (cid, name, email, lastChange)
   VALUES(pc.cid, pc.name, pc.email, pc.lastChange);
```

4. update email of cid=1 in customer table

```
UPDATE priyanshu_customer 
SET email = 'priyanshu@gmail.com', lastChange = current_timestamp()
WHERE cid = 1;
```

5. delete cid=2 in customer table

```
DELETE FROM priyanshu_customer
WHERE cid = 3;
```

6. Insert cid=4 in customer table

```
INSERT INTO priyanshu_customer
  (cid, name, email, lastChange)
VALUES
  (4, 'Andrei', 'andrei@gmail.com', current_timestamp());
```

7. Use merge statement to update customer_backup table using customer table.

```
MERGE INTO priyanshu_customer_backup AS pcb
USING priyanshu_customer AS pc
ON pcb.cid = pc.cid
WHEN MATCHED THEN
  UPDATE SET
    name = pc.name,
    email = pc.email,
    lastChange = pc.lastChange
WHEN NOT MATCHED THEN
  INSERT (cid, name, email, lastChange)
  VALUES (pc.cid, pc.name, pc.email, pc.lastChange);
```