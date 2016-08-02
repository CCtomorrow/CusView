package yong.qing.com.qimingview.weatherview;

/**
 * <b>Project:</b> yong.qing.com.qimingview.weatherview <br>
 * <b>Create Date:</b> 2016/5/30 <br>
 * <b>Author:</b> qingyong <br>
 * <b>Description:</b>  <br>
 */
public class WeatherModel {
    public int lowest;
    public int highest;
    public int[] low;
    public int[] high;

    public WeatherModel(int lowest, int highest, int[] low, int[] high) {
        this.lowest = lowest;
        this.highest = highest;
        this.low = low;
        this.high = high;
    }
}
