package com.nishithp.week3_twitterappclient;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nishithp.week3_twitterappclient.fragments.HomeTimelineFragment;
import com.nishithp.week3_twitterappclient.fragments.MentionsFragment;
import com.nishithp.week3_twitterappclient.fragments.TweetsListFragment;
import com.nishithp.week3_twitterappclient.models.Tweet;

public class TimelineActivity extends FragmentActivity implements TabListener {
	
	private final int REQUEST_CODE = 777;
	
	TweetsListFragment fragmentTweets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupNavigationTabs();
		
	}
	
	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); 
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab().setText("Home").setTag("HomeTimelineFragment").setIcon(R.drawable.ic_home2).setTabListener(this);
		Tab tabMentions = actionBar.newTab().setText("Mentions").setTag("MentionsFragment").setIcon(R.drawable.ic_mentions).setTabListener(this);
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	public void onCompose(MenuItem mi) {
	     // handle click from menu item
		Toast.makeText(this, "Clicked Compose", Toast.LENGTH_SHORT).show();
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
					TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
					
					ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
					lvTweets.setAdapter(adapter);
					
					//Log.d("DEBUG", jsonTweets.toString());
				}
			});
	  }
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		if(tab.getTag() == "HomeTimelineFragment")
		{
			//set the fragment in the framelayout to home timeline
			fts.replace(R.id.frame_container, new HomeTimelineFragment());
			
		} else if(tab.getTag() == "MentionsFragment") {
			//set the fragment in the framelayout to mentions timeline
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		fts.commit();
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	} 
	
	public void onProfileView(MenuItem mi) {
		Toast.makeText(this, "Clicked Profile", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, ProfileActivity.class);
		i.putExtra("code", 12345);
		startActivity(i);
	}
	
	public void lookupUser(View v) {
		
		Toast.makeText(this, "Clicked image", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, ProfileActivity.class);
		i.putExtra("code", 67890);
		startActivity(i);
	}

}
