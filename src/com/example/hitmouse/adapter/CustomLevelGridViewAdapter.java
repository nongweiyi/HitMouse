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

public class CustomLevelGridViewAdapter extends BaseAdapter {

	private Context mContext;
	public int[] imageIds;

	public CustomLevelGridViewAdapter(Context mContext, int[] imageIds) {
		super();
		this.mContext = mContext;
		this.imageIds = imageIds;

	}

	@Override
	public int getCount() {

		return imageIds.length;
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

		ImageView imageView;
		ViewHolder viewHolder;
		if (convertView == null) {

			imageView = (ImageView) View.inflate(mContext, R.layout.level_gridview_item, null);

		} else {

			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(imageIds[position]);

		return imageView;

	}

	class ViewHolder {
		ImageView imageView;
	}
}
