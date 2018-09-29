package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class XmlRecordReader {
	public static void main(String [] args)
	{

		
		SparkSession sparkSession = SparkSession
			      .builder()
			      .appName("XmlRecordReader")
			      .master("local")
			      .getOrCreate();
		JavaSparkContext javaSparkContext = new JavaSparkContext(sparkSession.sparkContext());
        Configuration configuration = new Configuration();
		configuration.set("xmlinput.start", "<Rec>");
        configuration.set("xmlinput.end", "</Rec>");	
		configuration.set("mapreduce.input.fileinputformat.inputdir", "/home/cloudera/temp/1.xml");
		
		JavaPairRDD<LongWritable,Text> javaPairRDD = javaSparkContext.newAPIHadoopRDD(configuration, XmlInputFormat.class, LongWritable.class, Text.class);
		javaPairRDD.foreach(new VoidFunction<Tuple2<LongWritable,Text>> () {

			@Override
			public void call(Tuple2<LongWritable, Text> tuple) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("Xml Record is:" + tuple._2);
			}});
	}

}