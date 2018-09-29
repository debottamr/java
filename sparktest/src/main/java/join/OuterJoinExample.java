package join;

import java.util.ArrayList;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;




public class OuterJoinExample{

	public static void main(String [] args)
	{
		SparkSession session = SparkSession.builder().appName("test 2.0").master("local[*]").getOrCreate();

		
		Dataset<Row> customers = session.read().option("header",true).csv("/home/debottamr/javaworkspace/sparktest/src/main/resources/customer.csv");
		Dataset<Row> orders = session.read().option("header",true).csv("/home/debottamr/javaworkspace/sparktest/src/main/resources/order.csv");
		
		ArrayList<String> joinColList = new ArrayList<String>();
		joinColList.add("CustomerId");
		Dataset<Row> joinedData = customers.join(orders,scala.collection.JavaConversions.asScalaBuffer(joinColList),"leftouter");
		
		joinedData.show();
	}
}