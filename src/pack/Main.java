package pack;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[70000000];
		int sum = 0;
		int qThread = 3;

		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(50);
		}

		Thread[] athr = new Thread[qThread];

		long time1 = System.currentTimeMillis();
		for (int i = 0, j = 0; i < qThread; i++, j += arr.length / qThread) {
			athr[i] = new Thread(new ArrayThread(arr, j, qThread));
			athr[i].start();
		}

		try {
			for (int i = 0; i < qThread; i++) {
				athr[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		long time2 = System.currentTimeMillis();
		System.out.printf("%d поток: сумма= %,d / %tL ms\n", qThread,
				ArrayThread.getQSum(), time2 - time1);

		time1 = System.currentTimeMillis();
		for (int x : arr) {
			sum += x;
		}
		time2 = System.currentTimeMillis();
		System.out.printf("1 поток: сумма= %,d / %tL ms\n", sum, time2 - time1);
	}

}
