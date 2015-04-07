package com.example.testcamera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

public class ShowPicture extends Activity {

	private static final String TAG = "ShowPicture";
	private ImageView mPicture;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_picture);

		mPicture = (ImageView) findViewById(R.id.picture);
		String fileName = getIntent().getStringExtra(
				TestCameraActivity.KEY_FILENAME);
		// 图片路径
		Log.i(TAG, fileName);
		String path = getFileStreamPath(fileName).getAbsolutePath();

		Display display = getWindowManager().getDefaultDisplay(); // 显示屏尺寸
		float destWidth = display.getWidth();
		float destHeight = display.getHeight();

		// 读取本地图片尺寸
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);// 设置为true，options依然对应此图片，但解码器不会为此图片分配内存

		float srcWidth = options.outWidth;
		float srcHeight = options.outHeight;

		int inSampleSize = 1;
		if (srcHeight > destHeight || srcWidth > destWidth) { // 当图片长宽大于屏幕长宽时
			if (srcWidth > srcHeight) {
				inSampleSize = Math.round(srcHeight / destHeight);
			} else {
				inSampleSize = Math.round(srcWidth / destWidth);
			}
		}
		options = new BitmapFactory.Options();
		options.inSampleSize = inSampleSize;

		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		BitmapDrawable bDrawable = new BitmapDrawable(getResources(), bitmap);
		mPicture.setImageDrawable(bDrawable);
	}

	@Override
	public void onDestroy() {
		if (!(mPicture.getDrawable() instanceof BitmapDrawable))
			return;
		// 释放bitmap占用的空间
		BitmapDrawable b = (BitmapDrawable) mPicture.getDrawable();
		b.getBitmap().recycle();
		mPicture.setImageDrawable(null);
	}

}
