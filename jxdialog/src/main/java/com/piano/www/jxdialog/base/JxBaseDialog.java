package com.piano.www.jxdialog.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.piano.www.jxdialog.R;

/**
 *
 * 为什么写这个类，因为Diaolog坑太深，莫名其妙的坑，只能记录下来
 * 继承了这个类后，一定参照 CustomDialogDemo 去写，直接复制就可，如果要处理进入场动画，要仔细阅读 CustomDialogDemo
 *
 * Dialog基类，继承这个类
 * 可以解决自定义dialog尺寸显示不全问题
 * 请参照：
 * 1. Android-->Dialog/DialogFragment宽度高度修改/全屏,自定义样式
 * https://blog.csdn.net/angcyo/article/details/50613084
 */
public abstract class JxBaseDialog extends Dialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {


    protected Window mWindow;

    public JxBaseDialog(@NonNull Context context) {
        super(context,R.style.customDialog);//使用了默认的主题样式

    }

    public JxBaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);//可以自定义主题样式
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWindow = getWindow();

        // 能够全屏幕显示dialog，就靠下面的设置了
        // 1.setLayout必须 在 setContentView之后调用;
        // 2.并且 setBackgroundDrawable 必须设置,
        // 3.不设置setBackgroundDrawable，必须在style里设置 <item name="android:windowBackground">@android:color/transparent</item>
        if (mWindow != null) {
//            mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//如果style设置，这里就不设置了
            mWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            setDialogAttribute();
            setOnDismissListener(this);
            setOnShowListener(this);
        }


        Object dialogContentView = setDialogContentView();
        if (dialogContentView instanceof Integer) {
            setContentView((int) dialogContentView);
        } else if (dialogContentView instanceof View) {
            setContentView((View) dialogContentView);
        } else {
            throw new RuntimeException("setDialogContentView()必须传入资源ID或者View");
        }


        initView();
    }

    /**
     * 已经默认设置了dialog的宽度为wrap_content，高度为wrap_content,适用于大多数情况
     * 如果你想要是match_parent的话，必须需要在这里设置
     * 如果你想设置具体的值也可以在这里设置，但是可以不设置直接用默认就行
     *
     * @param width
     * @param height
     */
    public void setDialogWidtHeight(int width, int height) {
        mWindow.setLayout(width, height);
    }

    /**
     * 设置对话框显示位置
     *
     * @param gravity
     */
    protected void setGravity(int gravity) {
        mWindow.setGravity(gravity);
    }

    /**
     * 设置对话框背景模糊程度
     *
     * @param amount 0~1.0 之间取值
     */
    protected void setDimAmount(float amount) {
        mWindow.setDimAmount(amount);
    }

    /**
     * 在这配置对话框的一些特性例如：
     * setDimAmount：背景模糊程度
     * setGravity：对话框显示位置
     * setCancelable：能不能通过点击返回键关闭对话框
     * setCanceledOnTouchOutside：能不能点击对话框外部
     * ....
     */
    protected abstract void setDialogAttribute();

    /**
     * 设置对话框的资源ID或者View
     *
     * @return
     */
    protected abstract Object setDialogContentView();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 透明状态栏
     *
     */

    protected void setStatusTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 无标题
     */

    protected void setNoTitle() {
            mWindow.requestFeature(Window.FEATURE_NO_TITLE);//必须放在setContextView之前调用
    }

    /**
     * 全屏幕
     */

    protected void setFullScreen() {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    /**
     * 设置进出场动画
     * @param resId
     */
    public void setAnimation(@StyleRes int resId) {
        mWindow.setWindowAnimations(resId);
    }
}