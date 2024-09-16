## Hive Assignment

1.	Difference between Data warehouse and database?

```
Database 

1. Transactional data based on OLTP (Online Transaction Processing) systems.
2. Contains detailed, current data that supports day-to-day operations. Real Time
3. Normalized and reduces data redundency.
4. Write heavy and simple queries.
5. Data collected from single sources.
6. Updates are real time.
7. Powered by Entity-Relationship (ER) modeling
8. MySQL, PostgreSQL, Oracle Database, Microsoft SQL Server.

Data Warehouse

1. Designed for analytical data processing based on OLAP (Online Analytical Processing) systems.
2. Historical Data
3. Denormalized and with data redundency.
4. Read-heavy operations and complex queries. (Aggregate Queries)
5. Data collected from multiple sources.
6. Delayed Updates with replication lag.
7. Powered by star or snowflake schemas (dimensional modeling)
8. Amazon Redshift, Google BigQuery, Snowflake, Teradata, Microsoft Azure Synapse Analytics.
```

2.	Difference between Data warehouse and data mart?

```
1. Scope

Data Warehouse

1. Aggregates entire org data from different data.
2. Schema on Read.
3. Centralized platform for data storage, reporting, and analysis for entire org.
4. Aggregates data from multiple source.
5. Large in size and quite complex.
6. Take a lot of time for implementation
7. More expensive to maintain.

Data Mart

1. Subset of Data warehouse focused on specific department.
2. Schema on write.
3. Depends on the use case for the specific needs of particular department. Provide quick access to data.
4. Pull data from DW or specific resources.
5. Smaller in size and less complex to DW.
6. Quick for implementation.
7. Less expensive to maintain.
```

3.	Difference between OLTP vs OLAP

```
OLTP

1. Transactional data.
2. Contains detailed, current data that supports day-to-day operations. Real Time
3. Normalized and reduces data redundency.
4. Write heavy and simple queries.
5. Data collected from single sources.
6. Updates are real time.
7. Powered by Entity-Relationship (ER) modeling
8. Transactional in nature.
9. Designed for high throughput and low latency to handle many transactions per second with fast response times.

OLAP

1. Designed for analytical data processing based on OLAP (Online Analytical Processing) systems.
2. Historical Data
3. Denormalized and with data redundency.
4. Read-heavy operations and complex queries. (Aggregate Queries)
5. Data collected from multiple sources.
6. Delayed Updates with replication lag.
7. Powered by star or snowflake schemas (dimensional modeling)
8. Non-transactional in nature.
9. Designed for high read performance, with the ability to quickly execute complex queries that scan and analyze large datasets.
```

4.	Why hive metadata is stored in SQL?

```
Metadata in hive is in relational Database.

1. Efficient Metadata Management
2. Due to highly structured nature of it.
3. Also Optimized for complex queries.
4. Provide Transaction Support
5. Rollback and Recovery
6. Highly Scalable in nature.
7. Better in Indexing for complex queries.
8. Interoperability
9. Better Tooling Support
10. Independence from Hive Engine
11. Availability of Mature Databases like Postgres.
12. Metadata Query Performance Optimization
```

5.	Which SQL is the default database for hive?

```
The default database used by Apache Hive for storing its metadata is Apache Derby. 
Derby is an embedded, lightweight, SQL-based relational database that comes bundled with Hive.

Key Points About Derby as the Default Hive Metastore:

Embedded Mode: Hive uses Derby in embedded mode. This means that the Derby database runs within the same JVM process as Hive, and it can only be accessed by a single Hive instance at a time. 

Ease of Setup: Using Derby as the default metastore database simplifies the setup process, as there’s no need to install and configure an external database. It allows users to quickly get Hive up and running without worrying about database configuration.

Limitations:

1. Single-User Access
2. Not Scalable
```

6.	What is managed table?

```
Managed table (also known as an internal table) is a type of table where Hive manages both the metadata and the actual data stored within the table. This means that Hive is responsible for the storage, management, and deletion of the data when a managed table is created, modified, or dropped.

Key Characteristics of a Managed Table:

Data Storage:

1. When you create a managed table, Hive stores the data in a directory under its default warehouse directory (typically located at /user/hive/warehouse on HDFS).

2. The location of the data is determined by Hive, and it follows a specific directory structure within the Hive warehouse directory.

Data Management:

1. Hive takes full responsibility for managing the data within the managed table. This includes tasks like data loading, updating, and deletion.
2. When you load data into a managed table using a Hive LOAD command, the data is moved into the table's directory within the Hive warehouse.

Dropping a Table:

1. If you drop a managed table using the DROP TABLE command, both the metadata and the actual data stored on HDFS are deleted. This means that all the data associated with the table is permanently removed from the Hive warehouse directory.
```

7.	What is external table?

```
An external table is a type of table where Hive manages only the metadata, while the actual data is stored externally, outside of Hive's control. This distinction allows the data to be managed and shared independently of Hive, providing flexibility in how and where the data is stored.

Key Characteristics of an External Table:

Data Storage:

1. The data for an external table is stored outside of Hive’s warehouse directory, typically on a filesystem like HDFS, Amazon S3, or even a local file system.

2. When you create an external table, you specify the location of the data using the LOCATION clause. Hive then references this location for reading and querying the data.

Data Management:

1. Hive does not take responsibility for the data stored in an external table. This means that you, as the user, are responsible for managing the data, including tasks like data loading, updating, and deletion.

2. You can add or remove data from the external location without affecting the table definition in Hive.

Dropping a Table:

1. If you drop an external table using the DROP TABLE command, Hive deletes only the metadata associated with the table. The actual data stored at the external location remains intact and is not deleted.

2. This behavior is useful when the data is shared between different systems or when you need to preserve the data even if the Hive table is deleted.
```

8.	When do we use external table?

```
1. Preserving Data After Dropping a Table - You want to ensure that the data remains intact even if the Hive table is dropped.

2. Sharing Data Between Different Tools or Systems - The data needs to be shared between Hive and other data processing tools or systems.

3. Accessing Pre-existing Data - The data already exists in a particular location, and you want to query it using Hive without moving or duplicating it.

4. Using Data Stored Outside Hive's Default Warehouse - The data is stored in a location outside of Hive's default warehouse directory, and you want to retain that specific storage arrangement.

5. Integrating with Other Data Storage Solutions - You want to integrate Hive with data stored in non-HDFS filesystems like Amazon S3, Google Cloud Storage, or a local file system.

6. Temporary Data Analysis or Exploration - You need to perform temporary analysis on a dataset without moving it into Hive’s warehouse.

7. Handling Large, Immutable Datasets - The data is large, and you don’t expect it to change frequently, or it is immutable.

8. Interfacing with Data Lakes - You are working with a data lake architecture where the data is stored in a central location, accessible by various tools.

9. Custom Data Formats or Storage Layouts - The data is stored in a custom format or layout that Hive does not natively manage.
```

9.	Diff between managed and external table?

```
Managed Table

1. Hive stores the data in its default warehouse directory (typically /user/hive/warehouse on HDFS). Hive determines the exact location of the data.
2. Hive takes full responsibility for managing the data. When you load data into the table, it is moved into Hive's warehouse directory. Hive manages the entire lifecycle of the data.
3. If you drop a managed table using the DROP TABLE command, both the metadata and the actual data stored on the filesystem (e.g., HDFS) are deleted. The data is permanently removed.
4. Used when data needs to be shared between Hive and other systems, when the data already exists in a specific location, or when it’s important to preserve the data independently of Hive’s metadata.
5. Hive "owns" the data. Any operation that impacts the table can also impact the data (e.g., a drop operation removes the data).
6. The user must explicitly specify that the table is external by using the EXTERNAL keyword when creating the table.
7. More flexible, allowing the data to be stored in any accessible location (e.g., HDFS, Amazon S3, or local filesystems) and managed independently.
8. Since Hive manages the data, certain operations (like dropping or altering partitions) might affect the data stored within the warehouse directory.

External Table:

1. The data is stored at an external location specified by the user, which can be outside Hive's warehouse directory. The location is defined using the LOCATION clause during table creation.
2. Hive only manages the metadata (table schema). The user is responsible for managing the data, including loading, updating, and deleting it. The data remains at the specified external location.
3. Dropping an external table deletes only the metadata in Hive. The actual data stored at the external location is not deleted and remains intact.
4. Used when Hive should fully manage the data lifecycle, such as for temporary or intermediate tables, or when the data is exclusively used within Hive.
5. The user "owns" the data. Hive merely references it. The data remains under the user's control regardless of Hive's operations on the table metadata.
6. If no special table options are specified during creation, Hive will create a managed table by default.
7. Less flexible in terms of data location and management since Hive controls where the data is stored and how it is managed.
8. Altering the table (e.g., adding or dropping partitions) does not move or delete data unless explicitly instructed, because the data remains in the external location managed by the user.
```

10.	What happens if you don’t provide location to external table?

```
If you create an external table in Hive without specifying a LOCATION, Hive will use the default warehouse directory for that table, just like it would for a managed table. 

1. Default Location

Default Directory: Without a specified LOCATION, Hive will store the data for the external table in its default warehouse directory. This default directory is typically /user/hive/warehouse on HDFS.

Directory Naming: The default location for the external table will be created under the Hive warehouse directory with a subdirectory named after the table.
```

11.	Performance optimization in hive?

```
1. Columnar Format - ORC (Optimized Row Columnar) or Parquet.
2. Compression.
3. Partitioning.
4. Bucketing.
5. Indexing.
6. Use of Map Reduce Pattern for Joining tables.
7. Selection of specific columns instead of *
8. Metadata Optimization.
```

12.	Explain partitioning? Where did you use with example

```
1. It is a technique used to divide a large table into smaller, more manageable pieces, based on the values of one or more columns. 

2. This helps improve query performance by reducing the amount of data that needs to be scanned when querying specific subsets of the data. 

3. Partition is generally done on low cardinality of column and is stored under partition directory. 

4. It is important to use partition in query in order to actually make use of it in query performance optimization.

Benefits of Partitioning

1. Improved Query Performance: Reduces the amount of data scanned by accessing only relevant partitions.

2. Efficient Data Management: Facilitates easier data management and archival by partitioning data based on time or other logical criteria.

3. Faster Data Loads: Allows incremental data loading by adding new partitions instead of loading data into a single large table.

Example of Partitioning

Suppose you have a large sales dataset that you want to partition by country name. Here's how you can create and use a partitioned table in Hive:

1. Creating a Partitioned Table

CREATE TABLE sales (
    transaction_id INT,
    customer_id STRING,
    amount DECIMAL(10,2)
)
PARTITIONED BY (country_name STRING)
STORED AS ORC;

In this example:

transaction_id, customer_id, amount: Columns of the table.
country_name: Partition key.

2. Loading Data into Partitions

When inserting data into a partitioned table, you need to specify the partition values.

INSERT INTO TABLE sales PARTITION (country_name='India')
VALUES (1, 'cust1', 100.00),
       (2, 'cust2', 150.00);

INSERT INTO TABLE sales PARTITION (country_name='US')
VALUES (3, 'cust3', 200.00),
       (4, 'cust4', 250.00);

3. Querying Partitioned Data
When querying data, use the partition columns to benefit from partition pruning.

SELECT amount FROM sales
WHERE country_name='India';

In this query:

Hive will only scan the partition directory for country_name='India' and ignore other partitions, improving query performance.
```

13.	Explain bucketing? Where did you use with example

```
1. It is a technique used to distribute data across a fixed number of buckets, or files, based on a hash function applied to one or more columns. 

2. Unlike partitioning, which divides a table into logical subsets based on column values, bucketing organizes data into a predefined number of buckets regardless of column values. 

3. This can improve query performance and enable more efficient processing.

4. Generally bucketing is done using the column of high cardinality. One need to specify the number of buckets and the bucket directory.

Benefits of Bucketing

1. Improved Join Performance: Bucketing can optimize join operations, especially when joining large tables on the bucketing column, by ensuring that rows with the same bucketing column value are located in the same bucket.

2. Efficient Sampling: Enables efficient sampling of data by reading data from specific buckets.

3. Simplified Query Optimization: When combined with partitioning, bucketing helps Hive optimize queries by reducing the amount of data read during joins and aggregations.

Example of Bucketing
Suppose you have a large customer dataset and you want to bucket the data based on the customer_id column. Here's how you can create and use a bucketed table in Hive:

1. Creating a Bucketed Table

CREATE TABLE customers (
    customer_id STRING,
    name STRING,
    email STRING
)
CLUSTERED BY (customer_id) INTO 10 BUCKETS
STORED AS ORC;

In this example:

customer_id: The column used for bucketing and 10 BUCKETS - The data will be distributed across 10 buckets (files).

2. Loading Data into a Bucketed Table
You can insert data into a bucketed table just like any other table. Hive will handle the distribution of data across the buckets based on the hash value of customer_id.

INSERT INTO TABLE customers
VALUES ('cust1', 'John Doe', 'john.doe@example.com'),
       ('cust2', 'Jane Smith', 'jane.smith@example.com');

3. Querying Bucketed Data
When you query data, bucketing helps optimize operations such as joins and aggregations.

SELECT c.name, o.order_amount
FROM customers c
JOIN orders o
ON c.customer_id = o.customer_id;

In this query:

If both customers and orders tables are bucketed on customer_id, Hive can efficiently join the data by accessing the corresponding buckets, reducing the amount of data shuffled across the cluster.

4. Combining Bucketing with Partitioning
You can combine bucketing with partitioning for even more efficient data management.

CREATE TABLE sales (
    transaction_id INT,
    customer_id STRING,
    amount DECIMAL(10,2)
)
PARTITIONED BY (country_name STRING)
CLUSTERED BY (customer_id) INTO 10 BUCKETS
STORED AS ORC;

The table is partitioned by country_name, and within each partition, data is bucketed by customer_id. This setup optimizes queries that filter by country_name and join on customer_id.
```

14.	Explain transactional table and implement merge to load incremental data.

```
Transactional Tables support ACID (Atomicity, Consistency, Isolation, Durability) properties, which allow for transactions that include operations like inserts, updates, and deletes. This makes them suitable for scenarios where data needs to be modified or updated incrementally, such as in data warehousing and ETL processes.

1. ACID Properties: Supports transactions, which ensure data consistency and integrity.
2. Insert, Update, Delete: Allows for more complex data manipulation compared to regular Hive tables.
3. Optimistic Concurrency Control: Ensures that transactions do not interfere with each other.
4. Transactional Storage Format: Use a format that supports transactions, such as ORC.
5. Enable Transactions: The Hive metastore must be set up to support transactions.

Merge to load incremental data

1. Creating a Transactional Table

CREATE TABLE employee (
    id INT,
    name STRING,
    department STRING
)
STORED AS ORC
TBLPROPERTIES ('transactional'='true');

In this example:

STORED AS ORC: Data is stored in ORC format, which supports transactions.
TBLPROPERTIES ('transactional'='true'): Marks the table as transactional.

2. Create employee_backup table

CREATE TABLE employee_backup (
    id INT,
    name STRING,
    department STRING
)
STORED AS ORC
TBLPROPERTIES ('transactional'='true');

2. Load Data into Source Table
Load incremental data into the employee table.

INSERT INTO TABLE employee
VALUES (1, 'John Doe', 'Engineering'),
       (2, 'Jane Smith', 'Marketing');

3. Merge Data into Backup Table
Use the MERGE statement to integrate new or updated data from the source table into the backup table.

MERGE INTO employee_backup AS target
USING employee AS source
ON target.id = source.id
WHEN MATCHED THEN
    UPDATE SET target.name = source.name,
               target.department = source.department
WHEN NOT MATCHED THEN
    INSERT (id, name, department)
    VALUES (source.id, source.name, source.department);
```
