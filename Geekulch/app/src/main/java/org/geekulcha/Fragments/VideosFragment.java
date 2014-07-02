package org.geekulcha.Fragments;


import java.io.IOException;
import java.util.ArrayList;

import org.geekulcha.R;
import org.geekulcha.Beans.Items;
import org.geekulcha.Beans.JsonResponse;
import org.geekulcha.Util.LoadFromNetwork;
import org.geekulcha.Util.WebCheck;
import org.geekulcha.Util.WebCheckResult;
import org.geekulcha.adapters.VideoAdapter;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class VideosFragment extends android.support.v4.app.Fragment{
	
	private ArrayList<Items> items;
	GridView grid;
	ProgressBar progress;
	LoadVideos task1;
	private Menu optionsMenu;
	private  boolean refreshing=false;
	WebCheckResult result;
	TextView error;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v=inflater.inflate(R.layout.videos, container,false);
		progress=(ProgressBar)v.findViewById(R.id.loadin_videos);
		grid=(GridView)v.findViewById(R.id.gridview);
		error=(TextView)v.findViewById(R.id.networkError);
		//grid.setAdapter(new VideoAdapter(getActivity()));
		task1=new LoadVideos();
		task1.execute();
		setHasOptionsMenu(true);
		return v;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		optionsMenu=menu;
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId())
		{
			case R.id.menu_refresh:
			{
				refreshing=true;
				LoadVideos load=new LoadVideos();
				load.execute();
				setRefreshActionButtonState(true);
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void setRefreshActionButtonState(final boolean refreshing) {
	    if (optionsMenu != null) {
	        final MenuItem refreshItem = optionsMenu
	            .findItem(R.id.menu_refresh);
	        if (refreshItem != null) {
	            if (refreshing) {
	                refreshItem.setActionView(R.layout.actionbar_indeterminate);
	            } else {
	                refreshItem.setActionView(null);
	            }
	        }
	    }
	}

	public class LoadVideos extends AsyncTask<Void, Void, String>
	{

		JsonResponse json;
		@Override
		protected void onPreExecute() {
			
			if(refreshing==false)
			{
				System.out.println("here we are");
				progress.setVisibility(View.VISIBLE);
				//pager.setVisibility(View.GONE);
			}
			
	
		}
		
		@Override
		protected String doInBackground(Void... arg0) {
			result=WebCheck.checkNetworkAvailability(getActivity());
			if(result.isNetworkUnavailable())
			{
				
			}
			else
			{
				LoadFromNetwork load=new LoadFromNetwork();
				try {
					json=load.convertToObject("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UChTMtcKnfaXs4IZf9h83nrQ&maxResults=50&order=date&key=AIzaSyASUc7syYgJFqfEXFsvLRFPzQpFJGhhcs0");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return "acabou execucao";
		}
		
		@Override
		protected void onPostExecute(String result1) {
			// TODO Auto-generated method stub
			//System.out.println(json.getItems().get(0).getSnippet().getTitle());
			result=WebCheck.checkNetworkAvailability(getActivity());
			
			if(json!=null)
			{
				items=json.getItems();
				//System.out.println(items);
				progress.setVisibility(View.GONE);
				System.out.println(items.get(items.size()-1).getSnippet().getThumbnails().getHigh().getUrl());
				grid.setAdapter(new VideoAdapter(getActivity(),items));
				setRefreshActionButtonState(false);
				if(result.isNetworkUnavailable())
				{
					Toast.makeText(getActivity(), "Couldn't connect to Stream!", Toast.LENGTH_LONG).show();
				}
				
			}
			else
			{
				progress.setVisibility(View.GONE);
				setRefreshActionButtonState(false);
				error.setVisibility(View.VISIBLE);
			}
			
			
		}
		
	}
	
}


