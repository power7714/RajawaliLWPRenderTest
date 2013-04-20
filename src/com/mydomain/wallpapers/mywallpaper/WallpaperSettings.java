package com.mydomain.wallpapers.mywallpaper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class WallpaperSettings extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener, OnPreferenceClickListener {

	
	public static final String PREF_APP_SHARE = "pref_app_share";
	  public static final String PREF_CONTACT = "pref_contact";
	  public static final String PREF_LIGHT_INTENSITY = "pref_light_intensity";
	  public static final String PREF_MODEL_SIZE = "pref_model_size";
	  public static final String PREF_ROTATION_RATE = "pref_rotation_rate";
	  Preference contact_us;
	  Preference share_text;
	
	protected void onCreate(Bundle icicle) {
		 super.onCreate(icicle);
		    getPreferenceManager().setSharedPreferencesName("wallpaper_settings");
		    addPreferencesFromResource(R.xml.settings);
		    getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		    this.share_text = findPreference("share_text");
		    this.share_text.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		    {
		      public boolean onPreferenceClick(Preference paramPreference)
		      {
		        WallpaperSettings.this.startEmail();
		        return true;
		      }
		      
		    });
		    this.contact_us = findPreference("contact_us");
		    this.contact_us.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
		    {
		      public boolean onPreferenceClick(Preference paramPreference)
		      {
		        WallpaperSettings.this.feedBackCommand();
		        return true;
		      }
		      
		    });
	}

	
	private void startEmail()
	  {
	    Intent localIntent = new Intent("android.intent.action.SEND");
	    localIntent.setType("text/plain");
	    String[] arrayOfString = new String[1];
	    arrayOfString[0] = "";
	    localIntent.putExtra("android.intent.extra.EMAIL", arrayOfString);
	    localIntent.putExtra("android.intent.extra.SUBJECT", "Check out this live wallpaper!");
	    localIntent.putExtra("android.intent.extra.TEXT", "Check out this awesome live wallpaper from PDC Studios! http://market.android.com/details?id=com.pspdemocenter.graffitilwp");
	    startActivity(Intent.createChooser(localIntent, "Share this App"));
	  }
	
	public void feedBackCommand()
	  {
	    String str = "Model=" + Build.MODEL + "\n" + "Device=" + Build.DEVICE + "\n" + "Release=" + Build.VERSION.RELEASE + "\n" + "Sdk Version=" + Build.VERSION.SDK + "\n";
	    Intent localIntent = new Intent("android.intent.action.SEND");
	    localIntent.setType("plain/text");
	    String[] arrayOfString = new String[1];
	    arrayOfString[0] = "steve.curtis.jr@gmail.com";
	    localIntent.putExtra("android.intent.extra.EMAIL", arrayOfString);
	    localIntent.putExtra("android.intent.extra.SUBJECT", "Graffiti Spraycan LWP Feedback");
	    localIntent.putExtra("android.intent.extra.TEXT", "Please write your suggestion or bug details here...\n\n\n" + str);
	    startActivity(Intent.createChooser(localIntent, "Send mail..."));
	  }
	
	protected void onResume() {
		super.onResume();
	}

	protected void onDestroy() {
		getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		super.onDestroy();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	}


	@Override
	public boolean onPreferenceClick(Preference arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}