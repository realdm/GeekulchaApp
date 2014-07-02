package org.geekulcha;

import org.geekulcha.Util.CircleTransform;
import org.geekulcha.Util.FontChanger;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ImageView img=(ImageView)findViewById(R.id.about);
		Picasso.with(this).load(R.drawable.ic_about).into(img);
	
		TextView name=(TextView)findViewById(R.id.developer_name);
		name.setText("Dario Mungoi");
		TextView role=(TextView)findViewById(R.id.role);
		role.setText("Designer/Developer:");
		TextView desc=(TextView)findViewById(R.id.appDescription);
		TextView title_desc=(TextView)findViewById(R.id.desc_title);
		desc.setText("Geekulcha, Where the young, talented, creative and ambitious IT minds meet to learn, connect with industry leaders, get exposure & put their skills to work... ");
		
		role.setTypeface(FontChanger.setRobotoCondesed(this));
		name.setTypeface(FontChanger.setRobotoLight(this));
		title_desc.setTypeface(FontChanger.setRobotoCondesed(this));
		desc.setTypeface(FontChanger.setRobotoLight(this));
		
	
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

}
