# SQL Merge

1. create customer table with cid PK,name,email,lastChange Timestamp columns.
Note : whenever the sql table is updated or deleted, the lastChange timestamp column should update to current timestamp.

1. Create a customer table
2. Create a function which will return a trigger on INSERT, UPDATE and DELETE to update the last timestamp of inserted, updated or deleted tuple.

```
CREATE TABLE customer (
    cid SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    lastChange TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_last_change()
RETURNS TRIGGER AS $$
BEGIN
    NEW.lastChange = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insert_last_change_trigger
BEFORE INSERT ON customer
FOR EACH ROW
EXECUTE FUNCTION update_last_change();

CREATE TRIGGER update_last_change_trigger
BEFORE UPDATE ON customer
FOR EACH ROW
EXECUTE FUNCTION update_last_change();

CREATE TRIGGER delete_last_change_trigger
BEFORE DELETE ON customer
FOR EACH ROW
EXECUTE FUNCTION update_last_change();
```

2. insert 3 records in customers table with id 1,2,3.

```
INSERT INTO customer
  (cid, name, email)
VALUES
  (1, 'Priyanshu', 'psharma@gmail.com'),
  (2, 'Gabe', 'gabe@gmail.com'),
  (3, 'Edwin', 'edwin@gmail.com');
```

3. Create customer_backup table same as customer table with same data.

```
CREATE TABLE customer_backup AS TABLE customer WITH NO DATA;

INSERT INTO customer_backup (cid, name, email, lastChange)
SELECT cid, name, email, lastChange
FROM customer;
```

4. update email of cid=1 in customer table

```
UPDATE customer 
SET email = 'priyanshu@gmail.com' 
WHERE cid = 1;
```

5. delete cid=2 in customer table

```
DELETE FROM customer
WHERE cid = 2;
```

6. Insert cid=4 in customer table

```
INSERT INTO customer
  (cid, name, email)
VALUES
  (4, 'Andrei', 'andrei@gmail.com');
```

7. Use merge statement to update customer_backup table using customer table.

```
MERGE INTO customer_backup cb
USING customer c ON cb.cid = c.cid
WHEN NOT MATCHED THEN
   INSERT (cid, name, email, lastChange)
   VALUES(c.cid, c.name, c.email, c.lastChange)
WHEN MATCHED THEN
   UPDATE SET
      cid = c.cid,
      name = c.name,
      email = c.email,
      lastChange = c.lastChange;
```