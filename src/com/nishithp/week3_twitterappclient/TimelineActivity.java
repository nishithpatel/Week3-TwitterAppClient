package com.nishithp.week3_twitterappclient;

import java.util.ArrayList;

import org.json.JSONArray;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nishithp.week3_twitterappclient.models.Tweet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class TimelineActivity extends Activity {
	
	private final int REQUEST_CODE = 777;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		MyTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			public void onSuccess(JSONArray jsonTweets) {
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				
				ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
				TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
				
				//Log.d("DEBUG", jsonTweets.toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	public void onCompose(MenuItem mi) {
	     // handle click from menu item
		Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, Compose.class);
		i.putExtra("mode", 2);
		startActivityForResult(i, REQUEST_CODE);
		
		
	  }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // REQUEST_CODE is defined above
	  if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
		  
		  MyTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
				public void onSuccess(JSONArray jsonTweets) {
					ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
					
					ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
					TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
					lvTweets.setAdapter(adapter);
					
					//Log.d("DEBUG", jsonTweets.toString());
				}
			});
	  }
	} 

}
