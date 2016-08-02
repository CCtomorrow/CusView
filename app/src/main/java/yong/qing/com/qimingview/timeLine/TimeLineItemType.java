package yong.qing.com.qimingview.timeLine;

/**
 * <b>Project:</b> yong.qing.com.qimingview.model <br>
 * <b>Create Date:</b> 2016/4/10 <br>
 * <b>Author:</b> Devin <br>
 * <b>Description:</b> 类型 <br>
 */
public class TimeLineItemType {
    //正常
    public final static int NORMAL = 0;
    //开始
    public final static int START = 1;
    //结束
    public final static int END = 2;
    //只有一条数据,那么beginLine和endLine都没有
    public final static int ATOM = 3;
}
