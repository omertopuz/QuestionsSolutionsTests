package net.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class App {

	public static void main(String[] args) throws Exception {
		System.out.println(arrayManipulation(10,Arrays.asList(Arrays.asList(2, 6, 8),Arrays.asList(3, 5, 7),Arrays.asList(1, 8, 1),Arrays.asList(5, 9, 15))));
//		System.out.println(arrayManipulation(10,Arrays.asList(Arrays.asList(1, 3, 8),Arrays.asList(4, 6, 5),Arrays.asList(8, 10, 1))));
	}
	public static int activityNotifications(List<Integer> expenditure, int d) {
		// Write your code here
		int notifications = 0;
		List<Integer> trail = new LinkedList<>();
		for(int i = 0;i<d;i++){
			trail.add(expenditure.get(i));
		}

		for(int i = d;i<expenditure.size();i++){
			double median = findMedian(trail);
			if(expenditure.get(i) >= median * 2d){
				notifications++;
			}
			trail.remove(expenditure.get(i-d));
			trail.add(expenditure.get(i));
		}
		return notifications;
	}

	private static double findMedian(List<Integer> trail){
		Collections.sort(trail);
		int s = trail.size();
		return s % 2 == 0 ? (trail.get(s/2) + trail.get(s/2 - 1))/2 : trail.get(s/2);
	}
	public static long arrayManipulation(int n, List<List<Integer>> queries) {
		// Write your code here
		long[] array = new long[n + 1];
		for(int i = 0; i<queries.size();i++ ){
			array[queries.get(i).get(0)-1] += queries.get(i).get(2);
			array[queries.get(i).get(1)] -= queries.get(i).get(2);
		}

		long maxNum = 0;
		long total = 0;

		for(int i = 0; i<array.length;i++ ){
			total += array[i];
			array[i] = total;
			maxNum = Math.max(maxNum,total);
		}

		return maxNum;
	}

	public static void readInput(){
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
			String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

			int n = Integer.parseInt(firstMultipleInput[0]);

			int m = Integer.parseInt(firstMultipleInput[1]);

			List<List<Integer>> queries = new ArrayList<>();

			IntStream.range(0, m).forEach(i -> {
				try {
					queries.add(
							Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
									.map(Integer::parseInt)
									.collect(Collectors.toList())
					);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			});
		}catch (Exception e){

		}


	}

	public static long arrayManipulation2(int n, List<List<Integer>> queries) {
		// Write your code here
		List<ArrayQuery> queryList = new ArrayList<>();
		for(int i = 0; i<queries.size();i++ ){
			queryList.add(new ArrayQuery(queries.get(i).get(0),queries.get(i).get(2),false));
			queryList.add(new ArrayQuery(queries.get(i).get(1),queries.get(i).get(2),true));
		}

		Collections.sort(queryList,(q1,q2)-> q1.index == q2.index ? -Integer.compare(q1.num, q2.num)
				: Integer.compare(q1.index, q2.index)
				);
		int maxNum = Integer.MIN_VALUE;
		int total = 0;
		for(int i = 0; i<queryList.size();i++ ){
			total += queryList.get(i).num;
			maxNum = Math.max(maxNum,total);
		}

		return maxNum;
	}

}
class ArrayQuery{
	public int index;
	public int num;
	public boolean end;
	public ArrayQuery(int index,int num,boolean end){
		this.index=index;
		this.num=end ? -num:num;
		this.end=end;
	}
}