package hadoop;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.conf.Configuration;

public class Junk {

	public static void main(String[] args) throws Exception {

		SparkConf conf = new SparkConf().setAppName("Example");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		Configuration hadoopConf = new Configuration();

		// pipe character | is the record seperator
		hadoopConf.set("textinputformat.record.delimiter", "|");

		JavaRDD<String> rdd = jsc.newAPIHadoopFile("/home/myhome/1.txt", TextInputFormat.class, LongWritable.class,
				Text.class, hadoopConf).values().map(new Function<Text, String>() {

					@Override
					public String call(Text arg0) throws Exception {
						return arg0.toString();
					}
				});

		rdd.foreach(new VoidFunction<String>() {

			@Override
			public void call(String record) throws Exception {
				System.out.println("Record==>" + record);

			}
		});
	}

}