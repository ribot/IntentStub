package uk.co.ribot.android.intentstub;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

public class ImageProvider {
    public static final String IMAGE_TYPE = "uk.co.ribot.android.intentstub.IMAGE_TYPE";
    public static final String IMAGE_TYPE_CLEAR_FACE = "uk.co.ribot.android.intentstub.IMAGE_TYPE_CLEAR_FACE";
    public static final String IMAGE_TYPE_BLURRY_FACE = "uk.co.ribot.android.intentstub.IMAGE_TYPE_BLURRY_FACE";
    public static final String IMAGE_TYPE_DOG = "uk.co.ribot.android.intentstub.IMAGE_TYPE_DOG";
    public static final String IMAGE_TYPE_CAT = "uk.co.ribot.android.intentstub.IMAGE_TYPE_CAT";
    public static final String IMAGE_TYPE_LANDSCAPE = "uk.co.ribot.android.intentstub.IMAGE_TYPE_LANDSCAPE";
    public static final String IMAGE_TYPE_COFFEE = "uk.co.ribot.android.intentstub.IMAGE_TYPE_COFFEE";

    private static final String PREFERENCES_NAME = "intentstub";
    private static final String NEXT_IMAGE_TYPE_KEY = "nextImageType";
    private static final String NEXT_IMAGE_INDEX_KEY = "nextImageIndex";

    private static final String TAG = "ImageProvider";

    public static void setNextImageType(String imageType, Context context) {
        Log.i(TAG, "Setting next image type to:"+imageType);

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NEXT_IMAGE_TYPE_KEY, imageType);
        editor.commit();
    }

    public static int getNextImageResourceId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = preferences.edit();

        Resources res = context.getResources();
        int imageTypeArrayId = 0;
        String imageType = getNextImageType(preferences, editor);

        if (imageType == null || imageType.equals(IMAGE_TYPE_CLEAR_FACE)) {
            imageTypeArrayId = R.array.clear_face_images;
        } else if (imageType.equals(IMAGE_TYPE_BLURRY_FACE)) {
            imageTypeArrayId = R.array.blurry_face_images;
        } else if (imageType.equals(IMAGE_TYPE_DOG)) {
            imageTypeArrayId = R.array.dog_images;
        } else if (imageType.equals(IMAGE_TYPE_CAT)) {
            imageTypeArrayId = R.array.cat_images;
        } else if (imageType.equals(IMAGE_TYPE_LANDSCAPE)) {
            imageTypeArrayId = R.array.landscape_images;
        } else if (imageType.equals(IMAGE_TYPE_COFFEE)) {
            imageTypeArrayId = R.array.coffee_images;
        }

        TypedArray images = res.obtainTypedArray(imageTypeArrayId);
        int nextImageResourceId = images.getResourceId(getNextImageIndex(images.length()-1, preferences, editor), R.raw.landscape_1); 

        images.recycle();
        Log.d(TAG, "Returning image:"+context.getResources().getResourceEntryName(nextImageResourceId));
        
        return nextImageResourceId;
    }

    private static int getNextImageIndex(int maxIndex, SharedPreferences preferences, SharedPreferences.Editor editor) {
        int nextImageIndex = preferences.getInt(NEXT_IMAGE_INDEX_KEY, 0);
        int newNextImageIndex;
        
        if (nextImageIndex > maxIndex) {
            nextImageIndex = 0;
            newNextImageIndex = 1;
        } else if (nextImageIndex == maxIndex) {
            newNextImageIndex = 0;
        } else { //if nextImageIndex < maxIndex
            newNextImageIndex = nextImageIndex+1;
        }

        editor.putInt(NEXT_IMAGE_INDEX_KEY, newNextImageIndex);
        editor.commit();
        
        return nextImageIndex;
    }

    private static String getNextImageType(SharedPreferences preferences, SharedPreferences.Editor editor) {
        String nextImageType = preferences.getString(NEXT_IMAGE_TYPE_KEY, null);

        editor.remove(NEXT_IMAGE_TYPE_KEY);
        editor.commit();

        return nextImageType;
    }
}