package sparktest;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Encoders;

import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class Main {

	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().appName("Spark SQL examples").master("local").getOrCreate();
		Dataset<Row> data = spark.read().json("/home/debottamr/javaworkspace/sparktest/src/main/resources/data.json");
		data.show();
		data.printSchema();

		Dataset<Row> fnameDS = data.select("fname");

		fnameDS.show();

		Dataset<Row> nameDS = data.select(col("fname"), col("lname"));

		nameDS.show();

		Dataset<Row> genderDS = data.groupBy("gender").count();

		genderDS.show();

		Dataset<Row> maleGenederDS = data.filter(col("gender").eqNullSafe("Male"));

		maleGenederDS.show();

		Dataset<String> fullNameDS = data.map(
				(MapFunction<Row, String>) row -> row.getAs("fname").toString() + " " + row.getAs("lname").toString(),
				Encoders.STRING());
		fullNameDS.show();

		Dataset<Person> peopleDS = data.as(Encoders.bean(Person.class));

		peopleDS.show();
		peopleDS.explain();

		spark.stop();
	}
}
