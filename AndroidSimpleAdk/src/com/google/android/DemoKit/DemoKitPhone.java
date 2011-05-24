package com.google.android.DemoKit;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;

public class DemoKitPhone extends BaseActivity {
	static final String TAG = "DemoKitPhone";
	private final byte digitalPort = 0x1;
	private final byte outputHighLow = 0x0;
	private final byte outputPWM = 0x1;
	private final byte low = 0x0;
	private final byte high = 0x1;

	private SeekBar dSeekBar3;
	private SeekBar dSeekBar5;
	private SeekBar dSeekBar6;

	private ToggleButton dHighLow2;
	private ToggleButton dHighLow3;
	private ToggleButton dHighLow4;
	private ToggleButton dHighLow5;
	private ToggleButton dHighLow6;
	private ToggleButton dHighLow7;

	private OnCheckedChangeListener highLowChangeListener = new HighLowChangeListener();
	private OnSeekBarChangeListener seekBarChangeListener = new SeekBarChangeListener();

	@Override
	protected void hideControls() {
		super.hideControls();
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void showControls() {
		super.showControls();

		dHighLow2 = (ToggleButton) findViewById(R.id.dPinHighLow2);
		dHighLow2.setTag((byte) 0x2);
		dHighLow2.setOnCheckedChangeListener(highLowChangeListener);
		dHighLow3 = (ToggleButton) findViewById(R.id.dPinHighLow3);
		dHighLow3.setTag((byte) 0x3);
		dHighLow3.setOnCheckedChangeListener(highLowChangeListener);
		dHighLow4 = (ToggleButton) findViewById(R.id.dPinHighLow4);
		dHighLow4.setTag((byte) 0x4);
		dHighLow4.setOnCheckedChangeListener(highLowChangeListener);
		dHighLow5 = (ToggleButton) findViewById(R.id.dPinHighLow5);
		dHighLow5.setTag((byte) 0x5);
		dHighLow5.setOnCheckedChangeListener(highLowChangeListener);
		dHighLow6 = (ToggleButton) findViewById(R.id.dPinHighLow6);
		dHighLow6.setTag((byte) 0x6);
		dHighLow6.setOnCheckedChangeListener(highLowChangeListener);
		dHighLow7 = (ToggleButton) findViewById(R.id.dPinHighLow7);
		dHighLow7.setTag((byte) 0x7);
		dHighLow7.setOnCheckedChangeListener(highLowChangeListener);

		dSeekBar3 = (SeekBar) findViewById(R.id.valueBarD3);
		dSeekBar3.setTag((byte) 0x3);
		dSeekBar3.setOnSeekBarChangeListener(seekBarChangeListener);
		dSeekBar5 = (SeekBar) findViewById(R.id.valueBarD5);
		dSeekBar5.setTag((byte) 0x5);
		dSeekBar5.setOnSeekBarChangeListener(seekBarChangeListener);
		dSeekBar6 = (SeekBar) findViewById(R.id.valueBarD6);
		dSeekBar6.setTag((byte) 0x6);
		dSeekBar6.setOnSeekBarChangeListener(seekBarChangeListener);
	}

	private class HighLowChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			byte portByte = (Byte) buttonView.getTag();
			if (isChecked) {
				sendCommand(digitalPort, portByte, outputHighLow, high);
				Log.i(TAG, "message send: digital pin " + portByte + " HIGH");
			} else {
				sendCommand(digitalPort, portByte, outputHighLow, low);
				Log.i(TAG, "message send: digital pin " + portByte + " LOW");
			}
		}
	}

	private class SeekBarChangeListener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			byte portByte = (Byte) seekBar.getTag();
			sendCommand(digitalPort, portByte, outputPWM, (byte)progress);
			Log.i(TAG, "message send: digital pwm pin " + portByte + " " + progress);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	}
}