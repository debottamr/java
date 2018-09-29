package map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class MapExample {
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rdd = jsc.textFile("/home/debottamr/javaworkspace/sparktest/src/main/resources/data1.json");
		JavaRDD<Double> rdd2 = rdd.map((String x) -> {
			String[] split = x.split(",", -1);
			if (split[4].length() > 0) {
				split[4] = String.valueOf(Double.parseDouble(split[4]) * 0.9876);
				return Double.parseDouble(split[4]);
			}
			return 0d;
		});
		for (Double string : rdd2.collect()) {
			System.out.println(string.toString());
		}
	}
}