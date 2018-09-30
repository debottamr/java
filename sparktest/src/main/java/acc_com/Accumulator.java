package acc_com;

import org.apache.spark.api.java.function.Function2;

public class Accumulator {
	public static Function2<Average, String, Average> sumAndCount = new Function2<Average, String, Average>() {
		@Override
		public Average call(Average avg, String arg1) throws Exception {
			avg.setCount(avg.getCount() + 1);
			avg.setSum(avg.getSum() + Integer.parseInt(arg1));
			return avg;
		}
	};
	public static Function2<Average, Average, Average> combine = new Function2<Average, Average, Average>() {
		@Override
		public Average call(Average one, Average two) throws Exception {
			one.setCount(one.getCount() + two.getCount());
			one.setSum(one.getSum() + two.getSum());
			return one;
		}
	};
}