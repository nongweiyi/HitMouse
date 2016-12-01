/**
 * 
 */
package com.example.hitmouse.activity;

import java.io.IOException;
import java.util.List;

import com.example.hitmouse.R;
import com.example.hitmouse.adapter.CustomLevelListViewAdapter;
import com.example.hitmouse.bean.Data;
import com.example.hitmouse.bean.LevelData;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * @Description:
 * 
 * @author 农韦依
 * @date 2016年9月25日
 */
public class LevelActivity extends Activity {

	ListView lv_level;
	Context mContext;
	List<LevelData> dataList;
	MediaPlayer mpBackgroundMusic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		mContext = this;
		initData();
		initView();
		setListviewData();
		startMusic();
	}

	/**
	 * @Description:
	 * 
	 */
	private void startMusic() {
		mpBackgroundMusic = MediaPlayer.create(mContext, R.raw.title_bg);
		try {
			mpBackgroundMusic.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mpBackgroundMusic.start();
		mpBackgroundMusic.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mpBackgroundMusic.start();

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {

		super.onPause();
		mpBackgroundMusic.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mpBackgroundMusic.start();
	}

	/**
	 * @Description:
	 * 
	 */
	private void initData() {
		dataList = Data.getLevelDataList();

	}

	/**
	 * @Description:
	 * 
	 */
	private void setListviewData() {
		CustomLevelListViewAdapter adpter = new CustomLevelListViewAdapter(mContext, dataList);
		lv_level.setAdapter(adpter);

	}

	/**
	 * @Description:
	 * 
	 */
	private void initView() {
		lv_level = (ListView) this.findViewById(R.id.lv_level);

	}

}
