package com.ecard.android.cardemulation;



import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class FloatActivity extends Activity implements OnClickListener {
	private TextView imei_num = null;
	private TextView tmsg=null;
	private Button btnEULA, btnClose,btnSetNFC;
	private NfcAdapter nfcAdapter;
	// private Button returnButton = null;
	private String sImei = null;
	private Intent serviceIntent; 
	@Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //先去除应用程序标题栏  注意：一定要在setContentView之前

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ExitManager.getInstance().addActivity(this);
        //将我们定义的窗口设置为默认视图

        setContentView(0x7f030004);
        btnEULA=(Button) findViewById(R.id.EULAbutton);
        btnClose=(Button)findViewById(R.id.closebutton);
        btnSetNFC=(Button)findViewById(R.id.setNFC);
        
        btnEULA.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnSetNFC.setOnClickListener(this);
        serviceIntent = new Intent(this, CardService.class);
    	sImei = ((TelephonyManager) getSystemService("phone")).getDeviceId();
	//"123456789012";//getLocalMac();
	//	Log.i("CardMainActivity", "---IMEI:" + sImei);
		if (sImei.length()%2==1)
			sImei=sImei.substring(2,3)+sImei;
		
		byte[] macimei=new byte[10];
	
		macimei=HexStringToByteArray(sImei);
	
		int itemp=(macimei[0]+macimei[1]+macimei[2]+macimei[3]+macimei[4]+macimei[5]+macimei[6])%256;

		for (byte i=0; i<7;i++)
		{
			macimei[i]=(byte) ((macimei[i]+itemp)% 256);
		}
		sImei=ByteArrayToHexString(macimei);
		sImei=sImei.substring(sImei.length()-14,sImei.length());
		this.imei_num = ((TextView) findViewById(2131230731));
		 this.tmsg = ((TextView) findViewById(R.id.tmsg));
		this.imei_num.setText(sImei);
		AccountStorage.SetAccount(this, sImei);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this); 
	

    }

	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.EULAbutton:
			Intent intent=new Intent();
			  intent.setClass(FloatActivity.this,LicAgreement.class);
			//  FloatActivity.this.finish();
			  startActivity(intent);
			  break;
		case R.id.closebutton:
			finish();
			stopService(serviceIntent);
			ExitManager.getInstance().exit();
			
			break;
		case R.id.setNFC:
			Intent intentNFC =  new Intent(Settings.ACTION_NFC_SETTINGS);  
			
            startActivity(intentNFC);
            break;
		default: break;
		}
	}
	
	 @Override  
     protected void onResume() {  
     super.onResume();  
      refreshStatus();  
      }  
	 private void refreshStatus() { 
		 final String tip; 
		  
		  if (nfcAdapter == null) 
			  tip = this.getResources().getString(R.string.tip_nfc_notfound);
		  else if (nfcAdapter.isEnabled()) 
			  tip = this.getResources().getString(R.string.tip_nfc_enabled);
		  else
			  tip = this.getResources().getString(R.string.tip_nfc_disabled);
		  
		 
		  this.tmsg.setText(tip);
		 
	 }
	 @Override
	public void onDestroy() {

	        super.onDestroy();
	        Log.i("float activty", "---exit floatactivity---");
	 }
	/* public static String  getLocalMac() {
		 String mac=null;
		 String str = "";
		      try 
		      {
		          Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
		          InputStreamReader ir = new InputStreamReader(pp.getInputStream());
		          LineNumberReader input = new LineNumberReader(ir);
		          for (; null != str;) 
		          {
		              str = input.readLine();
		              if (str != null)
		              {
		                  mac = str.trim();// 去空格
		                  mac=mac.replaceAll(":","");
		                 break;
		              }
		          }
		      } catch (IOException ex) {
		          // 赋予默认值
		         ex.printStackTrace();
		      }
		      return mac.toUpperCase();

		  }*/
	 
		public static byte[] HexStringToByteArray(String s)
				throws IllegalArgumentException {
			int len = s.length();
			if (len % 2 == 1) {
				throw new IllegalArgumentException(
						"Hex string must have even number of characters");
			}
			byte[] data = new byte[len / 2]; // Allocate 1 byte per 2 hex characters
			for (int i = 0; i < len; i += 2) {
				// Convert each character into a integer (base-16), then bit-shift
				// into place
				data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
						.digit(s.charAt(i + 1), 16));
			}
			return data;
		}
		
		public static String ByteArrayToHexString(byte[] bytes) {
			final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9', 'A', 'B', 'C', 'D', 'E', 'F' };
			char[] hexChars = new char[bytes.length * 2]; // Each byte has two hex
															// characters (nibbles)
			int v;
			for (int j = 0; j < bytes.length; j++) {
				v = bytes[j] & 0xFF; // Cast bytes[j] to int, treating as unsigned
										// value
				hexChars[j * 2] = hexArray[v >>> 4]; // Select hex character from
														// upper nibble
				hexChars[j * 2 + 1] = hexArray[v & 0x0F]; // Select hex character
															// from lower nibble
			}
			return new String(hexChars);
		}

}
