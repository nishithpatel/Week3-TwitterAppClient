package com.nishithp.week3_twitterappclient.fragments;

import java.util.ArrayList;

import com.nishithp.week3_twitterappclient.R;
import com.nishithp.week3_twitterappclient.TweetsAdapter;
import com.nishithp.week3_twitterappclient.models.Tweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TweetsListFragment extends Fragment {
	
	TweetsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tweets_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(); //Tweet.fromJson(jsonTweets);
		adapter = new TweetsAdapter(getActivity(), tweets); //new TweetsAdapter(getBaseContext(), tweets);
		
		ListView lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets); //(ListView) findViewById(R.id.lvTweets);
		lvTweets.setAdapter(adapter);
		
		//Log.d("DEBUG", jsonTweets.toString());
	}
	
	public TweetsAdapter getAdapter() {
		return adapter;
	}

}
