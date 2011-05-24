package com.google.android.DemoKit;

import java.io.IOException;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends DemoKitActivity {

	private static final String TAG = BaseActivity.class.getSimpleName();
	public BaseActivity() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (mAccessory != null) {
			showControls();
		} else {
			hideControls();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Quit");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle() == "Quit") {
			finish();
			System.exit(0);
		}
		return true;
	}

	protected void hideControls() {
		setContentView(R.layout.no_device);
	}

	protected void showControls() {
		setContentView(R.layout.main);
	}
	
	public void sendCommand(byte portType, byte portNumber, byte mode, byte value) {
		byte[] buffer = new byte[4];
		if (value > 255)
			value = (byte)255;
		buffer[0] = portType;
		buffer[1] = portNumber;
		buffer[2] = mode;
		buffer[3] = value;
		Log.i(TAG, "byte: " + buffer[0]);
		Log.i(TAG, "byte: " + buffer[1]);
		Log.i(TAG, "byte: " + buffer[2]);
		Log.i(TAG, "byte: " + buffer[3]);
		if (mOutputStream != null && buffer[1] != -1) {
			try {
				mOutputStream.write(buffer);
				mOutputStream.flush();;
			} catch (IOException e) {
				Log.e(TAG, "write failed", e);				
			}
		}
	}
}