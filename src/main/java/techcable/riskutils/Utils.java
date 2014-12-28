package techcable.riskutils;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.lang3.ArrayUtils;

public class Utils {
	private Utils() {}
	
	public static Comparator<Integer> REVERSE_COMPARE = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return -o1.compareTo(o2); //Opposite of positive
		}
	};
	public static void reverseSort(int[] array) {
		Arrays.sort(array);
		ArrayUtils.reverse(array);
	}
}
