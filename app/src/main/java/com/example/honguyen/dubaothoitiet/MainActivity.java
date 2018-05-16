package com.example.honguyen.dubaothoitiet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText etAddAddress;
    private Button btSearch;
    private TextView tvCityName;
    private TextView tvCountryName;
    private ImageView imItem;
    private TextView tvTemperature;
    private TextView tvCloud;
    private TextView tvWind;
    private TextView tvStatus;
    private TextView tvHumidity;
    private TextView tvUpdateDay;
    private Button btNextDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setupUI();
    }
    private void getCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=675410e1e50de3ce57c00dec8dbb5bff";
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name ");
                            tvCityName.setText("Tên thành phố : " + name);
                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE yyyy-mm-dd HH-mm-ss");
                            String Day = simpleDateFormat.format(date);

                            tvUpdateDay.setText(Day);
                            JSONArray jsonArrayWeather = new JSONArray("weather");
                            JSONObject jsonObject1Weather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObject1Weather.getString("main");
                            String icon = jsonObject1Weather.getString("icon");

                            Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/"+icon+".png").into(imItem);
                            tvStatus.setText(status);

                            JSONObject jsonObjecmain = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObjecmain.getString("temp");
                            String doam = jsonObjecmain.getString("humidity");

                            Double a = Double.valueOf(nhietdo);
                            String Nhietdo = String.valueOf(a.intValue());

                            tvTemperature.setText(Nhietdo+" C");
                            tvHumidity.setText(doam + "%");

                            JSONObject jsonObjectwind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectwind.getString("speed");

                            tvWind.setText(gio + "m/s");

                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("cloud");
                            String may = jsonObjectCloud.getString("all");
                            tvCloud.setText(may+ "%");

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            tvCountryName.setText(" Tên quốc gia " + country);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }
    private void initUI() {
        etAddAddress = findViewById(R.id.et_add_address);
        btSearch = findViewById(R.id.bt_search);
        tvCityName = findViewById(R.id.tv_city_name);
        tvCountryName = findViewById(R.id.tv_country_name);
        imItem = findViewById(R.id.im_item);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvCloud = findViewById(R.id.tv_cloud);
        tvWind = findViewById(R.id.tv_wind);
        tvStatus = findViewById(R.id.tv_status);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvUpdateDay = findViewById(R.id.tv_update_day);
        btNextDay = findViewById(R.id.bt_next_day);
    }
    public void setupUI (){
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = btSearch.getText().toString();
                getCurrentWeatherData(city);
            }
        });

    }
}
