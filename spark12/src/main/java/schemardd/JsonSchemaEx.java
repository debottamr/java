package schemardd;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;

public class JsonSchemaEx {
	
	public static void main(String[] args) {
		
		SparkConf conf = new SparkConf().setAppName("RddEx").setMaster("local");

		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaSQLContext sqlContext = new org.apache.spark.sql.api.java.JavaSQLContext(sc);
		JavaSchemaRDD people = sqlContext.jsonFile("/home/debottamr/javaworkspace/spark12/src/main/resources/json/people.json");
		people.printSchema();
		people.registerTempTable("people");
		JavaSchemaRDD teenagers = sqlContext.sql("SELECT name FROM people WHERE age >= 13 AND age <= 19");

		// Alternatively, a JavaSchemaRDD can be created for a JSON dataset represented by
		// an RDD[String] storing one JSON object per string.
		List<String> jsonData = Arrays.asList(
		  "{\"name\":\"Yin\",\"address\":{\"city\":\"Columbus\",\"state\":\"Ohio\"}}");
		JavaRDD<String> anotherPeopleRDD = sc.parallelize(jsonData);
		JavaSchemaRDD anotherPeople = sqlContext.jsonRDD(anotherPeopleRDD);

		/*
		 
		 
		 JavaHiveContext sqlContext = new org.apache.spark.sql.hive.api.java.HiveContext(sc);

		 sqlContext.sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING)");
		 sqlContext.sql("LOAD DATA LOCAL INPATH 'examples/src/main/resources/kv1.txt' INTO TABLE src");

		 // Queries are expressed in HiveQL.
		 Row[] results = sqlContext.sql("FROM src SELECT key, value").collect();

		 
		 */
		
		

		
	}

}
