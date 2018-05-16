package com.example.honguyen.dubaothoitiet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
}
