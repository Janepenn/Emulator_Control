package com.example.emulator_control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            StringBuilder sb=new StringBuilder();
            Bundle bundle=intent.getExtras();
            if(bundle!=null){
                Object[] obj=(Object[])bundle.get("pdus");
                SmsMessage[] sm=new SmsMessage[obj.length];
                int length=obj.length;
                for(int i=0;i<length;i++){
                    sm[i]=SmsMessage.createFromPdu((byte[])obj[i]);
                }
                for (int i=0;i<length;i++){
                    sb.append(sm[i].getDisplayOriginatingAddress());
                    sb.append("|");
                    sb.append(sm[i].getMessageBody());
                }
                Toast.makeText(
                        context,
                        "收到一条短信！",
                        Toast.LENGTH_SHORT
                ).show();
                Intent temintent=new Intent(context,MainActivity.class);
                Bundle mybundle=new Bundle();
                mybundle.putString("change",sb.toString().trim());
                temintent.putExtras(mybundle);
                temintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(temintent);
            }
        }
    }
}
