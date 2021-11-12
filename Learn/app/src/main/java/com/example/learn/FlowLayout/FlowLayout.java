package com.example.learn.FlowLayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.icu.util.Measure;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/*
自定义View onMeasure + onDraw
自定义ViewGroup onMeasure + onLayout
*/
public class FlowLayout extends ViewGroup {

    private static final  String TAG = "FlowLayout";
    private int mHorzontalSpacing = dp2px(16);
    private int mVerticalSpacing = dp2px(16);

    private  List<List<View>> allLines; //记录所有的行，一行一行的储存，用于layout
    List<Integer> lineHeights = new ArrayList<>(); //记录每一行的行高，用于layout

    //new 调用
    public FlowLayout(Context context) {
        super(context);
    }

    //xml转换成java代码是通过反射调用
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //切换主题 日间/夜间模式
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initMeasureParams() {
        if (allLines != null) {
            allLines.clear();
        } else {
            allLines = new ArrayList<>();
        }
        lineHeights = new ArrayList<>();
    }

    //度量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        initMeasureParams();

        //自定义View绘制流程
        //度量子类 将xml里面的layout_height或者layout_width 转化为 具体的值
        int childCount = getChildCount();
        Log.d("onLayout", "childCount = " + childCount );
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);  //ViewGroup解析的宽度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec); //ViewGroup解析的高度

        List<View> lineViews = new ArrayList<>(); //保存一行中的所有View

        int lineWidthUsed = 0;  //这行已经使用了多宽的size
        int lineHeight = 0; //一行的行高

        int parentNeededHeight = 0;
        int parentNeededWidth = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLP = childView.getLayoutParams();
            int childWidthMeasureSpc = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLP.width);
            int childHeightMeasureSpc = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLP.height);

            childView.measure(childWidthMeasureSpc, childHeightMeasureSpc);

           //获取子View的宽高
            int childMeasureWidth = childView.getMeasuredWidth();
            int childMeasureHeight = childView.getMeasuredHeight();

            //通过宽度度来判断是否需要换行，通过换行后的每行的行高来获取整个ViewGroup的行高
            //如果需要换行
            if (childMeasureWidth + lineWidthUsed + mHorzontalSpacing > selfWidth) {
                Log.d("onLayout", "selfWidth = " + selfWidth );
                allLines.add(lineViews);
                lineHeights.add(lineHeight);

                //一旦换行 可以判断当前行需要的宽和高来，所以此时要记录下来
                parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorzontalSpacing);

                lineViews = new ArrayList<>();  //换行置空
                lineWidthUsed = 0;
                lineHeight = 0;
            }

            //View是分行layout的，所以要记录每一行有哪些View，这样可以方便layout布局
            lineViews.add(childView);

            // 每行都有自己的宽高
            lineWidthUsed = lineWidthUsed + childMeasureWidth + mHorzontalSpacing ;
            lineHeight = Math.max(lineHeight, childMeasureHeight);
        }

        //记录最后一行的View和高度
        allLines.add(lineViews);
        lineHeights.add(lineHeight);
        //然后重新计算parent的高和宽
        parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
        parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorzontalSpacing);

        Log.d("onLayout", "lineViews = " + lineViews );
        Log.d("onLayout", "alllines = " + allLines );
        //根据子VIew的度量结果，来重新度量自己的ViewGroup
        //作为一个VIewGroup，它自己也是一个View，它的大小也需要根据它父亲给他提供的宽高来度量
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeededHeight;

        //度量自己
        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int curL = getPaddingLeft();
        int curT = getPaddingRight();

        int lineCount = allLines.size();
        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = allLines.get(i);
            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curL = right + mHorzontalSpacing;
            }
            curL = getPaddingLeft();
            curT = curT + lineHeight + mVerticalSpacing;
            Log.d("onLayout", "curT = " + curT );
        }
    }

    public static int dp2px(int dp) {
        return(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
