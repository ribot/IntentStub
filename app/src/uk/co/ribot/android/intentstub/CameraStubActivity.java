package uk.co.ribot.android.intentstub;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;

public class CameraStubActivity extends Activity {

	private static final String TAG = "CameraStubActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			Log.i(TAG, "External storage not available");
		} else {
			Intent intent = getIntent();
			if (intent.getExtras() != null) {
				Util.copyFile(getOutputPath(intent), ImageProvider.getNextImageResourceId(this), this);
				setResult(RESULT_OK);
			} else {
				Log.e(TAG, "Unable to capture photo. No output directory sent in intent"); //TODO support returning a bitmap if no output directory supplied
				setResult(RESULT_CANCELED);
			}
		}

		finish();
	}

	private File getOutputPath(Intent intent) {
		Uri uri = (Uri) intent.getExtras().get(MediaStore.EXTRA_OUTPUT);
		return new File(uri.getPath());
	}
}