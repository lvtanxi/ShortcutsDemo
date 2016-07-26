package com.qiangyu.test.coupondisplayviewtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.qiangyu.test.coupondisplayviewtest.view.CouponDisplayView;

/**
 * User: 吕勇
 * Date: 2016-07-26
 * Time: 10:09
 * Description:在桌面创建快捷方式
 */
public class ShortcutsActvity extends AppCompatActivity {

    private CouponDisplayView mShortcutsView;

    public static void startShortcutsActvity(Activity activity) {
        activity.startActivity(new Intent(activity, ShortcutsActvity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_shortcuts);
        initView();
        if(!TextUtils.isEmpty(getIntent().getStringExtra("userId"))){
            Snackbar.make(mShortcutsView,getIntent().getStringExtra("userId"),Snackbar.LENGTH_SHORT)
                    .setAction("点我", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ShortcutsActvity.this, "艹蛋", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        }
    }

    private void initView() {
        mShortcutsView = (CouponDisplayView) findViewById(R.id.shortcuts_view);
        mShortcutsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createShotCut("快捷方式");
            }
        });
    }
    public void createShotCut(String name) {

        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);

        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcutIntent.setClass(this, ShortcutsActvity.class);
        /**
         * 创建一个Bundle对象让其保存将要传递的值
         */
        Bundle bundle = new Bundle();
        bundle.putString("userId", "test");
        shortcutIntent.putExtras(bundle);
        /**
         * 设置这条属性，可以使点击快捷方式后关闭其他的任务栈的其他activity，然后创建指定的acticity
         */
        shortcutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        Intent shortcut = new Intent(Intent.ACTION_CREATE_SHORTCUT);

        shortcut.putExtra("duplicate", false);

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);


        Parcelable icon = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        shortcut.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        sendBroadcast(shortcut);
    }
}
