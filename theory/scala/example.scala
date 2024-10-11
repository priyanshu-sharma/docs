package org.itc.com
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, rank, row_number}
import org.apache.spark.sql.{SparkSession, functions}


object SQLAssignment extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val sparkconf = new SparkConf()
  sparkconf.set("spark.app.name", "DataframeDemo")
  sparkconf.set("spark.master", "local[*]")
  val spark = SparkSession.builder().config(sparkconf).getOrCreate();
  var menudf = spark.read.format("jdbc").option("url", "jdbc:postgresql://localhost:5432/challenge1").option("dbtable", "menu").option("user", "postgres").option("password", "password").load()
  //  menudf.show(10)
  var salesdf = spark.read.format("jdbc").option("url", "jdbc:postgresql://localhost:5432/challenge1").option("dbtable", "sales").option("user", "postgres").option("password", "password").load()
  //  salesdf.show(20)
  var membersdf = spark.read.format("jdbc").option("url", "jdbc:postgresql://localhost:5432/challenge1").option("dbtable", "members").option("user", "postgres").option("password", "password").load()
  //  membersdf.show(10)

  //  Question 1
  salesdf.join(menudf, salesdf.col("product_id") === menudf.col("product_id"), "inner").groupBy(salesdf.col("customer_id")).agg(functions.sum(menudf.col("price"))).show(20)

  //  Question 2
  salesdf.groupBy(salesdf.col("customer_id")).agg(functions.countDistinct(salesdf.col("order_date"))).show(20)

  //  Question 3
  var outputdf = salesdf.withColumn("row_number", row_number().over(Window.partitionBy(salesdf.col("customer_id")).orderBy(salesdf.col("order_date"))))
  outputdf.join(menudf, outputdf.col("product_id") === menudf.col("product_id"), "inner").filter(outputdf.col("row_number") === 1).show(20)

  //  Question 4
  outputdf = salesdf.join(menudf, salesdf.col("product_id") === menudf.col("product_id")).groupBy(salesdf.col("product_id"), menudf.col("product_name")).agg(functions.count("*"))
  outputdf.orderBy(outputdf.col("count(1)").desc).limit(1).show(5)

  //  Question 5
  outputdf = salesdf.join(menudf, salesdf.col("product_id") === menudf.col("product_id")).groupBy(salesdf.col("customer_id"), menudf.col("product_name")).agg(functions.count("*"))
  outputdf.withColumn("rank", rank().over(Window.partitionBy(outputdf.col("customer_id")).orderBy(outputdf.col("count(1)").desc))).filter(col("rank") === 1).show(10)

  menudf.createOrReplaceTempView("menu")
  salesdf.createOrReplaceTempView("sales")
  membersdf.createOrReplaceTempView("members")

  //  Question 6
  spark.sql("with output_data as (select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date <= s.order_date and m.customer_id = s.customer_id), formatted_data as ( select *, row_number() OVER (Partition by customer_id order by order_date) as row_number from output_data ) select * from formatted_data fd inner join menu m on fd.product_id = m.product_id where row_number = 1").show(10)

  //  Question 7
  spark.sql("with output_data as (select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date > s.order_date and m.customer_id = s.customer_id), formatted_data as ( select *, row_number() OVER (Partition by customer_id order by order_date desc) as row_number from output_data ) select * from formatted_data fd inner join menu m on fd.product_id = m.product_id where row_number = 1").show(10)

  //  Question 8
  spark.sql("with output_data as (select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date > s.order_date and m.customer_id = s.customer_id), formatted_data as (select * from output_data o inner join menu m on o.product_id = m.product_id ) select customer_id, sum(price) from formatted_data group by customer_id").show(10)

  //  Question 9
  spark.sql("with output_data as (select * from sales s inner join menu m on s.product_id = m.product_id ), sd as (select * from output_data where product_name = 'sushi'), nsd as (select * from output_data where product_name != 'sushi'), final_output as (select customer_id, 10 * price as point from nsd UNION select customer_id, 2 * price as point from sd) select customer_id, sum(point) from final_output group by customer_id").show(10)

  //  Question 10
  spark.sql("with output_data as (select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date <= s.order_date and m.customer_id = s.customer_id and date_add(m.join_date, 7) >= s.order_date), formatted_data as ( select o.customer_id, 2 * m.price as points from output_data o inner join menu m on o.product_id = m.product_id ) select customer_id, sum(points) from formatted_data group by customer_id").show(10)
}
