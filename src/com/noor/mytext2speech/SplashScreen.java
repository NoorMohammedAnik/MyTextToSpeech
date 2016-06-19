package com.noor.mytext2speech;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {

	private static int SplashTimeOut=3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		//Post delayed for main activity
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				//After 3 seconds ,start this activity
			Intent intent=new Intent(SplashScreen.this,MainActivity.class);
			startActivity(intent);
			
			//closing this activity.
			finish();
				
			}
		}, SplashTimeOut);
	}
	}

	


