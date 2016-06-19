package com.noor.mytext2speech;

import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements
		TextToSpeech.OnInitListener {
	private int result = 0;
	private TextToSpeech tts;
	private Button btnSpeak;
	private EditText txtText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tts = new TextToSpeech(this, this);
		btnSpeak = (Button) findViewById(R.id.btnSpeak);
		txtText = (EditText) findViewById(R.id.txtText);
		// button on click event
		btnSpeak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				speakOut();
			}
		});
	}

	// shutdown tts when activity destroy
	@Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	// It will called before TTS started
	@Override
	public void onInit(int status) {

		// check status for TTS is initialized or not
		if (status == TextToSpeech.SUCCESS) {
			// if TTS initialized than set language
			result = tts.setLanguage(Locale.US);
			// tts. setPitch(5) ; // you can set pitch level
			// tts. setSpeechRate(0) ; //you can set speech speed rate
			// check language is supported or not
			// check language data is available or not
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Toast.makeText(this, "Missing data", Toast.LENGTH_LONG).show();
				// disable button
				btnSpeak.setEnabled(false);
			} else {
				// if all is good than enable button convert text to speech
				btnSpeak.setEnabled(true);
			}
		} else {
			Log.e("TTS", "Initilization Failed");
		}
	}

	// call this method to speak text

	private void speakOut() {

		String text = txtText.getText().toString();

		if (result != tts.setLanguage(Locale.US)) {
			Toast.makeText(getApplicationContext(),
					"Enter right Words. . . . . . ", Toast.LENGTH_LONG).show();
		} else {
			// speak given text
			tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}
	}
}