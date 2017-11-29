package com.experiment.com.cameratest_2nd_trial;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.widget.Toast;

/**
 * Created by adaptive on 9/6/17.
 */

public class AutoFitTextureView extends TextureView{

    private int mRatioWidth = 0;
    private int mRatioHeight = 0;
    private Context mContext;

    public AutoFitTextureView(Context context) {
        this(context, null);
        mContext = context;
    }

    public AutoFitTextureView(Context context, AttributeSet attrs) {
        super(context, attrs , 0);
        mContext = context;
    }

    public AutoFitTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }
        mRatioWidth = width;
        mRatioHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //Toast.makeText(mContext , "initWidth : "+width+"\ninitHeight : "+height,Toast.LENGTH_SHORT).show();
        if (0 == mRatioWidth || 0 == mRatioHeight) {
            setMeasuredDimension(width, height);
        } else {
            if (width < height * mRatioWidth / mRatioHeight) {
                //Toast.makeText(mContext , "rWidth : "+mRatioWidth+"\nrHeight : "+mRatioHeight,Toast.LENGTH_SHORT).show();
                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
            } else {
                //Toast.makeText(mContext , "calcWidth-e : "+height * mRatioWidth / mRatioHeight+"\ncalcHeight-e : "+height,Toast.LENGTH_SHORT).show();
                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
            }
        }
    }
}
