package lamdba;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class LambdaEx {

	public static void main(String[] args) {
		new Thread(() -> System.out.println("My Runnable")).start();

		List<String> pointList = new ArrayList<>();
		pointList.add("1");
		pointList.add("2");

		pointList.forEach(p -> {
			System.out.println(p);
			// Do more work
		});
		JButton button =  new JButton("Submit");
		button.addActionListener((e) -> {
		    System.out.println("Click event triggered !!");
		});     
		
	}

}
