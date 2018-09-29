package rddtranformation;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class TransformationExample {
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> radiusData = jsc.textFile("/home/debottamr/javaworkspace/sparktest/src/main/resources/data2.json");
		JavaRDD<String> filtered = radiusData.filter(line -> {
			String[] split = line.split(",");
			if (split[2].contains("port=27")) {
				return true;
			} else {
				return false;
			}
		});
		filtered.foreach(x -> System.out.println(x));
		JavaRDD<String> filtered2 = radiusData.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String arg0) throws Exception {
				String[] split = arg0.split(",");
				if (split[2].contains("port=13")) {
					return true;
				} else {
					return false;
				}
			}
		});
		filtered2.foreach(x -> System.out.println(x));
		filtered.union(filtered2).foreach(x -> System.out.println(x));
	}
}