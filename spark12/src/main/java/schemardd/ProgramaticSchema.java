package schemardd;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.api.java.DataType;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;
import org.apache.spark.sql.api.java.Row;
import org.apache.spark.sql.api.java.StructField;
import org.apache.spark.sql.api.java.StructType;

public class ProgramaticSchema {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("RddEx").setMaster("local");

		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaSQLContext sqlContext = new org.apache.spark.sql.api.java.JavaSQLContext(sc);
		// Load a text file and convert each line to a JavaBean.
		JavaRDD<String> people = sc
				.textFile("/home/debottamr/javaworkspace/spark12/src/main/resources/schema/people.txt");

		String schemaString = "name age";

		// Generate the schema based on the string of schema
		List<StructField> fields = new ArrayList<StructField>();
		for (String fieldName : schemaString.split(" ")) {
			fields.add(DataType.createStructField(fieldName, DataType.StringType, true));
		}
		StructType schema = DataType.createStructType(fields);

		// Convert records of the RDD (people) to Rows.
		JavaRDD<Row> rowRDD = people.map(new Function<String, Row>() {
			public Row call(String record) throws Exception {
				String[] fields = record.split(",");
				return Row.create(fields[0], fields[1].trim());
			}
		});
		
		// Apply the schema to the RDD.
		JavaSchemaRDD peopleSchemaRDD = sqlContext.applySchema(rowRDD, schema);

		// Register the SchemaRDD as a table.
		peopleSchemaRDD.registerTempTable("people");

		// SQL can be run over RDDs that have been registered as tables.
		JavaSchemaRDD results = sqlContext.sql("SELECT name FROM people");
		
		// The results of SQL queries are SchemaRDDs and support all the normal RDD operations.
		// The columns of a row in the result can be accessed by ordinal.
		List<String> names = results.map(new Function<Row, String>() {
		  public String call(Row row) {
		    return "Name: " + row.getString(0);
		  }
		}).collect();
		
		//Save parquet file
		//results.saveAsParquetFile("people.parquet");
		
		

	}
}
