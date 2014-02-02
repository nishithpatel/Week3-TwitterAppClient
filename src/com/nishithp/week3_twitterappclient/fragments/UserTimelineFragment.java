package com.nishithp.week3_twitterappclient.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.ImageView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nishithp.week3_twitterappclient.MyTwitterApp;
import com.nishithp.week3_twitterappclient.R;
import com.nishithp.week3_twitterappclient.TweetsAdapter;
import com.nishithp.week3_twitterappclient.models.Tweet;
import com.nishithp.week3_twitterappclient.models.User;

public class UserTimelineFragment extends TweetsListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		int code = getActivity().getIntent().getIntExtra("code", 0);
		
		if(code == 12345) {
			MyTwitterApp.getRestClient().getAccountSettings(new JsonHttpResponseHandler() {	
				@Override
				public void onSuccess(JSONObject json) {
					User u = User.fromJson(json);
					String screenname = u.getScreenName();
					
					MyTwitterApp.getRestClient().getUserTimeline(screenname, new JsonHttpResponseHandler() {
						public void onSuccess(JSONArray jsonTweets) {
							getAdapter().addAll(Tweet.fromJson(jsonTweets));
						}
					});
				}
				
			});
		}
		
		if(code == 67890) {
			
			String screenname = "nishith_p";
			MyTwitterApp.getRestClient().getUserTimeline(screenname, new JsonHttpResponseHandler() {
				public void onSuccess(JSONArray jsonTweets) {
					getAdapter().addAll(Tweet.fromJson(jsonTweets));
				}
			});
		}
		
		
		
	}

}
