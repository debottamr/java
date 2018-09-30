package pair_rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class TestPair {

	public static void main(String[] args) {

		SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rdd = jsc.textFile("/home/debottamr/javaworkspace/sparktest/src/main/resources/pari_rdd/system_data");

		JavaPairRDD<String, String> pairs = rdd.mapToPair(new PairFunction<String, String, String>() {

			@Override
			public Tuple2<String, String> call(String input) throws Exception {
				return new Tuple2<String, String>(input.split(",")[0], input);
			}

		});

		for (Tuple2 string : pairs.collect()) {

			System.out.println("Key " + string._1 + " " + "value " + string._2);

		}

	}

}