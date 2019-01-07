package com.piano.www.jxdialog.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;

import com.piano.www.jxdialog.R;
import com.piano.www.jxdialog.base.JxBaseDialog;

/**
 * <仅仅是一个小例子，解释如何使用JxBaseDialog，快速建立一个Dialog>
 *
 * @Project: DialogDemo
 * @author: cjx
 * @date: 2019-01-04 13:49  星期五
 */
public class CustomDialogDemo extends JxBaseDialog {
    public CustomDialogDemo(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void setDialogAttribute() {
        setDimAmount(0.0f);//设置背景模糊程度
        setGravity(Gravity.CENTER);//对话框位置
        setCancelable(true);//对话框可不可以取消
        setCanceledOnTouchOutside(false);//对话框外部可不可以触摸
        setNoTitle();


//注意：↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        setDialogWidtHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//不是宽度全屏幕可以不写这句话，因为默认宽度wrap_content，高度wrap_content
//        setFullScreen();
//        setStatusTranslucent();// 这个透明状态栏，也会使得入场动画不正常
        setAnimation(R.style.BottomEnterBottomExit);

        //高度为MATCH_PARENT，FullScreen为false--》进场动画正常了√√√,只是状态栏为灰色
        //高度为MATCH_PARENT，FullScreen为true--》进场动画正常了√√√,只是状态栏会隐藏，有闪跳

        //高度为WRAP_CONTENT，FullScreen为true，并且setStatusTranslucent为false--》进场动画正常了√√√
        //高度为WRAP_CONTENT，FullScreen为false--》进场动画不正常了xxx，有的手机表现为第一次不正常，以后正常

//      ★★★★★★★★★★ 高度为WRAP_CONTENT，FullScreen必须为true，setStatusTranslucent为false★★★★★★★★★★

//        ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    }

    @Override
    protected Object setDialogContentView() {
        return R.layout.dialog_custom;
    }

    @Override
    protected void initView() {
        // TODO: 2019/1/4  do sth
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    @Override
    public void onShow(DialogInterface dialog) {

    }
}
