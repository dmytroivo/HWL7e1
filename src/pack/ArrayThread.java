package pack;

import java.util.concurrent.atomic.AtomicInteger;

public class ArrayThread implements Runnable {
	
	private static AtomicInteger qSum=new AtomicInteger(0);
	
	public static int getQSum(){
		return qSum.get();
	}
	
	private int[] arr;
	private int index;
	private int qThread;

	ArrayThread(int[] arr, int index, int qThread) {
		this.arr = arr;
		this.index=index;
		this.qThread=qThread;
	}

	public void run() {
		qSum.addAndGet(subsum(index));
	}

	private int subsum(int index) {
		int sum = 0;
		int maxIndex = (index + 2*arr.length/qThread) > arr.length ? arr.length
				: index + arr.length/qThread;
		for (int i = index; i < maxIndex; i++) {
			sum += arr[i];
		}
		return sum;	
	}
}
