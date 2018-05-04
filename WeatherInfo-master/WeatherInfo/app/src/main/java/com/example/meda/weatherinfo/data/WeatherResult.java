package com.example.meda.weatherinfo.data;

import java.util.ArrayList;
import java.util.List;

import com.example.meda.weatherinfo.weather_data.Cloud;
import com.example.meda.weatherinfo.weather_data.Coord;
import com.example.meda.weatherinfo.weather_data.Main;
import com.example.meda.weatherinfo.weather_data.Sys;
import com.example.meda.weatherinfo.weather_data.Weather;
import com.example.meda.weatherinfo.weather_data.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by meda on 5/3/16.
 */
public class WeatherResult {


        @SerializedName("coord")
        @Expose
        private Coord coord;
        @SerializedName("weather")
        @Expose
        private List<Weather> weather = new ArrayList<Weather>();
        @SerializedName("base")
        @Expose
        private String base;
        @SerializedName("main")
        @Expose
        private Main main;
        @SerializedName("wind")
        @Expose
        private Wind wind;
        @SerializedName("clouds")
        @Expose
        private Cloud clouds;
        @SerializedName("dt")
        @Expose
        private Integer dt;
        @SerializedName("sys")
        @Expose
        private Sys sys;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("cod")
        @Expose
        private Integer cod;

        /**
         *
         * @return
         * The coord
         */
        public Coord getCoord() {
            return coord;
        }

        /**
         *
         * @param coord
         * The coord
         */
        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        /**
         *
         * @return
         * The weather
         */
        public List<Weather> getWeather() {
            return weather;
        }

        /**
         *
         * @param weather
         * The weather
         */
        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        /**
         *
         * @return
         * The base
         */
        public String getBase() {
            return base;
        }

        /**
         *
         * @param base
         * The base
         */
        public void setBase(String base) {
            this.base = base;
        }

        /**
         *
         * @return
         * The main
         */
        public Main getMain() {
            return main;
        }

        /**
         *
         * @param main
         * The main
         */
        public void setMain(Main main) {
            this.main = main;
        }

        /**
         *
         * @return
         * The wind
         */
        public Wind getWind() {
            return wind;
        }

        /**
         *
         * @param wind
         * The wind
         */
        public void setWind(Wind wind) {
            this.wind = wind;
        }

        /**
         *
         * @return
         * The clouds
         */
        public Cloud getClouds() {
            return clouds;
        }

        /**
         *
         * @param clouds
         * The clouds
         */
        public void setClouds(Cloud clouds) {
            this.clouds = clouds;
        }

        /**
         *
         * @return
         * The dt
         */
        public Integer getDt() {
            return dt;
        }

        /**
         *
         * @param dt
         * The dt
         */
        public void setDt(Integer dt) {
            this.dt = dt;
        }

        /**
         *
         * @return
         * The sys
         */
        public Sys getSys() {
            return sys;
        }

        /**
         *
         * @param sys
         * The sys
         */
        public void setSys(Sys sys) {
            this.sys = sys;
        }

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The cod
         */
        public Integer getCod() {
            return cod;
        }

        /**
         *
         * @param cod
         * The cod
         */
        public void setCod(Integer cod) {
            this.cod = cod;
        }

}
