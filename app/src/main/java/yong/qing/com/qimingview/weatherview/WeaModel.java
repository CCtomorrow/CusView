package yong.qing.com.qimingview.weatherview;

import java.util.List;

/**
 * <b>Project:</b> yong.qing.com.qimingview.weatherview <br>
 * <b>Create Date:</b> 2016/5/30 <br>
 * <b>Author:</b> qingyong <br>
 * <b>Description:</b>  <br>
 */
public class WeaModel {

    /**
     * location : {"id":"WX4FBXXFKE4F","name":"北京","country":"CN","path":"北京,北京,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
     * daily : [{"date":"2016-05-30","text_day":"多云","code_day":"4","text_night":"阴","code_night":"9","high":"34","low":"22","precip":"","wind_direction":"无持续风向","wind_direction_degree":"0","wind_speed":"0","wind_scale":"0"},{"date":"2016-05-31","text_day":"多云","code_day":"4","text_night":"晴","code_night":"0","high":"29","low":"17","precip":"0","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"19.31","wind_scale":"3"},{"date":"2016-06-01","text_day":"多云","code_day":"4","text_night":"多云","code_night":"4","high":"30","low":"19","precip":"0","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"14.48","wind_scale":"3"},{"date":"2016-06-02","text_day":"多云","code_day":"4","text_night":"阴","code_night":"9","high":"31","low":"21","precip":"0","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"20.92","wind_scale":"4"},{"date":"2016-06-03","text_day":"阴","code_day":"9","text_night":"阴","code_night":"9","high":"29","low":"21","precip":"0","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"16.09","wind_scale":"3"},{"date":"2016-06-04","text_day":"阵雨","code_day":"10","text_night":"阴","code_night":"9","high":"25","low":"18","precip":"20","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"17.7","wind_scale":"3"},{"date":"2016-06-05","text_day":"阴","code_day":"9","text_night":"阴","code_night":"9","high":"24","low":"18","precip":"60","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"14.48","wind_scale":"3"},{"date":"2016-06-06","text_day":"阴","code_day":"9","text_night":"小雨","code_night":"13","high":"24","low":"17","precip":"60","wind_direction":"无持续风向","wind_direction_degree":"","wind_speed":"12.87","wind_scale":"3"},{"date":"2016-06-07","text_day":"小雨","code_day":"13","text_night":"小雨","code_night":"13","high":"28","low":"17","precip":"20","wind_direction":"南","wind_direction_degree":"180","wind_speed":"19.31","wind_scale":"3"},{"date":"2016-06-08","text_day":"小雨","code_day":"13","text_night":"晴","code_night":"0","high":"27","low":"17","precip":"30","wind_direction":"南","wind_direction_degree":"180","wind_speed":"24.14","wind_scale":"4"},{"date":"2016-06-09","text_day":"小雨","code_day":"13","text_night":"小雨","code_night":"13","high":"29","low":"20","precip":"","wind_direction":"东南","wind_direction_degree":"135","wind_speed":"","wind_scale":""},{"date":"2016-06-10","text_day":"小雨","code_day":"13","text_night":"小雨","code_night":"13","high":"27","low":"19","precip":"","wind_direction":"南","wind_direction_degree":"180","wind_speed":"","wind_scale":""},{"date":"2016-06-11","text_day":"小雨","code_day":"13","text_night":"小雨","code_night":"13","high":"26","low":"18","precip":"","wind_direction":"南","wind_direction_degree":"180","wind_speed":"","wind_scale":""},{"date":"2016-06-12","text_day":"小雨","code_day":"13","text_night":"小雨","code_night":"13","high":"30","low":"19","precip":"","wind_direction":"南","wind_direction_degree":"180","wind_speed":"","wind_scale":""}]
     * last_update : 2016-05-30T18:00:00+08:00
     */

    private List<WeatherResultsModel> results;

    public List<WeatherResultsModel> getResults() {
        return results;
    }

    public void setResults(List<WeatherResultsModel> results) {
        this.results = results;
    }

    public static class WeatherResultsModel {

        private WeatherLocationModel location;
        private String last_update;

        private List<WeatherDailyModel> daily;

        public WeatherLocationModel getLocation() {
            return location;
        }

        public void setLocation(WeatherLocationModel location) {
            this.location = location;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public List<WeatherDailyModel> getDaily() {
            return daily;
        }

        public void setDaily(List<WeatherDailyModel> daily) {
            this.daily = daily;
        }

        public static class WeatherLocationModel {
            /**
             * id : WX4FBXXFKE4F
             * name : 北京
             * country : CN
             * path : 北京,北京,中国
             * timezone : Asia/Shanghai
             * timezone_offset : +08:00
             */
            private String id;
            private String name;
            private String country;
            private String path;
            private String timezone;
            private String timezone_offset;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getTimezone_offset() {
                return timezone_offset;
            }

            public void setTimezone_offset(String timezone_offset) {
                this.timezone_offset = timezone_offset;
            }
        }

        public static class WeatherDailyModel {
            /**
             * date : 2016-05-30
             * text_day : 多云
             * code_day : 4
             * text_night : 阴
             * code_night : 9
             * high : 34
             * low : 22
             * precip :
             * wind_direction : 无持续风向
             * wind_direction_degree : 0
             * wind_speed : 0
             * wind_scale : 0
             */
            private String date;
            private String text_day;
            private int code_day;
            private String text_night;
            private int code_night;
            private int high;
            private int low;
            private String precip;
            private String wind_direction;
            private String wind_direction_degree;
            private String wind_speed;
            private String wind_scale;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getText_day() {
                return text_day;
            }

            public void setText_day(String text_day) {
                this.text_day = text_day;
            }

            public int getCode_day() {
                return code_day;
            }

            public void setCode_day(int code_day) {
                this.code_day = code_day;
            }

            public String getText_night() {
                return text_night;
            }

            public void setText_night(String text_night) {
                this.text_night = text_night;
            }

            public int getCode_night() {
                return code_night;
            }

            public void setCode_night(int code_night) {
                this.code_night = code_night;
            }

            public int getHigh() {
                return high;
            }

            public void setHigh(int high) {
                this.high = high;
            }

            public int getLow() {
                return low;
            }

            public void setLow(int low) {
                this.low = low;
            }

            public String getPrecip() {
                return precip;
            }

            public void setPrecip(String precip) {
                this.precip = precip;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_direction_degree() {
                return wind_direction_degree;
            }

            public void setWind_direction_degree(String wind_direction_degree) {
                this.wind_direction_degree = wind_direction_degree;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }

            public String getWind_scale() {
                return wind_scale;
            }

            public void setWind_scale(String wind_scale) {
                this.wind_scale = wind_scale;
            }
        }
    }
}
