package broadcast;

import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

public class BroadcastEx {
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().master("local").appName("JBroadcastVariables").getOrCreate();
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

		// Register state data and schema as broadcast variables
		Dataset<Row> localDF = spark.read().json("/home/debottamr/javaworkspace/sparktest/src/main/resources/broadcast/us_states.json");
		Broadcast<List<Row>> broadcastStateData = sc.broadcast(localDF.collectAsList());
		Broadcast<StructType> broadcastSchema = sc.broadcast(localDF.schema());
		
		
		// Create a DataFrame based on the store locations.
		Dataset<Row> storesDF = spark.read().json("/home/debottamr/javaworkspace/sparktest/src/main/resources/broadcast/store_locations.json");
		 
		// Create a DataFrame of US state data with the broadcast variables.
		Dataset<Row> stateDF = spark.createDataFrame(broadcastStateData.value(), broadcastSchema.value()); 
		
		// Join the DataFrames to get an aggregate count of stores in each US Region
		System.out.println("How many stores are in each US region?");
		Dataset<Row> joinedDF = storesDF.join(stateDF, "state").groupBy("census_region").count();
		joinedDF.show();
	}
}
