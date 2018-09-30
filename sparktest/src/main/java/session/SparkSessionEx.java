package session;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalog.Table;
import org.apache.spark.sql.hive.HiveContext;

public class SparkSessionEx {

	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().master("local[*]").config("spark.driver.cores", 1)
				.appName("JUnderstandingSparkSession").getOrCreate();
		
		
		// Spark 1.6
		SparkConf sparkConf = new SparkConf().setMaster("local[*]");
		sparkConf.set("spark.files", "file.txt"); 
		 
		// Spark 2.x
		SparkSession spark1 = SparkSession.builder().master("local[*]").getOrCreate();
		spark.conf().set("spark.files", "file.txt");

		
		// Spark 1.6
		SparkConf sparkConf1 = new SparkConf();
		JavaSparkContext sc = new JavaSparkContext(sparkConf1);
		sc.textFile("README.md", 4);
		 
		// Spark 2.x
		SparkSession spark2 = SparkSession.builder().getOrCreate();
		JavaSparkContext sc2 = new JavaSparkContext(spark.sparkContext());
		sc.textFile("README.md", 4);
		
		
		//read json
		// Spark 1.6
		SparkConf sparkConf2 = new SparkConf();
		JavaSparkContext sc3 = new JavaSparkContext(sparkConf);
		SQLContext sqlContext = new SQLContext(sc);
		//DataFrame df = sqlContext.read().json("data.json");
		//DataFrame tables = sqlContext.tables();
		             
		// Spark 2.x
		SparkSession spark3 = SparkSession.builder().getOrCreate();
		Dataset<Row> df = spark.read().json("data.json");
		Dataset<Table> tables = spark3.catalog().listTables(); 
		
		// Spark 1.6
		SparkConf sparkConf4 = new SparkConf();
		JavaSparkContext sc4 = new JavaSparkContext(sparkConf);
		HiveContext hiveContext = new HiveContext(sc);
		//DataFrame df = hiveContext.sql("SELECT * FROM hiveTable");
		             
		// Spark 2.x
		SparkSession spark5 = SparkSession.builder().enableHiveSupport().getOrCreate();
		Dataset<Row> df5 = spark.sql("SELECT * FROM hiveTable");
		
	}
}
