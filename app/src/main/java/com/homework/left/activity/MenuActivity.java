package com.homework.left.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.homework.left.R;


public class MenuActivity extends Activity {
    private Button init_renjibtn;
    private Button init_renrenbtn;
    private InitButtonListener initButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //设置沉浸式标题栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();
    }

    private void initView() {
        init_renjibtn = (Button) findViewById(R.id.init_renjibtn);
        init_renrenbtn = (Button) findViewById(R.id.init_renrenbtn);
        initButtonListener = new InitButtonListener();
        init_renjibtn.setOnClickListener(initButtonListener);
        init_renrenbtn.setOnClickListener(initButtonListener);
    }


    private class InitButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.init_renjibtn:
                    intent = new Intent(MenuActivity.this, ComputerActivity.class);
                    //设置从右边出现
                    MenuActivity.this.overridePendingTransition(R.anim.initactivity_open, 0);
                    startActivity(intent);
                    break;
                //人人对战
                case R.id.init_renrenbtn:
                    intent = new Intent(MenuActivity.this, LeftVsRightActivity.class);
                    //设置从右边出现
                    MenuActivity.this.overridePendingTransition(R.anim.initactivity_open, 0);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

    long lastBackPressed = 0;

    @Override
    public void onBackPressed() {
        //当前时间
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBackPressed < 1000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        }
        lastBackPressed = currentTime;
    }
}
