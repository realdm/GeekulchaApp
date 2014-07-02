package org.geekulcha.Fragments;


import java.util.List;

import org.geekulcha.R;
import org.geekulcha.Fragments.VideosFragment.LoadVideos;
import org.geekulcha.Util.SearchTweets;
import org.geekulcha.Util.WebCheck;
import org.geekulcha.Util.WebCheckResult;
import org.geekulcha.adapters.TagsAdapter;

import twitter4j.Status;

import com.squareup.picasso.Picasso;

import android.nfc.TagLostException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class TagsFragment extends Fragment {

	ProgressBar progress;
	GridView grid;
	List<Status> list;
	private Menu optionsMenu;
	private boolean refresh=false;
	WebCheckResult result;
	TextView error;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v=inflater.inflate(R.layout.tags, container,false);
		progress=(ProgressBar)v.findViewById(R.id.loadin_tags);
		grid=(GridView)v.findViewById(R.id.gridview1);
		error=(TextView)v.findViewById(R.id.networkError);
		LoadTweets load=new LoadTweets();
		load.execute();
		setHasOptionsMenu(true);
		return v;
	}
	
	
	public class LoadTweets extends AsyncTask<Void, Void, String>
	{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if(refresh==false)
			{
				progress.setVisibility(View.VISIBLE);
				
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
				SearchTweets search=new SearchTweets();
				list=search.getListTweets();
			}
			
			return "buya";
		}
		
		@Override
		protected void onPostExecute(String result1) {
			// TODO Auto-generated method stub
			result=WebCheck.checkNetworkAvailability(getActivity());
			if(list!=null)
			{
				progress.setVisibility(View.GONE);
				grid.setAdapter(new TagsAdapter(getActivity(), list));
				if(refresh==true)
				{
					setRefreshActionButtonState(false);
				}
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
				refresh=true;
				LoadTweets load=new LoadTweets();
				load.execute();
				setRefreshActionButtonState(true);
				break;
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

	
	
}
