package com.example.meda.weatherinfo.data;

import android.widget.EditText;

import com.orm.SugarApp;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by meda on 4/27/16.
 */
public class City extends SugarRecord implements Serializable{

    private String city;

    //sugar needs this
    public City() {
    }

    public City(String city){
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
