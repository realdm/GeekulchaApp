package org.geekulcha;


import java.io.IOException;
import java.util.ArrayList;

import org.geekulcha.R;
import org.geekulcha.Beans.Items;
import org.geekulcha.Beans.JsonResponse;
import org.geekulcha.Fragments.TagsFragment;
import org.geekulcha.Fragments.VideosFragment;
import org.geekulcha.Util.LoadFromNetwork;

import com.squareup.picasso.Picasso;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements TabListener {
	
	private ViewPager pager;
	private PagerAdapterCustom pagerAdapter;
	private ActionBar actionbar;
	private ProgressBar progress;
	private Bundle bundle;
	private Fragment fragment;
	private Menu optionsMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * INITIALIZE THE ADAPTER AND PROGRESS BAR
		 */
		pagerAdapter=new PagerAdapterCustom(this.getSupportFragmentManager());
		
			
		/**
		 * GET THE VIEWPAGER
		 */
		pager=(ViewPager)findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);
		
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                   
                			  getActionBar().setSelectedNavigationItem(position);
                		
                }
            });
            
		/**
		 * GET THE ACTIONBAR
		 */
		actionbar=getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionbar.setDisplayShowTitleEnabled(false);
		actionbar.setDisplayUseLogoEnabled(true);
		
		/**
		 * ADD TABS
		 */
		actionbar.addTab(actionbar.newTab().setText("Videos").setTabListener(this));
		actionbar.addTab(actionbar.newTab().setText("#GeeKulcha").setTabListener(this));
	}


	

	

	
	
	/**
	 * WORKS WITH THE LOADING VIDEO TASK
	 */
	/*public boolean checkLoadingVideo()
	{
		boolean taskdone=false;
		
		if(task1==null)
		{
			task1=new LoadVideos();
			task1.execute();
		}
		else if(task1.getStatus()==Status.RUNNING)
		{
			System.out.println("Still Running");
		}
		else if(task1.getStatus()==Status.FINISHED)
		{
			System.out.println("The Pain is Over");
			taskdone=true;
		}
		return taskdone;
	}*/

	
	/**
	 * TAB LISTENER METHODS
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.optionsMenu = menu;
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.refresh_menu, menu);
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
			case R.id.action_about:
			{
				Intent i=new Intent(this,About.class);
				startActivity(i);
				break;
				
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * END OF TABLISTENER METHODS
	 */
	
	/**
	 * INNER CUSTOM PAGER ADAPTER
	 */
	public class PagerAdapterCustom extends FragmentStatePagerAdapter{

		
		public PagerAdapterCustom(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch(position)
			{
			case 0:
				fragment=new VideosFragment();
				break;
			case 1:
				fragment=new TagsFragment();
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
	
	}
}
