package com.example.motorcyclesafetysystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    String msg_from;
    String msgBody = "";
    long msg_time;

    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals(SMS_RECEIVED)){
          Log.d("onreceive","in on receive");
          Bundle bundle = intent.getExtras();
          SmsMessage[]msgs;
          if (bundle!=null){
              try {
                  Object[]pdus = (Object[]) bundle.get("pdus");
                  msgs = new SmsMessage[pdus.length];
                  for (int i=0;i<msgs.length;i++){
                      msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                      msg_from = msgs[i].getOriginatingAddress();
                      msgBody = msgs[i].getMessageBody();
                      msg_time = msgs[i].getTimestampMillis();
                      Intent it = new Intent("broadCastName");
                      it.putExtra("message", msgBody);
                      context.sendBroadcast(it);
                  };
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      }
    }


}
