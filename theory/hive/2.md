Managed table 

create table julbatch.empR(id Int,fname String,country String) row format delimited fields terminated by ',' stored as textfile tblproperties("skip.header.line.count"="1")


load data inpath '/tmp/rupali/emp.csv' into table julbatch.empR

select * from julbatch.empR

----
external 

create external table julbatch.places(id Int, name String, place String, countrycode String) row format delimited fields terminated by ',' stored as textfile location '/user/ec2-user/UKUSAJULHDFS/priyanshu_new' tblproperties("skip.header.line.count"="1");

## Partitioning

create external table julbatch.placesP(name String, place String) partitioned by (countrycode String) row format delimited fields terminated by ',' stored as textfile;

insert overwrite table julbatch.placesP Select * from julbatch.places;
sudo -u hdfs hadoop fs -chmod -R 777 "/warehouse/external/hive/julybatch.db/countrycode=IND/000000_0"

## Bucket

file, not directory
bucket size is the power of 2 - 1, 2, 4, 8, 16
Column with high cardinality
city data inside country
monthwise for each year
id % 4 == 0, 1, 2, 3 => 4 buckets
string => hashcode

create external table julbatch.places(id Int, name String, place String, countrycode String) row format delimited fields terminated by ',' stored as textfile location '/user/ec2-user/UKUSAJULHDFS/priyanshu_new';

create external table julbatch.placesPB(name String, place String) partitioned by (countrycode String) clustered by (id) into 4 buckets row format delimited fields terminated by ',' stored as textfile;

insert overwrite table julbatch.placesPB Select * from julbatch.places;


## Creation

CREATE TABLE emp(id Int, name String) STORED as ORC properties tblproperties ("transactional" = "true")