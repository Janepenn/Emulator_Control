package com.example.emulator_control;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bundle b = msg.getData();
                    String tempmsg = (String) b.get("xx");
                    gotoXX(tempmsg);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            String tempmsg = bundle.getString("change");
            Bundle b = new Bundle();
            b.putString("xx", tempmsg);
            Message m = new Message();
            m.what = 0;
            m.setData(b);
            hd.sendMessage(m);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    private void gotoXX(String msg) {
        setContentView(R.layout.xx);
        EditText et01=(EditText)this.findViewById(R.id.EditText01);
        EditText et02=(EditText)this.findViewById(R.id.EditText02);
        String[] tempmsg=msg.split("\\|");
        et01.setText(tempmsg[0]);
        et02.setText(tempmsg[1]);
    }
}