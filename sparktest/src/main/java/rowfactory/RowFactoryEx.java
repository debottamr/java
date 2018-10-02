package rowfactory;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.spark_project.guava.collect.ImmutableList;

public class RowFactoryEx {

	
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().appName("Java Spark SQL data sources example")
				.config("spark.some.config.option", "some-value").master("local").getOrCreate();
		
		StructType schemata = DataTypes.createStructType(
		        new StructField[]{
		        		DataTypes.createStructField("NAME", DataTypes.StringType, false),
		        		DataTypes.createStructField("STRING_VALUE", DataTypes.StringType, false),
		        		DataTypes.createStructField("NUM_VALUE", DataTypes.IntegerType, false),
		        });
		Row r1 = RowFactory.create("name1", "value1", 1);
		Row r2 = RowFactory.create("name2", "value2", 2);
		List<Row> rowList = ImmutableList.of(r1, r2);
		Dataset<Row> data = spark.createDataFrame(rowList, schemata);

	}
}
