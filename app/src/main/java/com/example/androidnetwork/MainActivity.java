package com.example.androidnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        URL url = null ;

        try {
            //url = new URL("https://i.ibb.co/L0gS5rZ/room-pic.png");
            url = new URL("https://i.ibb.co/3FJqPLd/sa-mp-002.png");
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }

        //Bitmap bitmap = getREmoteImage(url);
        //imageView.setImageBitmap(bitmap);

        new getImageAsync().execute(url);

    }

    public Bitmap getREmoteImage(URL imageURL) {

        try {
            URLConnection urlConnection = imageURL.openConnection();
            urlConnection.connect();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

            bufferedInputStream.close();

            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private class getImageAsync extends AsyncTask<URL, Void, Bitmap > {

        @Override
        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            Bitmap bitmap = getREmoteImage(url);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            //super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }

    }
}