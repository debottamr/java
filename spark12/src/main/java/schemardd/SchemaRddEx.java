package schemardd;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;
import org.apache.spark.sql.api.java.Row;

public class SchemaRddEx {

	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("RddEx").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaSQLContext sqlContext = new org.apache.spark.sql.api.java.JavaSQLContext(sc);
		// Load a text file and convert each line to a JavaBean.
		JavaRDD<Person> people = sc.textFile("/home/debottamr/javaworkspace/spark12/src/main/resources/schema/people.txt").map(
		  new Function<String, Person>() {
		    public Person call(String line) throws Exception {
		      String[] parts = line.split(",");

		      Person person = new Person();
		      person.setName(parts[0]);
		      person.setAge(Integer.parseInt(parts[1].trim()));

		      return person;
		    }
		  });
		
		// Apply a schema to an RDD of JavaBeans and register it as a table.
		JavaSchemaRDD schemaPeople = sqlContext.applySchema(people, Person.class);
		schemaPeople.registerTempTable("people");
		
		// SQL can be run over RDDs that have been registered as tables.
		JavaSchemaRDD teenagers = sqlContext.sql("SELECT name FROM people WHERE age >= 13 AND age <= 19");
		
		// The results of SQL queries are SchemaRDDs and support all the normal RDD operations.
		// The columns of a row in the result can be accessed by ordinal.
		List<String> teenagerNames = teenagers.map(new Function<Row, String>() {
		  public String call(Row row) {
		    return "Name: " + row.getString(0);
		  }
		}).collect();
	}
}
