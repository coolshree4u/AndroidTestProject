package com.example.first.testandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonLoadTest extends AppCompatActivity {

    public TextView tvHitView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_load_test);

//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
//        ImageLoader.getInstance().init(config);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        listView = (ListView) findViewById(R.id.lstViewCountriesJson);

        Button btnHit = (Button) findViewById(R.id.btnHit);
        //tvHitView = (TextView) findViewById(R.id.tvJson);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute("http://services.groupkt.com/country/get/all");
            }
        });
    }

    class JSONTask extends AsyncTask<String, String, List<CountryModel>> {

        @Override
        protected List<CountryModel> doInBackground(String... urls) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream stream = httpURLConnection.getInputStream();

                bufferedReader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);

                List<CountryModel> countryModelList = new ArrayList<>();
                JSONObject nextObject = parentObject.getJSONObject("RestResponse");
                JSONArray countryArray = nextObject.getJSONArray("result");
                StringBuffer bufferNew = new StringBuffer();
                for (int i = 0; i < countryArray.length(); i++) {
                    CountryModel countryModel = new CountryModel();
                    countryModel.setName(countryArray.getJSONObject(i).getString("name"));
                    countryModel.setAlpha2_code(countryArray.getJSONObject(i).getString("alpha2_code"));
                    countryModel.setAlpha3_code(countryArray.getJSONObject(i).getString("alpha3_code"));
//                    String nameCountry=countryArray.getJSONObject(i).getString("name");
//                    String alpha2_code=countryArray.getJSONObject(i).getString("alpha2_code");
//                    String alpha3_code=countryArray.getJSONObject(i).getString("alpha3_code");

                    countryModelList.add(countryModel);
                    //bufferNew.append(nameCountry+" - "+alpha2_code+" - "+alpha3_code+"\n");

                }


                return countryModelList;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            return null;
        }

        @Override
        protected void onPostExecute(List<CountryModel> result) {
            super.onPostExecute(result);
            //tvHitView.setText(result);

            CountriesAdapter adapter = new CountriesAdapter(getApplicationContext(), R.layout.row, result);
            listView.setAdapter(adapter);
        }
    }


    public class CountriesAdapter extends ArrayAdapter {


        private List<CountryModel> countryModelList;
        private int resources;

        LayoutInflater inflater;

        public CountriesAdapter(@NonNull Context context, int resource, List<CountryModel> objects) {
            super(context, resource, objects);
            countryModelList = objects;
            this.resources = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(resources, null);
            }

            ImageView imageView;
            TextView tvName;
            TextView tvAlpha2;
            TextView tvAlpha3;

            imageView = (ImageView) convertView.findViewById(R.id.imageView2);
            tvName = (TextView) convertView.findViewById(R.id.tvName);
            tvAlpha2 = (TextView) convertView.findViewById(R.id.tvAlpha2);
            tvAlpha3 = (TextView) convertView.findViewById(R.id.tvAlpha3);


            tvName.setText(countryModelList.get(position).getName());
            tvAlpha2.setText(countryModelList.get(position).getAlpha2_code());
            tvAlpha3.setText(countryModelList.get(position).getAlpha3_code());
            return convertView;
        }
    }
}

