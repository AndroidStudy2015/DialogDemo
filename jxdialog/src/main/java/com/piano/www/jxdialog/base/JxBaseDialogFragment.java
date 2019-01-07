package com.piano.www.jxdialog.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.piano.www.jxdialog.R;

/**
 * 为什么写这个类，因为Diaolog坑太深，莫名其妙的坑，只能记录下来
 * 继承了这个类后，一定参照 CustomFragmentDiaolog 去写，直接复制就可，如果要处理进入场动画，要仔细阅读 CustomFragmentDiaolog
 *
 * DialogFragment基类，继承这个类
 * 可以解决自定义DialogFragment尺寸显示不全问题
 * 注意：
 * 可通过两种方式来创建DialogFragment
 * 1.onCreateView
 * ----->   1. ▲ ▲ ▲ ▲ ▲★★★★★此种方法的getDialog().setCanceledOnTouchOutside(false)有效， ▲ ▲ ▲ ▲ ▲★★★★★
 * ----->   2. ▲ ▲ ▲ ▲ ▲★★★★★此种方法的getDialog().setCancelable(false)无效，也就是说，当按下回退键会关闭对话框， ▲ ▲ ▲ ▲ ▲★★★★★
 * ----->       ▲ ▲ ▲ ▲ ▲★★★★★ 是因为你用错了，要★直接用setCancelable(false) ▲ ▲ ▲ ▲ ▲★★★★★ ，
 * ----->       ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ 也就是说用DialogFragment的setCancelable(false)就好了 ▲ ▲ ▲ ▲ ▲★★★★★
 * <p>
 * <p>
 * ----->   3.▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ 小米全面屏回到桌面时候防止圆角出现，要给你要显示的布局外再次套一个透明的布局，并且要设置5dp的padding▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲
 * <p>
 * ----->   参照：https://blog.csdn.net/elder_sword/article/details/51886297
 * <p>
 * 2.onCreateDialog（不讨论）
 * 本类知识抽象了1.onCreateView创建的方式
 * <p>
 * 显示使用 ： CustomFragmentDiaolog.show(getSupportFragmentManager(), "tag");
 * <p>
 * <p>
 * <p>
 * 请参照：
 * 1. Android 必知必会 - DialogFragment 使用总结
 * https://blog.csdn.net/ys743276112/article/details/52962046?utm_source=blogxgwz3
 * 2. Android-->Dialog/DialogFragment宽度高度修改/全屏,自定义样式
 * https://blog.csdn.net/angcyo/article/details/50613084
 * <p>
 * * 自定义对话框布局注意事项：（重要）
 * * 1.如果布局使用了具体的像素值座位宽高，那么setDialogWidtHeight(wrap_content, wrap_content)即可，或者不调用该方法也行，因为默认就是wrap_content
 * * 2.如果布局使用了match_parent，那么必须调用setDialogWidtHeight(match_parent, match_parent)，不然不会有全屏幕效果
 * * 3.高度除非要全屏幕隐藏状态栏，不然一般不需要设置为match_parent，setDialogWidtHeight(xxx, match_parent)
 *
 * @Project: DialogDemo
 * @author: cjx
 * @date: 2019-01-04 15:13  星期五
 */


public abstract class JxBaseDialogFragment extends AppCompatDialogFragment {

    protected Window mWindow;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ★ AppCompatDialogFragment只能在这里设置theme,不设置的话小米切到任务栏模式会出现阴影
//        设置R.style.customDialog之后，避免了小米全面屏手机上滑回到任务模式后，对话框出现缩小的阴影的情况
//         子类如果想更改样式的话，可以重写onCreate方法，在其内部调用setStyle方法

        setStyle(AppCompatDialogFragment.STYLE_NO_TITLE, R.style.customDialog);//必须设置

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 设置宽度为屏宽、靠近屏幕底部。
        mWindow = getDialog().getWindow();
        if (mWindow != null) {
//            mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处,必须设置背景透明（因为R.style.customDialog设置了，这里可以取消）
//            mWindow.getDecorView().setPadding(0, 0, 0, 0);//必须在设置宽高window.setLayout之前，不设置宽度显示不全（设置R.style.customDialog后，这里可以不设置）
            mWindow.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//这一步是真正地设置了对话框的宽高，不然对话框会很小
//        上面的setLayout方法是这一步是真正地设置了对话框的宽高，默认为WRAP_CONTENT，只要你的对话框布局不是使用了MATCH_PARENT,就可以使用默认设置
//            如果你的对话框布局使用的是MATCH_PARENT，那么请在子类里调用setDialogWidtHeight（MATCH_PARENT，MATCH_PARENT）设置尺寸，不然不会得到MATCH_PARENT的效果

        }
        setDialogAttribute();


        Object dialogContentView = setDialogContentView();
        View view;
        if (dialogContentView instanceof Integer) {
            view = inflater.inflate((Integer) setDialogContentView(), container, false);
        } else if (dialogContentView instanceof View) {
            view = ((View) dialogContentView);
        } else {
            throw new RuntimeException("setDialogContentView()必须传入资源ID或者View");
        }


        initView(view);

        return view;
    }

    /**
     * 透明状态栏
     */

    public void setStatusTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 无标题
     */

    public void setNoTitle() {
        mWindow.requestFeature(Window.FEATURE_NO_TITLE);//必须放在setContextView之前调用
    }

    /**
     * 全屏幕
     */

    public void setFullScreen() {
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 对话框进出场动画
     *
     * @param resId
     */
    public void setAnimation(@StyleRes int resId) {

        mWindow.setWindowAnimations(resId);
    }


    /**
     * 设置对话框的资源ID或者View
     *
     * @return
     */
    protected abstract Object setDialogContentView();

    /**
     * 初始化view
     *
     * @param view
     */
    protected abstract void initView(View view);


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
     * 已经默认设置了dialog的宽度为match_parent，高度为wrap_content,适用于大多数情况
     * 如果你想要高度是match_parent的话，必须需要在这里设置
     * 如果你想设置具体的值也可以在这里设置，但是可以不设置直接用默认就行
     *
     * @param width
     * @param height
     */
    protected void setDialogWidtHeight(int width, int height) {
        mWindow.setLayout(width, height);
    }
//    下面是使用创建Dialog的方式的正确配置，现存在这里
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        Dialog dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.dialog_progress);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//如果style设置，这里就不设置了
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        final CustomDialogDemo dialogDemo=new CustomDialogDemo(getContext());
//
//        return dialogDemo;
//    }


}
