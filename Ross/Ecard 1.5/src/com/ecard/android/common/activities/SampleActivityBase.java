package com.ecard.android.common.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class SampleActivityBase extends FragmentActivity {
	public static final String TAG = "SampleActivityBase";

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		Log.i("SampleActivityBase", "Ready");
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
}

/*
 * Location: E:\Install\cardemulator\classes-dex2jar.jar Qualified Name:
 * com.Elife.android.common.activities.SampleActivityBase JD-Core Version: 0.6.2
 */