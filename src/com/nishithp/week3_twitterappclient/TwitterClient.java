package com.nishithp.week3_twitterappclient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "Ikw81CgkKVT5qKSQFqqHQ";       // Change this
    public static final String REST_CONSUMER_SECRET = "t19JBW2LT3LiE87wyF5BU0j435iy7tibVvHwBBjJcU"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://week3_twitterappclient"; // Change this (here and in manifest)
    
    RequestParams params = new RequestParams();
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/home_timeline.json");
    	client.get(url, null, handler);
    }
    
    public void getMentions(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/mentions_timeline.json");
    	client.get(url, null, handler);
    }
    
    public void getUserTimeline(String screenname, AsyncHttpResponseHandler handler) {
    	//String screenname = "nishith_p";
    	String url = getApiUrl("statuses/user_timeline.json?screen_name=" + screenname);
    	client.get(url, null, handler);
    }
    
    public void postTweet(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/update.json");
    	client.post(url, params, handler);
    }
    
    public void getAccountSettings(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("account/settings.json");
    	client.get(url,  null, handler);
    }
    
    public void getMyInfo(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("account/verify_credentials.json");
    	client.get(url, null, handler);
    }
    
    public void getUserProfile(String screenname, AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("users/show.json?screen_name=" + screenname);
    	client.get(url, null, handler);
    }
    
}