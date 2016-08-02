package yong.qing.com.qimingview.weatherview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mmc.base.http.DefaultHttpListener;
import com.mmc.base.http.HttpRequest;
import com.mmc.base.http.MMCHttpClient;
import com.mmc.base.http.error.HttpError;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yong.qing.com.qimingview.R;

public class WeatherVpActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    // private WeatherDataAdapter mAdapter;
    // private List<WeatherModel> mDatas;

    private WeaDataAdapter mWeaDataAdapter;
    private WeaDataAdapter mWeaDataAdapter2;
    private List<WeaModel.WeatherResultsModel.WeatherDailyModel> mWeatherModels;

    MMCHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_vp);
        httpClient = MMCHttpClient.getInstance(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpClient.cancelRequest(this);
    }

    public void reqData(View view) {
        reqDataFromNet();
    }

    private void reqDataFromNet() {
        final HttpRequest request = new HttpRequest.Builder("https://api.thinkpage.cn/v3/weather/daily.json")
                .setMethod(HttpRequest.Builder.Method.GET)
                .addParam("key", "xtq3t9hr2fwcvo27")
                .addParam("location", "beijing")
                .addParam("language", "zh-Hans")
                .addParam("unit", "c")
                .addParam("start", "0")
                .addParam("days", "14")
                .build();
        httpClient.gsonRequest(WeaModel.class, request, new DefaultHttpListener<WeaModel>() {
            @Override
            public void onSuccess(WeaModel result) {
                if (result != null) {
                    fillDatatoRecyclerView(result.getResults().get(0).getDaily());
                }
            }

            @Override
            public void onError(HttpError error) {
                super.onError(error);
            }
        }, this);
    }

    private void fillDatatoRecyclerView(List<WeaModel.WeatherResultsModel.WeatherDailyModel> daily) {
        mWeatherModels = daily;
        Collections.sort(daily, new Comparator<WeaModel.WeatherResultsModel.WeatherDailyModel>() {
            @Override
            public int compare(WeaModel.WeatherResultsModel.WeatherDailyModel lhs,
                               WeaModel.WeatherResultsModel.WeatherDailyModel rhs) {
                // 排序找到温度最低的，按照最低温度升序排列
                return lhs.getLow() - rhs.getLow();
            }
        });

        int low = daily.get(0).getLow();

        Collections.sort(daily, new Comparator<WeaModel.WeatherResultsModel.WeatherDailyModel>() {
            @Override
            public int compare(WeaModel.WeatherResultsModel.WeatherDailyModel lhs,
                               WeaModel.WeatherResultsModel.WeatherDailyModel rhs) {
                // 排序找到温度最高的，按照最高温度降序排列
                return rhs.getHigh() - lhs.getHigh();
            }
        });
        int high = daily.get(0).getHigh();

        mWeaDataAdapter = new WeaDataAdapter(this, mWeatherModels, low, high);
        mRecyclerView.setAdapter(mWeaDataAdapter);

        mWeaDataAdapter2 = new WeaDataAdapter(this, mWeatherModels, low, high);
        mRecyclerView2.setAdapter(mWeaDataAdapter2);
    }

    private void initView() {
        //得到控件
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        mRecyclerView2 = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal2);
        //设置布局管理器
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView2.setLayoutManager(layoutManager2);
        // mRecyclerView2.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
    }

    /*
    private void initDatas() {
        mDatas = new ArrayList<>(15);
        int[] low = new int[]{0, 15, 15};
        int[] high = new int[]{0, 20, 21};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{15, 16, 17};
        high = new int[]{21, 22, 24};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{17, 18, 17};
        high = new int[]{24, 26, 23};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{17, 16, 20};
        high = new int[]{23, 20, 24};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{20, 24, 20};
        high = new int[]{24, 28, 25};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{20, 16, 17};
        high = new int[]{25, 26, 27};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{17, 18, 19};
        high = new int[]{27, 28, 25};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{19, 20, 22};
        high = new int[]{25, 22, 25};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{22, 24, 24};
        high = new int[]{25, 28, 29};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{24, 24, 22};
        high = new int[]{29, 30, 27};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{22, 20, 21};
        high = new int[]{27, 24, 26};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{21, 22, 23};
        high = new int[]{26, 28, 29};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{23, 24, 20};
        high = new int[]{29, 30, 28};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{20, 16, 17};
        high = new int[]{28, 26, 27};
        mDatas.add(new WeatherModel(15, 30, low, high));

        low = new int[]{17, 18, 0};
        high = new int[]{27, 28, 0};
        mDatas.add(new WeatherModel(15, 30, low, high));

        //设置适配器
        mAdapter = new WeatherDataAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }*/

}
