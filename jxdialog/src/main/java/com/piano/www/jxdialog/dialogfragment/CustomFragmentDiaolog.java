package com.piano.www.jxdialog.dialogfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.piano.www.jxdialog.R;
import com.piano.www.jxdialog.base.JxBaseDialogFragment;

/**
 * <仅仅是一个小例子，解释如何使用JxBaseDialogFragment，快速建立一个DialogFragment>
 * <p>
 * 自定义对话框布局注意事项：
 * 1.如果布局使用了具体的像素值座位宽高，那么setDialogWidtHeight(wrap_content, wrap_content)即可，或者不调用该方法也行，因为默认就是wrap_content
 * 2.如果布局使用了match_parent，那么必须调用setDialogWidtHeight(match_parent, match_parent)，不然不会有全屏幕效果
 * 3.高度除非要全屏幕隐藏状态栏，不然一般不需要设置为match_parent，setDialogWidtHeight(xxx, match_parent)
 *
 * @Project: DialogDemo
 * @author: cjx
 * @date: 2019-01-04 16:59  星期五
 */
public class CustomFragmentDiaolog extends JxBaseDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果想设置其他主题，可以重写onCreate，在这里设置你的主题,
        // 默认主题是（AppCompatDialogFragment.STYLE_NO_TITLE, R.style.customDialog）
//        setStyle(AppCompatDialogFragment.STYLE_NO_TITLE, R.style.customDialog);

    }

    @Override
    protected Object setDialogContentView() {
        return R.layout.dialog_custom;
    }

    @Override
    protected void initView(View view) {
        // TODO: 2019/1/4 do sth
    }


    @Override
    protected void setDialogAttribute() {

        setGravity(Gravity.CENTER);//对话框位置
        setDimAmount(0.3f);//设置背景模糊度
//        getDialog().setCancelable(false);//无效，还是可以点击回退键关闭对话框,
        setCancelable(true);//★★★★★★★★★★★★生效，记得用这个不要用上面的★★★★★★★★★★★★★★
        getDialog().setCanceledOnTouchOutside(true);//生效
        setNoTitle();


//注意：↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

        setDialogWidtHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//不是宽度全屏幕宽度最好不写这句话，因为默认宽度wrap_content，高度wrap_content,
//        setFullScreen();
        setAnimation(R.style.LeftEnterRightExit);
        //高度为MATCH_PARENT，FullScreen为false--》进场动画正常了√√√,只是状态栏为灰色
        //高度为MATCH_PARENT，FullScreen为true--》进场动画不正常了xxx，有的手机也会是正常√√√，只是有闪跳

        //高度为WRAP_CONTENT，FullScreen为true，并且setStatusTranslucent为false--》进场动画正常了√√√。。。
        //高度为WRAP_CONTENT，FullScreen为false--》进场动画不正常了xxx

//      ★★★★★★★★★★ √√√√√√√√√√√√√√√高度为MATCH_PARENT，FullScreen必须为false（有的手机true也行） √√√√√√√√√√√√√√√√√√★★★★★★★★★★
//      ★★★★★★★★★★ √√√√√√√√√√√√√√√高度为WRAP_CONTENT，FullScreen必须为true，setStatusTranslucent为false √√√√√√√√√√√√√√√√√√√√√★★★★★★★★★★
//        ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑



    }
}
