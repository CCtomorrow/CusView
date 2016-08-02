package yong.qing.com.qimingview.zhihudetail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * <b>Project:</b> yong.qing.com.qimingview <br>
 * <b>Create Date:</b> 2016/4/8 <br>
 * <b>Author:</b> Devin <br>
 * <b>Description:</b>  <br>
 */
public class CustomScrollerView extends ScrollView {

    private OnScrollListener mListener;

    private int downY;
    private int offsetY;

    public interface OnScrollListener {
        void onScroll(int scrollY);

        void onScrollToTop();

        void onScrollToBottom();
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mListener = listener;
    }

    public CustomScrollerView(Context context) {
        super(context);
    }

    public CustomScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScroll(t);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mListener != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //记录按下时的Y坐标
                    downY = (int) ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //记录滑动时的Y坐标
                    int moveY = (int) ev.getY();
                    //计算出一个差值
                    offsetY = moveY - downY;
                    downY = moveY;
                    break;
                case MotionEvent.ACTION_UP:
                    //当手指抬起时判断差值的大小
                    if (offsetY < 0) {//如果小于0，则说明用户手指向上滑动
                        mListener.onScrollToBottom();
                    } else {//如果大于0，则说明用户手指向下滑动
                        mListener.onScrollToTop();
                    }
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }
}
