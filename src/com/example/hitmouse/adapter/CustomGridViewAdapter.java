package com.example.hitmouse.adapter;

import com.example.hitmouse.R;
import com.example.hitmouse.activity.GameActivity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomGridViewAdapter extends BaseAdapter {
	  
    private Context mContext;
    public Integer[]  mAnimIds=null;
    public int appearPositon;
    int delayMillis=GameActivity.DURING_TIME/2;
    
	public CustomGridViewAdapter(Context mContext,Integer[] mAnimIds) {
		super();
		this.mContext = mContext;
		this.mAnimIds=mAnimIds;
		
	}
	

	@Override
	public int getCount() {
		
		return mAnimIds.length;
	}

	@Override
	public Object getItem(int arg0) {
		
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		
		final ImageView imageView;
        if (convertView == null)
        {
            imageView=(ImageView) View.inflate(mContext, R.layout.gridview_item, null);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        
        //imageView.setImageResource(mImageIds[position]);
        //imageView.setBackgroundResource(mImageIds[position]);
        imageView.setBackgroundResource(mAnimIds[position]);
        AnimationDrawable ad=(AnimationDrawable)imageView.getBackground();
 	    ad.start();
	     if(position==appearPositon&&appearPositon!=-1){
	    	   Handler handler=new Handler();
	    	   handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					
					imageView.setBackgroundResource(R.anim.anim_disappear);
			        AnimationDrawable ad=(AnimationDrawable)imageView.getBackground();
			 	    ad.start();
					
				}
			}, delayMillis);
	    	   
	    	   
	       }
 	    
 	  
 	   
        
        return imageView;
		
	}

}
