package uk.co.ribot.android.intentstub;

import android.content.Context;
import android.content.Intent;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;

public class Util {
    
    public static void copyFile(File destination, int resourceId, Context context) {
        try {
            InputStream in = new BufferedInputStream(context.getResources().openRawResource(resourceId));
            OutputStream out = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int length;

            if (in != null) {
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}