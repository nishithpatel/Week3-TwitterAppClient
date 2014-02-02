package com.nishithp.week3_twitterappclient;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

public class Compose extends Activity {
	
	private EditText etNewTweet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		etNewTweet = (EditText) findViewById(R.id.etNewTweet);
		
	}
	
	public void onSubmit(View v) {
		String newTweet = etNewTweet.getText().toString();
		
		MyTwitterApp.getRestClient().params.put("status", newTweet);
		
		MyTwitterApp.getRestClient().postTweet(new JsonHttpResponseHandler() {
			
			public void onSuccess(JSONObject response) {
				Intent data = new Intent();
				data.putExtra("mode",2);
				setResult(RESULT_OK, data);				
				finish();
				//Log.d("DEBUG", jsonTweets.toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

}
