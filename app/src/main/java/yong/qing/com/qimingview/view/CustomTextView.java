package yong.qing.com.qimingview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import yong.qing.com.qimingview.R;

/**
 * <b>Project:</b> yong.qing.com.qimingview <br>
 * <b>Create Date:</b> 2016/3/13 <br>
 * <b>Author:</b> Devin <br>
 * <b>Description:</b> 自定义的TextView 点击四个数字替换的 <br>
 */
public class CustomTextView extends View {

    private String mText; // 文字
    private int mTextColor; // 颜色
    private int mTextSize; // 大小

    private TextPaint mPaint; // 画笔
    private Rect mRect; // 文字需要的空间大小

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context, attrs, defStyleAttr);
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize);
        mRect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mRect);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = randomText();
                postInvalidate();
            }
        });
    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }
        return sb.toString();
    }

    /**
     * 返回当前的字符串
     *
     * @return
     */
    public String getText() {
        return mText;
    }

    private void initStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextViewStyle, defStyleAttr, 0);
        mText = a.getString(R.styleable.CustomTextViewStyle_text);
        mTextColor = a.getColor(R.styleable.CustomTextViewStyle_textColor, Color.BLACK);
        int defaultSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                16, getResources().getDisplayMetrics());
        mTextSize = a.getDimensionPixelSize(R.styleable.CustomTextViewStyle_textSize, defaultSize);
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
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            int whSize;
            if (type == 0) {
                whSize = mRect.width();
                result = whSize + getPaddingLeft() + getPaddingRight();
            } else {
                whSize = mRect.height();
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
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTextColor);
        float basex = (getMeasuredWidth() - mRect.width()) / 2 - 2;
        // float basey = getHeight() / 2 + mRect.height() / 2;
        float basey = (getMeasuredHeight() - (mPaint.ascent() + mPaint.descent())) / 2 + 2;
        canvas.drawText(mText, basex, basey, mPaint);
    }
}
