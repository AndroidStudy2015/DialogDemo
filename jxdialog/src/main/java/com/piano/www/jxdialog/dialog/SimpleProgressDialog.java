package com.piano.www.jxdialog.dialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.piano.www.jxdialog.R;
import com.piano.www.jxdialog.base.JxBaseDialog;
import com.piano.www.jxdialog.utils.DisplayUtil;

/**
 * 一个简单的Progress对话框，
 * 就是一个图片在旋转
 * 可以自定义图片、宽高、转动速度、背景色模糊程度
 *
 * @Project: DialogDemo
 * @author: cjx
 * @date: 2019-01-03 15:50  星期四
 */
public class SimpleProgressDialog extends JxBaseDialog {

    private ObjectAnimator rotation;
    private int mDrawableResId = R.drawable.progress;
    private ImageView mImageView;
    private int mWidth = DisplayUtil.dip2px(getContext(), 50);
    private int mHeight = DisplayUtil.dip2px(getContext(), 50);
    private float mDim = 0.0f;
    /**
     * 转动速率，越大，越快
     * 建议取值为0.1~2.0之间（不能等于0）
     * 默认值为1.0
     */
    private float mVelocity = 1.0f;

    /**
     * 使用这个构造函数，将采用默认的图片、宽高、速率、背景色模糊程度
     *
     * @param context
     */
    public SimpleProgressDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * 使用这个构造函数，可以自定义图片,宽高、速率、背景色模糊程度采用默认值
     * @param context
     * @param drawableResId
     */
    public SimpleProgressDialog(@NonNull Context context, @DrawableRes int drawableResId) {
        super(context);
        mDrawableResId = drawableResId;
    }
    /**
     * 使用这个构造函数，可以自定义图片、宽高、速率、背景色模糊程度
     *
     * @param context
     * @param drawableResId 图片资源
     * @param width         图片宽度： 单位px   请注意传入经过dp转换后的值
     * @param height        图片高度： 单位px   请注意传入经过dp转换后的值
     * @param velocity      转动速度:建议取值为0.1~2.0之间（不能等于0）,默认值为1.0
     * @param dim           对话框背景模糊程度：建议取值为0.0~1.0之间,默认值为0.3
     */

    public SimpleProgressDialog(@NonNull Context context, @DrawableRes int drawableResId, int width, int height, float velocity, float dim) {
        super(context);
        mDrawableResId = drawableResId;
        mWidth = width;
        mHeight = height;
        mVelocity = velocity;
        mDim = dim;
    }

    @Override
    protected void setDialogAttribute() {
        setDimAmount(mDim);
        setGravity(Gravity.CENTER);
        setCancelable(true);
        setCanceledOnTouchOutside(false);

    }

    @Override
    protected Object setDialogContentView() {

        mImageView = new ImageView(getContext());
        mImageView.setImageResource(mDrawableResId);
        return mImageView;
    }

    @Override
    public void initView() {
        //为什么用FrameLayout.LayoutParams，因为dialog的父布局是一个FrameLayout.LayoutParams，使用其他的会报错
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(mWidth, mHeight);
        lp.gravity = Gravity.CENTER;
        mImageView.setLayoutParams(lp);

        rotation = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360 * 50);
        rotation.setDuration((long) (3000 * 30 / mVelocity));
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setRepeatCount(Animation.INFINITE);

    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        rotation.end();
    }

    @Override
    public void onShow(DialogInterface dialog) {
        rotation.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        rotation.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        rotation.end();

    }
}
