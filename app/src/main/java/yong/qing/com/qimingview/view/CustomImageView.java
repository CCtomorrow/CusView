package yong.qing.com.qimingview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import yong.qing.com.qimingview.R;

/**
 * <b>Project:</b> yong.qing.com.qimingview <br>
 * <b>Create Date:</b> 2016/3/13 <br>
 * <b>Author:</b> Devin <br>
 * <b>Description:</b>  <br>
 */
public class CustomImageView extends View {

    private String mText; // 介绍文字
    private int mTextColor; // 颜色
    private int mTextSize; // 大小
    private int mScaleType; // 0 fillXY 1 center
    private Bitmap mBitmap; // 图片

    private Rect mRect; // 总的大小
    private Paint mPaint;
    private TextPaint mTextPaint;
    private Rect mTextBounds; // 文字所占的大小

    private int mWidth;
    private int mHeight;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint();
        mRect = new Rect();
        mTextBounds = new Rect();
        mPaint.setTextSize(mTextSize);
        // 绘制文字需要的范围
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
    }

    private void initStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        mText = a.getString(R.styleable.CustomImageView_text);
        mTextColor = a.getColor(R.styleable.CustomImageView_textColor, Color.BLACK);
        int defaultSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                16, getResources().getDisplayMetrics());
        mTextSize = a.getDimensionPixelSize(R.styleable.CustomImageView_textSize, defaultSize);
        mScaleType = a.getInt(R.styleable.CustomImageView_imageScaleType, 0);
        mBitmap = BitmapFactory.decodeResource(getResources(), a.getResourceId(R.styleable.CustomImageView_imgSrc, 0));
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
     * @param mode
     * @param size
     * @param type 0宽度 1高度
     * @return
     */
    private int getSize(int mode, int size, int type) {
        int result;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            if (type == 0) {
                int textWidth = getPaddingLeft() + getPaddingRight() + mTextBounds.width();
                int imgWidth = getPaddingLeft() + getPaddingRight() + mBitmap.getWidth();
                result = Math.max(textWidth, imgWidth);
            } else {
                int textHeight = mTextBounds.height();
                int imgHeight = mBitmap.getHeight();
                result = getPaddingTop() + getPaddingBottom() + textHeight + imgHeight;
            }
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        if (type == 0) {
            mWidth = result;
        } else {
            mHeight = result;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 边框
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(4);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        // 确定绘制的边缘
        mRect.left = getPaddingLeft();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();
        mRect.left = mWidth - getPaddingRight();

        // 文字
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        if (mTextBounds.width() > mWidth) {
            mTextPaint.set(mPaint);
            String msg = TextUtils.ellipsize(mText, mTextPaint, mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(mText, (mWidth - mTextBounds.width()) / 2, mHeight - getPaddingBottom(), mPaint);
        }

        // 图片
        mRect.bottom -= mTextBounds.height();
        if (mScaleType == 0) {
            canvas.drawBitmap(mBitmap, null, mRect, mPaint);
        } else {
            //计算居中的矩形范围
            mRect.left = mWidth / 2 - mBitmap.getWidth() / 2;
            mRect.right = mWidth / 2 + mBitmap.getWidth() / 2;
            mRect.top = (mHeight - mTextBounds.height()) / 2 - mBitmap.getHeight() / 2;
            mRect.bottom = (mHeight - mTextBounds.height()) / 2 + mBitmap.getHeight() / 2;
            canvas.drawBitmap(mBitmap, null, mRect, mPaint);
        }
    }
}
