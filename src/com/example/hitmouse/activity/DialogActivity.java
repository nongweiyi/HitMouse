/**
 * 
 */
package com.example.hitmouse.activity;

import com.example.hitmouse.R;

import android.app.Activity;
import android.app.PendingIntent.OnFinished;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

/**@Description: 
 * 
 * @author 农韦依
 * @date 2016年9月24日
 */
public class DialogActivity extends Activity {
	ImageButton ib_replay;
	ImageButton ib_next;
	Context mContext;
	MediaPlayer mp_levelup;
	MediaPlayer mp_gameover;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFinishOnTouchOutside(false);//设置为true点击区域外消失
		setContentView(R.layout.layout_dialog);
		mContext=this;
		initView();
		initMusic();
		mp_levelup.start();
		
	}

	/**@Description:
	 * 
	 */
	private void initMusic() {
		try{
			mp_levelup=MediaPlayer.create(mContext, R.raw.levelup);
			mp_gameover=MediaPlayer.create(mContext, R.raw.gameover);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

	/**@Description:
	 * 
	 */
	private void initView() {
		ib_replay=(ImageButton)this.findViewById(R.id.ib_replay);
		ib_next=(ImageButton)this.findViewById(R.id.ib_next);
		
		
		
		setListener();
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		this.setResult(GameActivity.mRequestCode);
		super.onDestroy();
	}
	

	/**@Description:
	 * 
	 */
	private void setListener() {
		ib_replay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogActivity.this.finish();
			}
		});
		ib_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "下一关", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
