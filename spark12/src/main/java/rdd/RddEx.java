package rdd;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.Accumulator;
import org.apache.spark.AccumulatorParam;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.util.Vector;

import scala.Tuple2;

public class RddEx {

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("RddEx").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
		JavaRDD<Integer> distData = sc.parallelize(data);

		JavaRDD<String> lines = sc.textFile("/home/debottamr/javaworkspace/spark12/src/main/resources/data.txt");
		JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
		int totalLength = lineLengths.reduce((a, b) -> a + b);

		JavaRDD<Integer> lineLengths1 = lines.map(new Function<String, Integer>() {
			public Integer call(String s) {
				return s.length();
			}
		});
		int totalLength1 = lineLengths.reduce(new Function2<Integer, Integer, Integer>() {
			public Integer call(Integer a, Integer b) {
				return a + b;
			}
		});
		
		JavaPairRDD<String, Integer> pairs = lines.mapToPair(s -> new Tuple2(s, 1));
		JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
		
		Broadcast<int[]> broadcastVar = sc.broadcast(new int[] {1, 2, 3});

		broadcastVar.value();
		
		Accumulator<Integer> accum = sc.accumulator(0);

		sc.parallelize(Arrays.asList(1, 2, 3, 4)).foreach(x -> accum.add(x));
		// ...
		// 10/09/29 18:41:08 INFO SparkContext: Tasks finished in 0.317106 s

		accum.value();

	}
}



class VectorAccumulatorParam implements AccumulatorParam<Vector> {
	  public Vector zero(Vector initialValue) {
	    return Vector.zeros(0);
	  }
	  public Vector addInPlace(Vector v1, Vector v2) {
	    v1.addInPlace(v2); return v1;
	  }
	@Override
	public Vector addAccumulator(Vector arg0, Vector arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	}