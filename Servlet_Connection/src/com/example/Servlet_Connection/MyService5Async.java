package com.example.Servlet_Connection;

//------------------------------------------------------------------------
//Intensive CPU service running its heavy duty task in an 
//AsyncTask object. Uses 'Message handling' for synchronization.
//computing Fibonacci numbers between 20 & 50 [ O(2^n) ]


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import java.util.Random;

public class MyService5Async extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		new GetNumbers().execute(35);
	}

	public class GetNumbers extends AsyncTask <Integer, Integer, int[]> {
		
		@Override
		protected int[] doInBackground(Integer... params) {
			Random rand = new Random();

			int one = rand.nextInt(1000);
			int two = rand.nextInt(1000);
			int three = rand.nextInt(1000);

			int[] array = new int[3];
			array[0] = one;
			array[1] = two;
			array[2] = three;

			return array;



						//for (int i=0; i<params[0]; i++){
				//publishProgress(i);
			//}
		//	return null;

		}

		@Override
		protected void onPostExecute(int[] ints) {
			super.onPostExecute(ints);

			Intent intentFilter5 = new Intent("NameOfIntent");
			intentFilter5.putExtra("array",ints);
			sendBroadcast(intentFilter5);

		}


		//		@Override
//		protected void onProgressUpdate(Integer... values) {
//			super.onProgressUpdate(values);
//
//			Intent intentFilter5 = new Intent("NameOfIntent");
//			String data = "New Count: " + values[0];
//			intentFilter5.putExtra("KeyForValue", data);
//			sendBroadcast(intentFilter5);
//		}
	}// ComputeFibonacciRecursivelyTask

}//MyService5

