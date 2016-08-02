package yong.qing.com.qimingview.timeLine;

import android.graphics.drawable.Drawable;

/**
 * <b>Project:</b> yong.qing.com.qimingview.model <br>
 * <b>Create Date:</b> 2016/4/10 <br>
 * <b>Author:</b> Devin <br>
 * <b>Description:</b> 时光轴的item <br>
 * timeLineIcon:事件对应的icon，比如生日对应的icon
 * timeLineName:事件的名称，比如生日
 * timeLineDate:事件的日期
 * timeLineTime:事件的日期距离现在的时间
 */
public class TimeLineItem {
    public Drawable timeLineIcon;
    public String timeLineName;
    public String timeLineDate;
    public String timeLineTime;

    public TimeLineItem() {
    }

    public TimeLineItem(String timeLineDate, Drawable timeLineIcon,
                        String timeLineName, String timeLineTime) {
        this.timeLineIcon = timeLineIcon;
        this.timeLineName = timeLineName;
        this.timeLineDate = timeLineDate;
        this.timeLineTime = timeLineTime;
    }

}
