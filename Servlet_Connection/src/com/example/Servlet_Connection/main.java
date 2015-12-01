package com.example.Servlet_Connection;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by Eric on 12/1/2015.
 */
public class main extends Activity {
    int[] numbers = new int[3];

    Intent intentCallService5;
    BroadcastReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        intentCallService5 = new Intent(this, MyService5Async.class);

        IntentFilter filter5 = new IntentFilter("NameOfIntent");

        receiver = new MyEmbeddedBroadcastReceiver();
        registerReceiver(receiver, filter5);

    }



    public void sendNums (View v){
//
//        EditText txt1 = (EditText)findViewById(R.id.editText);
//        EditText txt2 = (EditText)findViewById(R.id.editText2);
//        EditText txt3 = (EditText)findViewById(R.id.editText3);

//        double num1 = Double.parseDouble(txt1.getText().toString());
//        double num2 = Double.parseDouble(txt2.getText().toString());
//        double num3 = Double.parseDouble(txt3.getText().toString());

        JSONObject json = new JSONObject();
        try {
            TextView view1 = (TextView)findViewById(R.id.textView);
            view1.setText("Fallowing are set before send and given numbers are: "+numbers[0]+", "+numbers[1]+", "+numbers[2]);

            json.put("one",numbers[0]);
            json.put("two",numbers[1]);
            json.put("three",numbers[2]);
           String baseUrl = "http://10.12.2.169:8080/MinMax";
           // String baseUrl = "http://192.168.1.101:8080/MinMax";
            new HttpAsyncTask().execute(baseUrl, json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void Rand (View v){
        startService(intentCallService5);
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            String jsonString = "";

            try {
                jsonString = HttpUtils.urlContentPost(urls[0], "num", urls[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(jsonString);
            return jsonString;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);

                String num1 = jsonResult.getString("max");
                String num2 = jsonResult.getString("min");

                TextView view1 = (TextView)findViewById(R.id.textView);

                view1.setText("Min is" +num1);
                view1.append("\nMax is" +num2);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    public class MyEmbeddedBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView txt2 = (TextView)findViewById(R.id.textView2);

            if (intent.getAction().equals("NameOfIntent")) {

                numbers = intent.getIntArrayExtra("array");
                txt2.append("Fallowing given numbers are: "+numbers[0]+", "+numbers[1]+", "+numbers[2]);

            }
        }//onReceive
    }// MyEmbeddedBroadcastReceiver

}
