package partition;

import java.util.ArrayList;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.api.java.function.PairFunction;


import scala.Tuple2;

public class SparkPartitionExample {

	public static void main(String [] args)
	{
		SparkConf sparkConf = new SparkConf().setAppName("CustomParitioning Example").setMaster("local");
		JavaSparkContext jSparkContext = new JavaSparkContext(sparkConf);
		JavaRDD<String> rdd= jSparkContext.textFile("file:///home/debottamr/javaworkspace/sparktest/src/main/resources/input.txt");
		
		JavaPairRDD<String,String> pairRDD= rdd.mapToPair(new PairFunction<String,String,String>(){

			@Override
			public Tuple2<String, String> call(String arg0) throws Exception {
				//return a tuple ,split[0] contains continent and split[1] contains country
				return new Tuple2<String,String>(arg0.split(",")[0],arg0.split(",")[1]);
			}});
		
		pairRDD=pairRDD.partitionBy(new CustomPartitioner(4));		
		pairRDD.saveAsTextFile("file:///home/debottamr/javaworkspace/sparktest/src/main/resources/output/output.txt");
		
	}
	

}
