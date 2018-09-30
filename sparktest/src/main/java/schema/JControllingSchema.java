package schema;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Demonstrates strategies for controlling the schema of a DataFrame built from
 * a JSON or RDD data source.
 */
public final class JControllingSchema {

	public static void main(String[] args) throws Exception {
		SparkSession spark = SparkSession.builder().appName("JControllingSchema").master("local").getOrCreate();
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

		// Create an RDD with sample data.
		JavaRDD<Record> beanRDD = sc.parallelize(buildSampleData());

		// Create a DataFrame from the RDD, inferring the schema from a bean class.
		System.out.println("RDD: Schema inferred from bean class.");
		Dataset<Row> dataDF = spark.createDataFrame(beanRDD, Record.class);
		dataDF.printSchema();

		// Use the DataFrame to generate an RDD of Rows for the next demonstration
		// instead of manually building it up again from raw data.
		JavaRDD<Row> rowRDD = dataDF.javaRDD();

		// Create a DataFrame from the RDD, specifying a schema.
		System.out.println("RDD: Schema programmatically specified.");
		dataDF = spark.createDataFrame(rowRDD, buildSchema());
		dataDF.printSchema();

		// Create a DataFrame from a JSON source, inferring the schema from all rows.
		System.out.println("JSON: Schema inferred from all rows.");
		dataDF = spark.read().option("samplingRatio", "1.0").json("/home/debottamr/javaworkspace/sparktest/src/main/resources/schema/data.json");
		dataDF.printSchema();

		// Create a DataFrame from a JSON source, specifying a schema.
		System.out.println("JSON: Schema programmatically specified.");
		dataDF = spark.read().schema(buildSchema()).json("/home/debottamr/javaworkspace/sparktest/src/main/resources/schema/data.json");
		dataDF.printSchema();

		spark.stop();
	}	

	/**
	 * Helper method to construct a Date for sample data.
	 */
	private static Date buildDate(int year, int month, int date, int hour, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date, hour, min);
		return (new Date(calendar.getTimeInMillis()));
	}
	
	/**
	 * Build and return the sample data.
	 */
	private static List<Record> buildSampleData() {
		List<Record> beanData = new ArrayList<>();
		Record record = new Record(
			"Alex",
			3,
			true,
			new HashMap<String, String>(),
			buildDate(2015, 1, 1, 12, 0),
			new ArrayList<Date>());
		record.getPreferences().put("preferred_vet", "Dr. Smith");
		record.getPreferences().put("preferred_appointment_day", "Monday");
		record.getVisits().add(buildDate(2015, 2, 1, 11, 0));
		record.getVisits().add(buildDate(2015, 2, 2, 10, 45));
		beanData.add(record);
		record = new Record(
			"Beth",
			2,
			false,
			new HashMap<String, String>(),
			buildDate(2013, 1, 1, 12, 0),
			new ArrayList<Date>());
		record.getPreferences().put("preferred_vet", "Dr. Travis");
		record.getVisits().add(buildDate(2015, 1, 15, 12, 15));
		record.getVisits().add(buildDate(2015, 2, 1, 11, 15));
		beanData.add(record);
		record = new Record(
			"Charlie",
			1,
			true,
			new HashMap<String, String>(),
			buildDate(2016, 5, 1, 12, 0),
			new ArrayList<Date>());
		beanData.add(record);
		return (beanData);
	}
	
	/**
	 * Build and return a schema to use for the sample data.
	 */	
	private static StructType buildSchema() {
		StructType schema = new StructType(
			new StructField[] {
				DataTypes.createStructField("name", DataTypes.StringType, true),
				DataTypes.createStructField("num_pets", DataTypes.IntegerType, true),
				DataTypes.createStructField("paid_in_full", DataTypes.BooleanType, true),
				DataTypes.createStructField("preferences", 
					DataTypes.createMapType(DataTypes.StringType, DataTypes.StringType, true), true),
				DataTypes.createStructField("registered_on", DataTypes.DateType, true),
				DataTypes.createStructField("visits", 
					DataTypes.createArrayType(DataTypes.TimestampType, true), true) });
		return (schema);
	}
}
