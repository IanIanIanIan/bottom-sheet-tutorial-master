package com.androidtutorialshub.bottomsheets;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;


public class NestedScrollViewBehavior extends AppBarLayout.ScrollingViewBehavior {

    private BottomSheetBehavior bottomSheetBehavior;

    /**
     * 构造方法
     *
     * @param context context
     * @param attrs   attrs
     */
    public NestedScrollViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }




    /**
     * 是否正在滑动
     **/
    private boolean mIsBeingDragged;
    /**
     * 上次Down事件X方向值
     **/
    private int mLastMotionDownX;
    /**
     * 上次事件Y方向值
     **/
    private int mLastMotionY;
    /**
     * 滑动临界值
     **/
    private int mTouchSlop = -1;

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        //手指上滑
        getbottomsheet(parent);
        if (mTouchSlop < 0) {
            mTouchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
            Log.e("123", "onInterceptTouchEvent: mTouchSlop" + mTouchSlop);

        }
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                int y = (int) ev.getY();
                mLastMotionY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int y = (int) ev.getY();
                int yDiff = mLastMotionY-y;
                if (yDiff > mTouchSlop &&isBottomed(child)) {


                    if (bottomSheetBehavior!=null) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
                break;
            }
            default:
                break;
        }
        return super.onInterceptTouchEvent(parent,child,ev);
    }



    private void getbottomsheet(CoordinatorLayout parent) {
        NestedScrollView scrollView = null;
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            if (null != view) {
                if (view instanceof NestedScrollView) {
                    if (view.getId() == R.id.bottom_sheet) {
                        scrollView = (NestedScrollView) view;
                    }
                }
            }
        }

        if (null != scrollView) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) scrollView.getLayoutParams();
            if (params.getBehavior() instanceof BottomSheetBehavior) {
                bottomSheetBehavior = (BottomSheetBehavior) params.getBehavior();
            }

        }
    }


    /**
     * view是否到底部了
     *
     * @return  true;
     */
    protected boolean isBottomed(View view) {
        return !view.canScrollVertically(1);
    }

/*
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return isDependency(dependency);
    }



    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (isDependency(dependency)) {
            child.setTranslationY(dependency.getTranslationY());
        }
        return false;
    }



    @Override
    public View findFirstDependency(List<View> views) {
        if (views != null) {
            for (int i = 0; i < views.size(); i++) {
                if (isDependency(views.get(i))) {
                    return views.get(i);
                }
            }
        }
        return null;
    }*/

}
