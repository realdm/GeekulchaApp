package org.geekulcha.adapters;

import java.util.List;

import org.apache.http.client.CircularRedirectException;
import org.geekulcha.R;
import org.geekulcha.VideoDetail;
import org.geekulcha.Util.CircleTransform;
import org.geekulcha.Util.FontChanger;
import org.geekulcha.Util.TimeUtil;

import com.squareup.picasso.Picasso;

import twitter4j.Status;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TagsAdapter extends BaseAdapter {


	List<Status> list;
	Context context;
	public TagsAdapter(Context context,List<Status> list)
	{
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup root) {
		// TODO Auto-generated method stub
        LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CardViewHolder mTweetCardHolder=new CardViewHolder();
		if(convertView==null)
		{

			convertView=li.inflate(R.layout.tags_card,root,false);
            mTweetCardHolder.mImageview=(ImageView)convertView.findViewById(R.id.avatar);
            mTweetCardHolder.mTxtUsername=(TextView)convertView.findViewById(R.id.user);
            mTweetCardHolder.mTxtTwitterName=(TextView)convertView.findViewById(R.id.twitter);
            mTweetCardHolder.mTxtTweet=(TextView)convertView.findViewById(R.id.tweet);
            mTweetCardHolder.mHours=(TextView)convertView.findViewById(R.id.hours);
            mTweetCardHolder.clickView=(View)convertView.findViewById(R.id.clickView1);
            convertView.setTag(mTweetCardHolder);
		}
        else
        {
            mTweetCardHolder=(CardViewHolder)convertView.getTag();
        }
		CircleTransform circlet=new CircleTransform();
		Picasso.with(context).load(list.get(position).getUser().getOriginalProfileImageURL()).transform(circlet).placeholder(R.drawable.card_placeholder).into(mTweetCardHolder.mImageview);
		mTweetCardHolder.mTxtUsername.setText(list.get(position).getUser().getName());
		mTweetCardHolder.mTxtTwitterName.setText("@"+list.get(position).getUser().getScreenName());
		mTweetCardHolder.mTxtTweet.setText(list.get(position).getText());
		mTweetCardHolder.mHours.setText(TimeUtil.getTimeString(list.get(position).getCreatedAt()));
		//System.out.println(list.get(0).getCreatedAt().getTime());
        mTweetCardHolder.mHours.setTypeface(FontChanger.setRobotoRegular(context));
		//set Fonts
        mTweetCardHolder.mTxtUsername.setTypeface(FontChanger.setRobotoRegular(context));
        mTweetCardHolder.mTxtTwitterName.setTypeface(FontChanger.setRobotoLight(context));
        mTweetCardHolder.mTxtTweet.setTypeface(FontChanger.setRobotoLight(context));

		mTweetCardHolder.clickView.setOnClickListener(new CustomListener(position));

		return convertView;
	}


    public class CardViewHolder
    {
       public ImageView  mImageview;
       public TextView mTxtUsername;
       public TextView mTxtTwitterName;
       public TextView mTxtTweet;
       public TextView mHours;
       public View clickView;
    }

	public class CustomListener implements OnClickListener
	{
		int position;
		public CustomListener(int position)
		{
			this.position=position;
		}
		@Override
		public void onClick(View arg0) {
			boolean isInstalled=appInstalledOrNot("com.twitter.android");
			Intent i;
			if(isInstalled==true)
			{
				i=new Intent(Intent.ACTION_VIEW,Uri.parse("twitter://status?status_id="+list.get(position).getId()));
			}
			else 
			{
				i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/<screen_name>/status/"+list.get(position).getId()));
			}
			
			
			context.startActivity(i);
		}
	}


	
	 private boolean appInstalledOrNot(String uri)
     {
         PackageManager pm=context.getPackageManager();
         boolean app_installed = false;
         try
         {
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
                app_installed = true;
         }
         catch (PackageManager.NameNotFoundException e)
         {
                app_installed = false;
         }
         return app_installed ;
 }
	
}
