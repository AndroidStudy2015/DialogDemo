package com.piano.www.jxpopupwindows;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;


/**
 * Created by apple on 2017/9/25.
 */
//用法示例
// PopupWindowEngine
//         .builder()
//         .with(getContext())
//         .outsideTouchable(true)//外部可不可以触摸
//         .animationStyle(AnimationStyle.BOTTOM_ENTER_BOTTOM_EXIT)//popwindos进出的动画
//         .rootView(mView)//popwindow所附着的的根view
//         .contetView(R.layout.ccc)//popwindow本身的view，可以穿入view，也可以穿入布局id
//         .contetViewWidth(ViewGroup.LayoutParams.MATCH_PARENT)
//         .contetViewHeight(100)
//         .build()
//         .showInParent(Gravity.CENTER,0,0);
//         .showAsDropDown(view,100,100)

public class PopupWindowEngine {
    private Context mContext;
    private View mRootView;

    public View getContentView() {
        return mContentView;
    }

    private View mContentView;
    private int mContentViewWidth;
    private int mContentViewHeight;
    private boolean mOutsideTouchable;
    private AnimationStyle mAnimationStyle;

    private IBindPopupWindow mIBindePopupWindow;

    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    private PopupWindow mPopupWindow;

    public static PopupWindowBuilder builder() {
        return new PopupWindowBuilder();
    }


    PopupWindowEngine(Context context, View rootView, Object contentView, int contentViewWidth, int contentViewHeight, boolean outsideTouchable, AnimationStyle animationStyle, IBindPopupWindow iBindePopupWindow) {
        mContext = context;
        mRootView = rootView;

        mContentViewWidth = contentViewWidth;
        mContentViewHeight = contentViewHeight;
        mOutsideTouchable = outsideTouchable;
        mAnimationStyle = animationStyle;
        mIBindePopupWindow = iBindePopupWindow;
        //1.初始化PopupWindow
        mPopupWindow = new PopupWindow(mContentView,
                mContentViewWidth, mContentViewHeight, true);
        //得到contentView
        if (contentView instanceof Integer) {
            mContentView = LayoutInflater.from(context).inflate((Integer) contentView, null);

        } else if (rootView instanceof View) {
            mContentView = (View) contentView;
        } else {
            throw new ClassCastException("必须传入一个布局资源R.layout.xxx，或者是一个View类型");
        }


        //2.为PopupWindow设置contentView
        mPopupWindow.setContentView(mContentView);


        //3.PopupWindow外部是否可以点击

        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);

        if (!outsideTouchable)

        {
            mPopupWindow.setFocusable(false);//必须Focusable设置为false，setOutsideTouchable设置为false点击外部才不会消失 ,http://blog.csdn.net/qq402164452/article/details/53353798
            mPopupWindow.setOutsideTouchable(false);
        }

//        要想让点击外部可以取消pop，必须给他家一个透明背景
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        //4.设置动画所对应的style
        if (mAnimationStyle != null) {
            switch (mAnimationStyle)

            {
                case BOTTOM_ENTER_BOTTOM_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.BottomEnterBottomExit);
                    break;
                case LEFT_ENTER_LEFT_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.LeftEnterLeftExit);
                    break;
                case RIGHT_ENTER_RIGHT_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.RightEnterRightExit);
                    break;
                case TOP_ENTER_TOP_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.TopEnterTopExit);
                    break;
                case BOTTOM_ENTER_TOP_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.BottomEnterTopExit);
                    break;

                case TOP_ENTER_BOTTOM_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.TopEnterBotttomExit);
                    break;

                case LEFT_ENTER_RIGHT_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.LeftEnterRightExit);
                    break;

                case RIGHT_ENTER_LEFT_EXIT:
                    mPopupWindow.setAnimationStyle(R.style.RightEnterLeftExit);
                    break;

                default:

                    break;

            }
        }
        //5. 用于使用者操作mContentView
        if (mContentView != null && mIBindePopupWindow != null) {
            mIBindePopupWindow.onBindPopupWindow(mPopupWindow, mContentView);
        }

    }

    /**
     * 显示在父View中的位置
     *
     * @param gravity 可以同时指定两个位置,例如：右下方Gravity.RIGHT|Gravity.BOTTOM
     * @param x       以指定的gravity座位基准点，即坐标（0，0）原点，不一定会右为正值
     * @param y       以指定的gravity座位基准点，即坐标（0，0）原点，不一定是下为正值
     */
    public void showInParent(int gravity, int x, int y) {
        mPopupWindow.showAtLocation(mRootView, gravity, x, y);
//        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * 显示在指定的View的下方
     *
     * @param anchor 所要锚定的View
     * @param xoff   x方向的偏移量
     * @param yoff   y方向的偏移量
     */
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        mPopupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    public void dismissPopupWindow() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }


}
