package acc_com;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AggregateExample {
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rdd = jsc.textFile("/home/debottamr/javaworkspace/sparktest/src/main/resources/acc_com/system_data");
		JavaRDD<String> convertedRdd = rdd.map(x -> {
			String[] split = x.split(",", -1);
			return split[4];
		});
		Average average = new Average(0, 0);
		Average avg = convertedRdd.aggregate(average, Accumulator.sumAndCount, Accumulator.combine);
		System.out.println(avg.average());
	}
}