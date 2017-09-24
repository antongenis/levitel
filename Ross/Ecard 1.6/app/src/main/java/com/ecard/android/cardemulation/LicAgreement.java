package com.ecard.android.cardemulation;


import android.app.Activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

public class LicAgreement extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//setContentView(2130903043);
	TextView busDetailContent=(TextView)findViewById(R.id.LIC);
	ExitManager.getInstance().addActivity(this);
	busDetailContent.setMovementMethod(ScrollingMovementMethod.getInstance());
	}
//	public LicAgreement() {
		// TODO Auto-generated constructor stub
//	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK){
           // Intent myIntent = new Intent();
           // myIntent = new Intent(LicAgreement.this, FloatActivity.class);
           // startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
