package yong.qing.com.qimingview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import yong.qing.com.qimingview.R;

/**
 * <b>Project:</b> yong.qing.com.qimingview <br>
 * <b>Create Date:</b> 2016/3/28 <br>
 * <b>Author:</b> Devin <br>
 * <b>Description:</b> 交替的Loading <br>
 */
public class SimpleLoading extends View {

    private int mFirstColor; // 第一圈颜色
    private int mSecondColor; // 第二圈颜色
    private int mRadius; // 圆的半径
    private int mCircleWidth; // 圆环宽度
    private int mSpeed; // 速度
    private Paint mPaint;
    private RectF mOval;
    private int mProgress = 0; // 当前进度

    private boolean mNeedStop = false;

    private Thread mThread;

    public SimpleLoading(Context context) {
        this(context, null);
    }

    public SimpleLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOval = new RectF();
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (mNeedStop) {
                        break;
                    }
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        int tmp = mFirstColor;
                        mFirstColor = mSecondColor;
                        mSecondColor = tmp;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mNeedStop = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mNeedStop = false;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            mNeedStop = false;
            if (!mThread.isAlive()) {
                Thread.State state = mThread.getState();
                if (state == Thread.State.NEW)
                    mThread.start();
            }
        } else {
            mNeedStop = true;
        }
    }

    /**
     * 初始化样式
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleLoading, defStyleAttr, 0);
        mFirstColor = a.getColor(R.styleable.SimpleLoading_firstColor, Color.GREEN);
        mSecondColor = a.getColor(R.styleable.SimpleLoading_secondColor, Color.RED);
        mRadius = (int) a.getDimension(R.styleable.SimpleLoading_radius,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics()));
        mCircleWidth = (int) a.getDimension(R.styleable.SimpleLoading_circleWidth,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics()));
        mSpeed = a.getInt(R.styleable.SimpleLoading_speed, 16);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = getSize(widthMode, widthSize, 0);
        int height = getSize(heightMode, heightSize, 1);

        setMeasuredDimension(width, height);
    }

    /**
     * @param mode Mode
     * @param size Size
     * @param type 0表示宽度，1表示高度
     * @return 宽度或者高度
     */
    private int getSize(int mode, int size, int type) {
        // 默认
        int result;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            int whSize = (mRadius + mCircleWidth / 2) * 2;
            if (type == 0) {
                result = whSize + getPaddingLeft() + getPaddingRight();
            } else {
                result = whSize + getPaddingTop() + getPaddingBottom();
            }

            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centre = getWidth() / 2; // 获取圆心的x坐标
        // int radius = centre - mCircleWidth / 2;// 半径
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        // mOval.set(centre - radius, centre - radius, centre + radius, centre + radius);  // 用于定义的圆弧的形状和大小的界限
        mOval.set(centre - mRadius, centre - mRadius, centre + mRadius, centre + mRadius);  // 用于定义的圆弧的形状和大小的界限
        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        // canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
        canvas.drawCircle(centre, centre, mRadius, mPaint); // 画出圆环
        mPaint.setColor(mSecondColor); // 设置圆环的颜色
        canvas.drawArc(mOval, -90, mProgress, false, mPaint); // 根据进度画圆弧
    }
}
