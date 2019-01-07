package com.piano.www.dialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.piano.www.jxdialog.dialog.CustomDialogDemo;
import com.piano.www.jxdialog.dialog.SimpleProgressDialog;
import com.piano.www.jxdialog.dialogfragment.CustomFragmentDiaolog;
import com.piano.www.jxpopupwindows.AnimationStyle;
import com.piano.www.jxpopupwindows.PopupWindowEngine;

public class MainActivity extends AppCompatActivity {

    private SimpleProgressDialog mSimpleProgressDialog;
    private CustomDialogDemo mCustomDialogDemo;
    private CustomFragmentDiaolog mCustomFragmentDiaolog;
    private PopupWindowEngine mWindowEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSimpleProgressDialog = new SimpleProgressDialog(
                this, R.drawable.progress);

        mCustomDialogDemo = new CustomDialogDemo(this);


        mCustomFragmentDiaolog = new CustomFragmentDiaolog();

        FrameLayout rootview = (FrameLayout) findViewById(R.id.rootview);

        //用法示例
        mWindowEngine = PopupWindowEngine
                .builder()
                .with(this)
                .outsideTouchable(true)
                .animationStyle(AnimationStyle.LEFT_ENTER_LEFT_EXIT)
                .rootView(rootview)
                .contetView(R.layout.dialog_custom)
                .contetViewWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .contetViewHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .build();





        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mSimpleProgressDialog.show();
//                mCustomDialogDemo.show();
//                mCustomFragmentDiaolog.show(getSupportFragmentManager(), "");
//


                mWindowEngine.showInParent(Gravity.BOTTOM,0,0);
//                mWindowEngine.showAsDropDown(findViewById(R.id.tv),0,0);

            }
        });

    }


}
