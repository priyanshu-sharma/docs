//1. find outlier for length,width column. to check the output you can intentionally add outlier
  //2. Add 2 new columns based on product_number, split by _ and first part as store_name and second as product_number
  //3. Add new column product_classification considering length and width.
 // if length >4000 and width >20 then "small and wide"
  // if length >6000 and width >40 then "small and narrow"
  // if length >8000 and width >60 then "large and wide"
  //else "large and narrow.
  //4. Find min , max , avg of length for each category

  package org.itc.com

```
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{col, split, when}

object ProductOutput extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val sparkconf = new SparkConf()
  sparkconf.set("spark.app.name", "DataframeDemo")
  sparkconf.set("spark.master", "local[*]")
  val spark = SparkSession.builder().config(sparkconf).getOrCreate();
  val ddlSchema = """product_number string,product_name string,product_category string,product_scale string,product_manufacturer string,product_description string,length double,width double,height double""".stripMargin
  var productDf = spark.read.option("header", true).schema(ddlSchema).csv("C:\\spark\\warehouse\\products.csv")
  //  Question - 2
  productDf = productDf.withColumn("store_name", split(col("product_number"), "_").getItem(0)).withColumn("product_number", split(col("product_number"), "_").getItem(1))
  //  Question - 3
  productDf = productDf.withColumn("product_classification", when(col("length") > 0 && col("length") < 4000 && col("width") > 0 && col("width") < 20, "small_and_wide").when(col("length") > 4000 && col("length") < 6000 && col("width") > 20 && col("width") < 40, "small_and_narrow").when(col("length") > 6000 && col("length") < 8000 && col("width") > 40 && col("width") < 60, "large_and_wide").otherwise("large_and_narrow"))
  productDf.show(10)
  //  Question - 4
  val aggregateDf = productDf.groupBy("product_classification").agg(functions.min(col("length")), functions.max(col("length")), functions.avg(col("length")))
  aggregateDf.show(10)
  // Question - 1
  val lenQuantile = productDf.stat.approxQuantile("length", Array(0.25, 0.75), 0)
  val lengthIQR = lenQuantile(1) - lenQuantile(0)
  val lowerLengthRange = lenQuantile(0) - (1.5 * lengthIQR)
  val upperLengthRange = lenQuantile(1) + (1.5 * lengthIQR)
  println(lenQuantile(0), lenQuantile(1), lengthIQR, lowerLengthRange, upperLengthRange)
  var widQuantile = productDf.stat.approxQuantile("width", Array(0.25, 0.75), 0)
  val widthIQR = widQuantile(1) - widQuantile(0)
  val lowerWidthRange = widQuantile(0) - (1.5 * widthIQR)
  val upperWidthRange = widQuantile(1) + (1.5 * widthIQR)
  println(widQuantile(0), widQuantile(1), widthIQR, lowerWidthRange, upperWidthRange)
  val filteredDf = productDf.filter(col("length") > lowerLengthRange && col("length") < upperLengthRange && col("width") > lowerWidthRange && col("width") < upperWidthRange)
  filteredDf.show(10)
}
```