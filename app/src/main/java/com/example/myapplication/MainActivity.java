package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private String path = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data=getData();
                Log.e("myMessage",""+data);
            }
        }).start();
        getData();
    }

    private String getData() {

        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
           if (urlConnection.getResponseCode()==200){
               InputStream stream = urlConnection.getInputStream();
               ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
               byte[] bytes = new byte[1024];
               int lenght=0;
               while ((lenght=stream.read(bytes))!=-1){
                   outputStream.write(bytes,0,lenght);
               }
               String string = outputStream.toString();
               return  string;
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
       

        return "";
    }


}
