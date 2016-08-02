package yong.qing.com.qimingview.weatherview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yong.qing.com.qimingview.R;

/**
 * <b>Project:</b> yong.qing.com.qimingview.weatherview <br>
 * <b>Create Date:</b> 2016/5/29 <br>
 * <b>Author:</b> qingyong <br>
 * <b>Description:</b> adapter <br>
 */
public class WeaDataAdapter extends RecyclerView.Adapter<WeaDataAdapter.WeatherDataViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<WeaModel.WeatherResultsModel.WeatherDailyModel> mDatas;
    private int mLowestTem;
    private int mHighestTem;

    public WeaDataAdapter(Context context, List<WeaModel.WeatherResultsModel.WeatherDailyModel> datats
            , int lowtem, int hightem) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
        mLowestTem = lowtem;
        mHighestTem = hightem;
    }

    @Override
    public WeatherDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_weather_item, parent, false);
        WeatherDataViewHolder viewHolder = new WeatherDataViewHolder(view);
        viewHolder.dayText = (TextView) view.findViewById(R.id.id_day_text_tv);
        viewHolder.dayIcon = (ImageView) view.findViewById(R.id.id_day_icon_iv);
        viewHolder.weatherLineView = (WeatherLineView) view.findViewById(R.id.wea_line);
        viewHolder.nighticon = (ImageView) view.findViewById(R.id.id_night_icon_iv);
        viewHolder.nightText = (TextView) view.findViewById(R.id.id_night_text_tv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherDataViewHolder holder, int position) {
        // 最低温度设置为15，最高温度设置为30
        Resources resources = mContext.getResources();
        WeaModel.WeatherResultsModel.WeatherDailyModel weatherModel = mDatas.get(position);
        holder.dayText.setText(weatherModel.getText_day());
        int iconday = resources.getIdentifier("wth_code_" + weatherModel.getCode_day(), "drawable", mContext.getPackageName());
        if (iconday == 0) {
            holder.dayIcon.setImageResource(R.drawable.wth_code_99);
        } else {
            holder.dayIcon.setImageResource(iconday);
        }
        holder.weatherLineView.setLowHighestData(mLowestTem, mHighestTem);
        int iconight = resources.getIdentifier("wth_code_" + weatherModel.getCode_day(), "drawable", mContext.getPackageName());
        if (iconight == 0) {
            holder.nighticon.setImageResource(R.drawable.wth_code_99);
        } else {
            holder.nighticon.setImageResource(iconight);
        }
        holder.nightText.setText(weatherModel.getText_night());
        int low[] = new int[3];
        int high[] = new int[3];
        low[1] = weatherModel.getLow();
        high[1] = weatherModel.getHigh();
        if (position <= 0) {
            low[0] = 0;
            high[0] = 0;
        } else {
            WeaModel.WeatherResultsModel.WeatherDailyModel weatherModelLeft = mDatas.get(position - 1);
            low[0] = (weatherModelLeft.getLow() + weatherModel.getLow()) / 2;
            high[0] = (weatherModelLeft.getHigh() + weatherModel.getHigh()) / 2;
        }
        if (position >= mDatas.size() - 1) {
            low[2] = 0;
            high[2] = 0;
        } else {
            WeaModel.WeatherResultsModel.WeatherDailyModel weatherModelRight = mDatas.get(position + 1);
            low[2] = (weatherModel.getLow() + weatherModelRight.getLow()) / 2;
            high[2] = (weatherModel.getHigh() + weatherModelRight.getHigh()) / 2;
        }
        holder.weatherLineView.setLowHighData(low, high);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class WeatherDataViewHolder extends RecyclerView.ViewHolder {

        TextView dayText;
        ImageView dayIcon;
        WeatherLineView weatherLineView;
        ImageView nighticon;
        TextView nightText;

        public WeatherDataViewHolder(View itemView) {
            super(itemView);
        }
    }
}
