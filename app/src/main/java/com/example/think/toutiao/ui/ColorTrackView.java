package com.example.think.toutiao.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.think.toutiao.R;
import com.example.think.toutiao.util.common.SizeUtils;

/**
 * Created by Think on 2017/3/11.
 */

public class ColorTrackView extends View {

    private int mTextStartX;

    private int mDirection = DIRECTION_LEFT;

    private static final int DIRECTION_LEFT = 0;
    private static final int DIRECTION_RIGHT = 1;

    public void setDirection(int direction) {
        mDirection = direction;
    }

    private String mText = "张鸿洋";
    private Paint mPaint;
    private int mTextSize = SizeUtils.sp2px(30);
    private int mTextOriginColor = 0xff000000;
    private int mTextChangeColor = 0xffff0000;
    private Rect mTextBound = new Rect();
    private int mTextWidth;
    private int mRealWidth;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    private float progress;

    public ColorTrackView(Context context) {
        super(context, null);
    }

    public ColorTrackView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.ColorTrackView);
        mText = ta.getString(R.styleable.ColorTrackView_text);
        mTextSize = ta.getDimensionPixelSize(
                R.styleable.ColorTrackView_text_size, mTextSize);
        mTextOriginColor = ta.getColor(
                R.styleable.ColorTrackView_text_origin_color,
                mTextOriginColor);
        mTextChangeColor = ta.getColor(
                R.styleable.ColorTrackView_text_change_color,
                mTextChangeColor);
        progress = ta.getFloat(R.styleable.ColorTrackView_progress_ctv, 0);
        mDirection = ta.getInt(R.styleable.ColorTrackView_direction, mDirection);
        ta.recycle();
        mPaint.setTextSize(mTextSize);
        measureText();
    }

    private void measureText() {
        mTextWidth = (int) mPaint.measureText(mText);
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    public void setText(String text) {
        this.mText = text;
        measureText();
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        setMeasuredDimension(suggestedMinimumWidth, suggestedMinimumHeight);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mTextStartX = mRealWidth / 2 - mTextWidth / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = (int) (progress * mTextWidth + mTextStartX);

        if (mDirection == DIRECTION_LEFT) {
            drawChangeLeft(canvas, r);
            drawOriginLeft(canvas, r);
        } else {
            drawOriginRight(canvas, r);
            drawChangeRight(canvas, r);
        }
    }

    private void drawChangeRight(Canvas canvas, int r) {
        drawText(canvas, mTextChangeColor, (int) (mTextStartX + (1 - progress) * mTextWidth), mTextStartX + mTextWidth);
    }

    private void drawOriginRight(Canvas canvas, int r) {
        drawText(canvas, mTextOriginColor, mTextStartX, (int) (mTextStartX + (1 - progress) * mTextWidth));
    }

    private void drawChangeLeft(Canvas canvas, int r) {
        drawText(canvas, mTextChangeColor, mTextStartX, (int) (mTextStartX + progress * mTextWidth));
    }

    private void drawOriginLeft(Canvas canvas, int r) {
        drawText(canvas, mTextOriginColor, (int) (mTextStartX + progress * mTextWidth), mTextStartX + mTextWidth);
    }

    private void drawText(Canvas canvas, int color, int startX, int endX) {
        mPaint.setColor(color);
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(startX, 0, endX, getMeasuredHeight());
        canvas.drawText(mText, mTextStartX, getMeasuredHeight() / 2
                + mTextBound.height() / 2, mPaint);
        canvas.restore();
    }
}
