package yong.qing.com.qimingview.weatherview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import yong.qing.com.qimingview.R;

/**
 * <b>Project:</b> yong.qing.com.qimingview.weatherview <br>
 * <b>Create Date:</b> 2016/5/29 <br>
 * <b>Author:</b> qingyong <br>
 * <b>Description:</b> adapter <br>
 */
public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataAdapter.WeatherDataViewHolder> {

    private LayoutInflater mInflater;
    private List<WeatherModel> mDatas;

    public WeatherDataAdapter(Context context, List<WeatherModel> datats) {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    @Override
    public WeatherDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_weather_item,
                parent, false);
        WeatherDataViewHolder viewHolder = new WeatherDataViewHolder(view);
        viewHolder.weatherLineView = (WeatherLineView) view.findViewById(R.id.wea_line);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherDataViewHolder holder, int position) {
        // 最低温度设置为15，最高温度设置为30
        WeatherModel weatherModel = mDatas.get(position);
        holder.weatherLineView.setLowHighestData(weatherModel.lowest, weatherModel.highest);
        holder.weatherLineView.setLowHighData(weatherModel.low, weatherModel.high);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class WeatherDataViewHolder extends RecyclerView.ViewHolder {

        WeatherLineView weatherLineView;

        public WeatherDataViewHolder(View itemView) {
            super(itemView);
        }
    }
}
