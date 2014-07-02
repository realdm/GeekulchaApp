package org.geekulcha.adapters;

import java.util.ArrayList;

import org.geekulcha.R;
import org.geekulcha.VideoDetail;
import org.geekulcha.Beans.Items;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoAdapter extends BaseAdapter {

	Context context;
	ArrayList<Items> items;
	
	public VideoAdapter(Context context, ArrayList<Items> items)
	{
		this.context=context;
		this.items=items;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size()-1;
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
	public View getView(int position, View convertview, ViewGroup viewGroup) {
		

		VideoCardHolder mHolder=new VideoCardHolder();
        LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(convertview==null)
		{

			convertview=li.inflate(R.layout.videos_card,viewGroup,false);
            mHolder.mtxtTitle=(TextView)convertview.findViewById(R.id.videoTitle);
            mHolder.mImgCard=(ImageView)convertview.findViewById(R.id.videoThumb);
            mHolder.mClickView=(View)convertview.findViewById(R.id.clickView);

            convertview.setTag(mHolder);
			
		}
        else
        {
            mHolder=(VideoCardHolder)convertview.getTag();
        }
		
		mHolder.mtxtTitle.setText(items.get(position).getSnippet().getTitle());
		Picasso.with(context).load(items.get(position).getSnippet().getThumbnails().getHigh().getUrl()).into(mHolder.mImgCard);
		mHolder.mClickView.setOnClickListener(new CustomListener(position));
		
		return convertview;
			
	}


    public class VideoCardHolder
    {
        public TextView mtxtTitle;
        public ImageView mImgCard;
        public View mClickView;
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
			Intent i=new Intent(context, VideoDetail.class);
			i.putExtra("item",items.get(position));
			context.startActivity(i);
		}
	}

}
