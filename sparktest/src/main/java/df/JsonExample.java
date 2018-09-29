package df;

import java.util.List;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;

import scala.collection.mutable.WrappedArray;
public class JsonExample {
	public static void main(String [] args)
	{
		SparkSession sparkSession = SparkSession
			      .builder()
			      .appName("JsonExample")
			      .master("local")
			      .getOrCreate();
		
		//read the csv file
		Dataset<Row> employees = sparkSession.read().option("header", "true").csv("/home/debottamr/javaworkspace/sparktest/src/main/resources/emp.csv");
		
		//create the temp view
		employees.createOrReplaceTempView("employees");
		
		//First , group the employees based on company AND department 
		sparkSession.sql("select company,department,collect_list(name) as department_employees from employees group by company,department").createOrReplaceTempView("employees");
		/*Now create a struct by invoking the UDF create_struct. 
		 * The struct will contain department and the list of employees 
		*/
		sparkSession.sql("select company,collect_list(struct(department,department_employees)) as department_info from employees group by company").toJSON().show(false);
		
	
		
	}
}