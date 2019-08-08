package www.androwheels.com.satishkumar.textfromwebpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button get;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.display_Text);
        get = findViewById(R.id.getText);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadData().execute();
            }
        });
    }

    public class LoadData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                //It will load the JavaTPoint Android Home page
                Document doc= Jsoup.connect("https://www.javatpoint.com/android-tutorial").get();
                data=doc.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Updating UI...", Toast.LENGTH_SHORT).show();
            textView.setText(data);
        }
    }
}
