package pair_rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class FilterKeyValue {
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("test").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rdd = jsc.textFile("C:\\codebase\\scala-project\\input data\\system_data");
		JavaPairRDD<String, String> pairRdd = rdd.mapToPair(new PairFunction<String, String, String>() {
			@Override
			public Tuple2<String, String> call(String x) throws Exception {
// TODO Auto-generated method
				return new Tuple2<String, String>(x.split(",")[0], x);
			}
		});
		JavaPairRDD<String, String> filteredRdd = pairRdd.filter(x -> {
			if (Integer.parseInt(x._2.split(",")[4]) > 0) {
				return true;
			} else {
				return false;
			}
		});
		for (Tuple2 string : filteredRdd.collect()) {
			System.out.println("Key " + string._1 + " Value " + string._2);
		}
	}
}