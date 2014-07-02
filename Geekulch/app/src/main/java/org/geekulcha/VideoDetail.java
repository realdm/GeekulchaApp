package org.geekulcha;

import java.util.ArrayList;

import org.geekulcha.Beans.Items;
import org.geekulcha.Util.FontChanger;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class VideoDetail extends YouTubeBaseActivity implements OnInitializedListener {

	private YouTubePlayer player;
	Items item;
	String link;
	String title;
	String description;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_detail);
		
		/**
		 * GET INTENT EXTRAS
		 */
		item=(Items) getIntent().getSerializableExtra("item");
		link=item.getId().getVideoId();
		title=item.getSnippet().getTitle();
		description=item.getSnippet().getDescription();
		/**
		 * END GET INTENT EXTRAS
		 */
		
		//Showing up navigation button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Initializing textviews for title description and changing font
		TextView textTitle=(TextView)findViewById(R.id.title);
		TextView txtDesc=(TextView)findViewById(R.id.description);
		textTitle.setTypeface(FontChanger.setRobotoCondesed(getApplicationContext()));
		txtDesc.setText(description);
		textTitle.setText(title);
		/**
		 * INITIALIZE YOUTUBE PLAYER VIEW
		 */
		YouTubePlayerView youtubepView=(YouTubePlayerView)findViewById(R.id.youtube_view);
		youtubepView.initialize("AIzaSyBqI4iC1m5iRypWmNy_3ioFSUsk8ewpf8Q", this);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
	        return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitializationSuccess(Provider provider, YouTubePlayer player,
			boolean arg2) {
	this.player=player;
	player.cueVideo(link);

	}

}
