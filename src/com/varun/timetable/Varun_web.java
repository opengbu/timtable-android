package com.varun.timetable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Varun_web extends Activity // loads webpage
{

	private WebView webView;
	String loadurl;
	int old;
	public static final String PREFS_NAME = "Varun_ShiniGami";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Intent myintent = getIntent();
		super.onCreate(savedInstanceState);
		loadurl = myintent.getStringExtra("loadurl");
		old = myintent.getIntExtra("old", 0);
		setContentView(R.layout.webview);

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String currenturl = settings.getString("currenturl",
				"http://tech-league.tk/blog/index.php");

		webView = new WebView(this);
		webView = (WebView) findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.getSettings().setBuiltInZoomControls(true);

		if (old == 1) {
			webView.loadUrl(currenturl);
			Toast.makeText(getApplicationContext(), "loading last timetable",
					Toast.LENGTH_LONG).show();
		} else {
			webView.loadUrl(loadurl);
			Toast.makeText(getApplicationContext(),"loading ",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		webView.saveState(outState);
	}

	@Override
	public void onBackPressed() {
		String currenturl;
		currenturl = webView.getUrl();

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("currenturl", currenturl);
		editor.commit();

		// fixes the bug when app is restarted after exiting it with back button
		finish();
		System.exit(0);
		return;
	}

}