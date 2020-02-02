package com.homework.left.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.homework.left.R;
import com.homework.left.view.ComputerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ComputerActivity extends Activity implements ComputerView.OnWinListener {
    public ComputerActivity renjiGameAty = this;
    ComputerView gbv;
    TextView textView;
    Button huiqi;
    Button shuaxin;
    TextView showtime;
    ImageView renjibeijing;
    boolean isWined = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computer_layout);
        renjiGameAty = this;
        initView();
    }

    private void initView() {
        showtime = (TextView) findViewById(R.id.showtime);
        gbv = (ComputerView) this.findViewById(R.id.gobangview);
        textView = (TextView) findViewById(R.id.text);
        renjibeijing = (ImageView) findViewById(R.id.renjibeijingdongtu);
        renjibeijing.setColorFilter(android.R.color.holo_blue_bright);
        huiqi = (Button) findViewById(R.id.btn1);
        shuaxin = (Button) findViewById(R.id.btn2);
        SimpleDateFormat simpleDateFormat = null;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        textView.setText("当前时间：" + simpleDateFormat.format(new Date()));

        gbv.setTextView(textView);
        gbv.setButtons(huiqi, shuaxin);
        gbv.setShowTimeTextViewTime(jishitime);
        gbv.setWinListner(this);
        Timer timer = new Timer();
        JishiTask myTask = new JishiTask();
        timer.schedule(myTask, 1000, 1000);
        renjisetflag(getIntent().getIntExtra("flag", 0));//设置难度，默认为简单
    }

    public void renjisetflag(int flag) {
        gbv.setflag(2);
    }

    int[] jishitime = {0, 0, 0, 0};//秒，分，时，总

    @Override
    public void onWin(int i) {
        Toast.makeText(this, i == 1 ? "恭喜你赢了!!!" : "电脑赢了!!!", Toast.LENGTH_LONG).show();
        isWined = true;
    }

    private class JishiTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    jishitime[0]++;
                    jishitime[3]++;
                    if (jishitime[0] == 60) {
                        jishitime[1]++;
                        jishitime[0] = 0;
                    }
                    if (jishitime[1] == 60) {
                        jishitime[2]++;
                        jishitime[1] = 0;
                    }
                    if (jishitime[2] == 24) {
                        jishitime[2] = 0;
                    }
                    showtime.setText(String.format("%02d:%02d:%02d", jishitime[2], jishitime[1], jishitime[0]));
                }
            });
        }
    }
}
