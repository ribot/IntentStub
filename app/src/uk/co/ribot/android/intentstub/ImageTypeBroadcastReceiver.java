package uk.co.ribot.android.intentstub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ImageTypeBroadcastReceiver extends BroadcastReceiver {
    public static String IMAGE_TYPE = "uk.co.ribot.android.intentstub.ImageTypeBroadcastReceiver.IMAGE_TYPE";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        ImageProvider.setNextImageType(intent.getStringExtra(ImageProvider.IMAGE_TYPE), context);
    }
}