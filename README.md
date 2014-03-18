# Android Intent Stub
An app for stubbing out built in Android apps for automated testing.

Set these apps as default for completing tasks that rely on external apps.

## Camera & Gallery
These apps immeditaley return an image and repond to the normal camera and choose image requests. 

You can also choose an image type to be returned. To choose an image send a broadcast before you request an image:

	Intent intent = new Intent("uk.co.ribot.android.intentstub.ImageTypeBroadcastReceiver.IMAGE_TYPE");
	        intent.putExtra("uk.co.ribot.android.intentstub.IMAGE_TYPE", "uk.co.ribot.android.intentstub.IMAGE_TYPE_COFFEE");
	        context.sendBroadcast(intent);

For a full list of available image types, see the ImageProvider class. If no image type is requested, clear images of faces are returned.

## Others
None yet, but pull requests accepted...
