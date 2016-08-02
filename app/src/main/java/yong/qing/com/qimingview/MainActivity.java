package yong.qing.com.qimingview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import yong.qing.com.qimingview.timeLine.TimeLineActivity;
import yong.qing.com.qimingview.uniqueid.GetUniqueId;
import yong.qing.com.qimingview.weatherview.WeatherVpActivity;
import yong.qing.com.qimingview.zhihudetail.DetailActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goDetailBtn = (Button) findViewById(R.id.btn_go_detail);
        Button goTimeLineBtn = (Button) findViewById(R.id.btn_go_timeline);
        Button goWeatherVpBtn = (Button) findViewById(R.id.btn_go_weather);
        goDetailBtn.setOnClickListener(this);
        goTimeLineBtn.setOnClickListener(this);
        goWeatherVpBtn.setOnClickListener(this);
        TextView uniqueIdTv = (TextView) findViewById(R.id.uniqueid);
        uniqueIdTv.setText("唯一ID : " + GetUniqueId.getUniquePsuedoID());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_detail:
                Intent goDetailIntent = new Intent(this, DetailActivity.class);
                startActivity(goDetailIntent);
                break;
            case R.id.btn_go_timeline:
                Intent goTimeLineActivity = new Intent(this, TimeLineActivity.class);
                startActivity(goTimeLineActivity);
                break;
            case R.id.btn_go_weather:
                Intent goWeatherVp = new Intent(this, WeatherVpActivity.class);
                startActivity(goWeatherVp);
            default:
                break;
        }
    }
}