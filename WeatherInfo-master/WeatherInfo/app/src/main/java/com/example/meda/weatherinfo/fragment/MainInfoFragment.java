package com.example.meda.weatherinfo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class MainInfoFragment extends Fragment {

    public static final String CITY_NAME = "CITY_NAME";

    TextView tvMain;
    TextView tvDescription;
    TextView tvCurrentTemp;
    ImageView ivIcon;


    public static MainInfoFragment newInstance(String theCityName) {
        MainInfoFragment fragment = new MainInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CITY_NAME, theCityName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.main_info_fragment, container, false);

        Bundle bundle = this.getArguments();
        final String cityName = bundle.getString(CITY_NAME);

        tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);
        tvCurrentTemp = (TextView) rootView.findViewById(R.id.tvCurrentTemp);
        ivIcon = (ImageView) rootView.findViewById(R.id.ivIcon);
        tvMain = (TextView) rootView.findViewById(R.id.tvMain);


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
                if(cityName.equals(response.body().getName())){
                    tvCurrentTemp.setText("Current Temperature:\n" + response.body().getMain().getTemp() + (char) 176);
                    tvMain.setText("Weather: " + response.body().getWeather().get(0).getMain());
                    tvDescription.setText("Description: " +
                            (response.body().getWeather().get(0).getDescription()));

                    Glide.with(getContext())
                            .load("http://openweathermap.org/img/w/" +
                                    response.body().getWeather().get(0).getIcon() + ".png")
                            .override(400, 350)
                            .into(ivIcon);
                }else{
                    Toast.makeText(getContext(),"The city you entered is not valid. " +
                            "\nPlease return to the main page and try again.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                tvDescription.setText("ERROR: " + t.getMessage());
                tvCurrentTemp.setText("ERROR: " + t.getMessage());
                tvMain.setText("ERROR: " + t.getMessage());
            }
        });

        return rootView;
    }

}
