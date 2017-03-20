package com.example.think.toutiao.ui.pullresfresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.think.toutiao.R;

/**
 * Created by Think on 2017/3/6.
 */

public class RectProgressView extends View {

    public int progress;

    public Paint paint;
    public int width;
    public int height;
    public Type type = Type.stroke;
    private int color = Color.GRAY;
    private int strokeWidth = 2;
    private int max = 100;

    public enum Type {fill, stroke, line}

    public RectProgressView(Context context) {
        super(context);
    }

    public RectProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RectProgressView, 0, 0);
            try {
                this.progress = ta.getInt(R.styleable.RectProgressView_progress, 0);
                int type = ta.getInt(R.styleable.RectProgressView_RectProgressViewStyle, 0);
                strokeWidth = ta.getInt(R.styleable.RectProgressView_strokeWidth, 0);
                this.type = Type.values()[type];
                color = ta.getInt(R.styleable.RectProgressView_color, 0);
                max = ta.getInt(R.styleable.RectProgressView_max, 100);
            } finally {
                ta.recycle();
            }
        }

        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        if (this.type == Type.fill) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        } else {
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    public RectProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int progress = (int) (this.progress * 100f / max);
        Path path = new Path();
        if (type == Type.stroke) {
            if (progress <= 25) {
                path.lineTo(progress / 25f * width, 0);
            } else if (progress <= 50) {
                path.lineTo(width, 0);
                path.lineTo(width, (progress - 25) / 25f * height);
            } else if (progress <= 75) {
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo((75 - progress) / 25f * width, height);
            } else if (progress <= 100) {
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.lineTo(0, (100 - progress) / 25f * height);
            }
        } else if (type == Type.fill) {
            if (progress <= 25) {
                path.reset();
            } else if (progress > 25 && progress <= 50) {
                path.lineTo(width, 0);
                path.lineTo(width, (progress - 25) / 25f * height);
            } else if (progress <= 75) {
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo((75 - progress) / 25f * width, height);
            } else if (progress <= 100) {
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height);
                path.lineTo(0, 0);
            }
            path.close();
        } else {
            path.moveTo(0, height / 2);
            path.lineTo(progress / 100f * width, height / 2);
        }
        canvas.drawPath(path, paint);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress > max)
            progress = max;
        else if (progress < 0)
            progress = 0;
        if (this.progress != progress) {
            this.progress = progress;
            postInvalidate();
        }
    }
}
