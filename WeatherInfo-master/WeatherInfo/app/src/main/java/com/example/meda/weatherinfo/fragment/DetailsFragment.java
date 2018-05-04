package com.example.meda.weatherinfo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meda.weatherinfo.R;
import com.example.meda.weatherinfo.data.WeatherResult;
import com.example.meda.weatherinfo.network.WeatherAPI;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meda on 4/30/16.
 */
public class DetailsFragment extends Fragment {

    public static final String CITY_NAME = "CITY NAME";

    TextView tvMinTemp;
    TextView tvMaxTemp;
    TextView tvHumidity;
    TextView tvWindSpeed;
    ImageView ivIcon;

    public static DetailsFragment newInstance(String theCityName) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        //cityName
        bundle.putString(CITY_NAME, theCityName);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.details_fragment, container, false);

        Bundle bundle = this.getArguments();
        String cityName = bundle.getString(CITY_NAME);

        tvMinTemp = (TextView) rootView.findViewById(R.id.tvMinTemp);
        tvMaxTemp = (TextView) rootView.findViewById(R.id.tvMaxTemp);
        tvHumidity = (TextView) rootView.findViewById(R.id.tvHumidity);
        tvWindSpeed = (TextView) rootView.findViewById(R.id.tvWindSpeed);
        ivIcon= (ImageView) rootView.findViewById(R.id.ivIcon);


        //API STUFF: RETROFIT
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://api.openweathermap.org/data/2.5/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        //weatherQuery call to API
        final Call<WeatherResult> weatherQuery = weatherAPI.getCurrentWeatherData(
                cityName, "imperial", "ae4f713e289de1e4af2775ce3d1194a4");
        weatherQuery.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
               tvMinTemp.setText("Minimum Temperature: " + response.body().getMain().getTempMin() + (char)176);
                tvMaxTemp.setText("Maximum Temperature: " + response.body().getMain().getTempMax() + (char)176);
                tvHumidity.setText("Humidity: " + response.body().getMain().getHumidity() + "%");
                tvWindSpeed.setText("Wind Speed: " + response.body().getWind().getSpeed() + " mph");

                Glide.with(getContext())
                        .load("http://openweathermap.org/img/w/" +
                                response.body().getWeather().get(0).getIcon() + ".png")
                        .override(400,350)
                        .into(ivIcon);

            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                tvMinTemp.setText("ERROR: " + t.getMessage());
                tvMaxTemp.setText("ERROR: " + t.getMessage());
                tvHumidity.setText("ERROR: " + t.getMessage());
                tvWindSpeed.setText("ERROR: " + t.getMessage());
            }
        });

        return rootView;
    }
}
