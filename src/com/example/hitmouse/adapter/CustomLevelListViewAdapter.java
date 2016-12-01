/**
 * 
 */
package com.example.hitmouse.adapter;

import java.util.List;

import com.example.hitmouse.R;
import com.example.hitmouse.activity.GameActivity;
import com.example.hitmouse.bean.LevelData;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @Description:
 * 
 * @author 农韦依
 * @date 2016年9月25日
 */
public class CustomLevelListViewAdapter extends BaseAdapter {
	Context mContext;
	List<LevelData> dataList;

	public CustomLevelListViewAdapter(Context mContext, List<LevelData> dataList) {
		super();
		this.mContext = mContext;
		this.dataList = dataList;
	}

	@Override
	public int getCount() {

		return dataList.size();
	}

	@Override
	public Object getItem(int position) {

		return position;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int index = position;
		ViewHoder viewHoder;
		LevelData levelData = dataList.get(position);
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.level_listview_item, null);
			viewHoder = new ViewHoder();
			viewHoder.iv_awordFlag = (ImageView) convertView.findViewById(R.id.iv_awordFlag);
			viewHoder.iv_award = (ImageView) convertView.findViewById(R.id.iv_award);
			viewHoder.gv_level = (GridView) convertView.findViewById(R.id.gv_level);
			convertView.setTag(viewHoder);
		} else {

			viewHoder = (ViewHoder) convertView.getTag();
		}
		viewHoder.iv_awordFlag.setImageResource(levelData.getImage_awordFlagId());
		viewHoder.iv_award.setImageResource(levelData.getImage_awardId());
		CustomLevelGridViewAdapter adapter = new CustomLevelGridViewAdapter(mContext, levelData.getImage_levelIdArr());
		viewHoder.gv_level.setAdapter(adapter);

		viewHoder.gv_level.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int clickposition, long id) {
				startGameActivty();
			}

			
		});

		return convertView;
	}
	private void startGameActivty() {
		Intent intent=new Intent(mContext,GameActivity.class);
		
		mContext.startActivity(intent);
		
	}
	class ViewHoder {
		private ImageView iv_awordFlag;
		private ImageView iv_award;
		private GridView gv_level;
	}

}
