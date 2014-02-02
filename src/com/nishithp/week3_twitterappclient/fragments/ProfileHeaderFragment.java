package com.nishithp.week3_twitterappclient.fragments;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nishithp.week3_twitterappclient.MyTwitterApp;
import com.nishithp.week3_twitterappclient.R;
import com.nishithp.week3_twitterappclient.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileHeaderFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_profile_header, container, false);
	}
/*	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}
*/	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyTwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {	
			@Override
			public void onSuccess(JSONObject json) {
				User u = User.fromJson(json);
				getActivity().getActionBar().setTitle("@" + u.getScreenName());
				populateProfileHeader(u);
			}
			
		});
	}
	
	private void populateProfileHeader(User u) {
		TextView tvName = (TextView) getActivity().findViewById(R.id.tvName);
		TextView tvTagline = (TextView) getActivity().findViewById(R.id.tvTagline);;
		TextView tvFollowers = (TextView) getActivity().findViewById(R.id.tvFollowers);;
		TextView tvFollowing = (TextView) getActivity().findViewById(R.id.tvFollowing);;
		ImageView ivProfileImage = (ImageView) getActivity().findViewById(R.id.ivProfileImage);
		
		tvName.setText(u.getName());
		tvTagline.setText(u.getName());
		tvFollowers.setText(u.getFollowersCount() + "Followers");
		tvFollowing.setText(u.getFriendsCount() + "Following");
		
		//load profile image
		ImageLoader.getInstance().displayImage(u.getProfileImageUrl(), ivProfileImage);
	}

}
