package org.geekulcha.Util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;

import com.squareup.picasso.Transformation;

public class CircleTransform  implements Transformation{

	@Override
	public String key() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bitmap transform(Bitmap bitmap) {
		 Bitmap output;

		    if (bitmap.getWidth() > bitmap.getHeight()) {
		        output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
		    } else {
		        output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
		    }

		    Canvas canvas = new Canvas(output);

		    final int color = 0xff424242;
		    final Paint paint = new Paint();
		    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		    float r = 0;

		    if (bitmap.getWidth() > bitmap.getHeight()) {
		        r = bitmap.getHeight() / 2;
		    } else {
		        r = bitmap.getWidth() / 2;
		    }
		    
		   
		    
		    paint.setAntiAlias(true);
		    canvas.drawARGB(0, 0, 0, 0);
		    paint.setColor(color);
		    canvas.drawCircle(r, r, r, paint);
		    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		    canvas.drawBitmap(bitmap, rect, rect, paint);
		    
		    if(output!=bitmap)
		    {
		    	bitmap.recycle();
		    }
		    return output;
	}

}
