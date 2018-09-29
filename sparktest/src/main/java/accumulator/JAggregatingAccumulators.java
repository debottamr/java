package accumulator;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.LongAccumulator;

/**
 * Uses accumulators to provide statistics on potentially incorrect data.
 */
public final class JAggregatingAccumulators {

	public static void main(String[] args) throws Exception {
		SparkSession spark = SparkSession.builder().appName("JAggregatingAccumulators").master("local").getOrCreate();
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

		// Create an accumulator to count how many rows might be inaccurate.
		LongAccumulator heightCount = spark.sparkContext().longAccumulator();

		// Create an accumulator to store all questionable values.
		StringAccumulator heightValues = new StringAccumulator();
		spark.sparkContext().register(heightValues);

		// A function that checks for questionable values
		VoidFunction<Row> validate = new VoidFunction<Row>() {
			public void call(Row row) {
				Long height = row.getLong(row.fieldIndex("height"));
				if (height < 15 || height > 84) {
					heightCount.add(1);
					heightValues.add(String.valueOf(height));
				}
			}
		};

		// Create a DataFrame from a file of names and heights in inches.
		Dataset<Row> heightDF = spark.read().json("/home/debottamr/javaworkspace/sparktest/src/main/resources/accumulator/heights.json");

		// Validate the data with the function.
		heightDF.javaRDD().foreach(validate);

		// Show how many questionable values were found and what they were.
		System.out.println(String.format("%d rows had questionable values.", heightCount.value()));
		System.out.println(String.format("Questionable values: %s", heightValues.value()));

		spark.stop();
	}
}
