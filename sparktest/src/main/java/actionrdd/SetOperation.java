package actionrdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class SetOperation {

	public static void main(String[] args) {

		SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rdd = jsc.textFile("/home/debottamr/javaworkspace/sparktest/src/main/resources/data1.json");

		JavaRDD<String> rdd2 = jsc.textFile("/home/debottamr/javaworkspace/sparktest/src/main/resources/data1_copy.json");

		JavaRDD<String> distinct_rdd = rdd.distinct();

		for (String string : distinct_rdd.collect()) {

			System.out.println(string);

		}

		JavaRDD<String> union_rdd = rdd.union(rdd2);

		for (String string : union_rdd.collect()) {

			System.out.println(string);
		}

		JavaRDD<String> intersection_rdd = rdd.intersection(rdd2);

		for (String string : intersection_rdd.collect()) {

			System.out.println(string);
		}

		JavaRDD<String> substract_rdd = rdd.subtract(rdd2);

		JavaPairRDD<String, String> cartesian_rdd = rdd.cartesian(rdd2);

		for (Tuple2<String, String> string : cartesian_rdd.collect()) {

			System.out.println(string._1 + "-----" + string._2);

		}

	}

}