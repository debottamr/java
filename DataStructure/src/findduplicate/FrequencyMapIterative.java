package findduplicate;

import java.util.HashMap;
import java.util.Map;

public class FrequencyMapIterative {

	// Function to find frequency of each element in a sorted array
	public static void findFrequency(int[] A, Map<Integer, Integer> freq) {
		// search space is A[left..right]
		int left = 0, right = A.length - 1;

		// run till search space is exhausted
		while (left <= right) {
			// A[left..right] consists of only one element,
			// then update its count
			if (A[left] == A[right]) {
				if (freq.get(A[left]) == null) {
					freq.put(A[left], 0);
				}

				freq.put(A[left], freq.get(A[left]) + (right - left + 1));

				// Now discard A[left..right] and continue searching in
				// A[right+1.. n-1] for A[left]
				left = right + 1;
				right = A.length - 1;
			} else {
				// reduce the search space
				right = (left + right) / 2;
			}
		}
	}

	public static void main(String[] args) {
		int[] A = { 2, 2, 2, 4, 4, 4, 5, 5, 6, 8, 8, 9 };

		// map to store frequency of each element of the array
		Map<Integer, Integer> count = new HashMap<>();
		findFrequency(A, count);

		for (Map.Entry<Integer, Integer> pair : count.entrySet()) {
			System.out.println(pair.getKey() + " occurs " + pair.getValue() + " times");
		}
	}
}
//1111