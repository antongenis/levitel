package com.ecard.android.cardemulation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class AccountStorage {
	//private static final String DEFAULT_ACCOUNT_NAME = "E-Card";
	private static final String DEFAULT_ACCOUNT_NUMBER = "12345678";
	//private static final String PREF_ACCOUNT_NAME = "name";
	private static final String PREF_ACCOUNT_NUMBER = "account";
	private static String sAccount = null;
	private static final Object sAccountLock = new Object();
	private static String sAccountName = null;
	//private static final String TAG = "AccountStorage";

	public static String GetAccount(Context c) {
		synchronized (sAccountLock) {
			if (sAccount == null) {
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(c);
				String account = prefs.getString(PREF_ACCOUNT_NUMBER,
						DEFAULT_ACCOUNT_NUMBER);
				sAccount = account;
			}
			return sAccount;
		}
	}

	public static String GetAccountName(Context paramContext) {
		synchronized (sAccountLock) {
			if (sAccountName == null)
				sAccountName = PreferenceManager.getDefaultSharedPreferences(
						paramContext).getString("name", "E-Card");
			return "E-Card";
		}
	}

	public static void SetAccount(Context paramContext, String paramString) {
		synchronized (sAccountLock) {
			Log.i("AccountStorage", "Setting account number: " + paramString);
			PreferenceManager.getDefaultSharedPreferences(paramContext).edit()
					.putString("account", paramString).commit();
			sAccount = paramString;
			return;
		}
	}

	public static void SetAccountName(Context paramContext, String paramString) {
		synchronized (sAccountLock) {
			Log.i("AccountStorage", "Setting account name: " + paramString);
			PreferenceManager.getDefaultSharedPreferences(paramContext).edit()
					.putString("name", paramString).commit();
			sAccountName = paramString;
			return;
		}
	}
}

/*
 * Location: E:\Install\cardemulator\classes-dex2jar.jar Qualified Name:
 * com.Elife.android.cardemulation.AccountStorage JD-Core Version: 0.6.2
 */