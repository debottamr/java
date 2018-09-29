package rdd;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

public class SparkParallelizeExample {

	public static void main(String[] args) {
		// configure spark
		SparkConf sparkConf = new SparkConf().setAppName("Print Elements of RDD").setMaster("local[2]")
				.set("spark.executor.memory", "1g");
		// start a spark context
		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		// sample collection
		List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// parallelize the collection to two partitions
		JavaRDD<Integer> rdd = sc.parallelize(collection, 2);

		// sample collection
		List<Integer> collection1 = Arrays.asList(11, 2, 31, 4, 51, 6, 71, 8, 91, 10);

		// parallelize the collection to two partitions
		JavaRDD<Integer> rdd2 = sc.parallelize(collection1, 3);

		System.out.println("Number of partitions : " + rdd.getNumPartitions());

		rdd.foreach(new VoidFunction<Integer>() {
			public void call(Integer number) {
				System.out.println("rdd "+number);
			}
		});

		JavaRDD<Integer> union_rdd = rdd.union(rdd2);

		for (Integer string : union_rdd.collect()) {

			System.out.println("union "+string);
		}

		JavaRDD<Integer> intersection_rdd = rdd.intersection(rdd2);

		for (Integer string : intersection_rdd.collect()) {

			System.out.println("intersection "+string);
		}

		JavaRDD<Integer> substract_rdd = rdd.subtract(rdd2);

		JavaPairRDD<Integer, Integer> cartesian_rdd = rdd.cartesian(rdd2);

		for (Tuple2<Integer, Integer> string : cartesian_rdd.collect()) {

			System.out.println("cartesian "+string._1 + "-----" + string._2);

		}

	}
}
