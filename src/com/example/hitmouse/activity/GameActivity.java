package com.example.hitmouse.activity;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.hitmouse.R;
import com.example.hitmouse.adapter.CustomGridViewAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
	GridView gridView;
	TextView tv_score;
	TextView tv_time;
	Handler mHandler;
	BaseAdapter mAdapter;
	
	Integer[]   mImageIds   = 
        { 
                R.anim.anim_empty,
                R.anim.anim_empty,
                R.anim.anim_empty,
                R.anim.anim_empty,
                R.anim.anim_empty,
                R.anim.anim_empty,
                R.anim.anim_empty,
                R.anim.anim_empty,
                R.anim.anim_empty,
        };
	int mousePosition=-1;
	int lastMousePosition=-1;
	long lastHitTime=-1;
	int mScore=0;
	int mTime=20;
	
	int defaultTime=mTime;
	
	
	final int SCORE_FLAG=0;
	public final static int DURING_TIME=3000;
	final int TIMER_DELAY=1000;
	final int TIME_REFLASH=0x11;
	public static final int mRequestCode=0x22;
	
	Timer timeRefleshTimer;
	Timer goTimer;
	
	MediaPlayer mMediaPlayerBackground ;
	MediaPlayer mMediaPlayerBeat ;
	MediaPlayer mMediaPlayerEscape ;
	
	
	private Context mContex;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		mContex=this;
		initView();
		setListener();
		setHandler();
		setTimer();
		initMusic();
		mMediaPlayerBackground.start();
		
		
	}
	/**@Description:
	 * 
	 */
	private void initMusic(){
		mMediaPlayerBackground = MediaPlayer.create(this,R.raw.backmusic);
		mMediaPlayerBeat = MediaPlayer.create(this,R.raw.beat);
		mMediaPlayerEscape = MediaPlayer.create(this,R.raw.escape);
		try {
			mMediaPlayerBackground.prepare();
			
			mMediaPlayerBeat.prepare(); 
			mMediaPlayerEscape.prepare();
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}  
		
		mMediaPlayerBackground.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {  
	          
	            @Override  
	            public void onCompletion(MediaPlayer arg0) {  
	            	mMediaPlayerBackground.start();  
	            	mMediaPlayerBackground.setLooping(true);             
	            }  
	        });  
	}
	void setMyText(int flag,int parm){
		if(flag==SCORE_FLAG){
			tv_score.setText("Score:"+parm);
		}else{
			tv_time.setText("Time:"+parm+"s");
		}
		
		
	}
	
	@Override
	protected void onPause() {
		
		super.onPause();
		if(mMediaPlayerBackground.isPlaying()){
			mMediaPlayerBackground .pause();
		}
		if(mMediaPlayerBeat.isPlaying()){
			mMediaPlayerBeat .pause();
		}
		if(mMediaPlayerEscape.isPlaying()){
			mMediaPlayerEscape.pause();
		}
		stopTimer();
		
		
		
		
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
			mMediaPlayerBackground .start();
		
		
	}

	private void setHandler() {
		mHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				int randomInt=msg.arg1;
				//刷新时间
				if(randomInt==TIME_REFLASH){
					
					setMyText(1,mTime--);
					if(mTime<0){
						stopTimer();
						initAnimToEmpty();
						showCompleteDialog();
						//Toast.makeText(GameActivity.this, "玩完了 分数="+mScore, Toast.LENGTH_SHORT).show();
					}
				}else{
					mousePosition=randomInt;
					for(int index=0;index<mImageIds.length;index++){
						if(index==randomInt){
							mImageIds[index]=R.anim.anim_appear;
						}else{
							mImageIds[index]=R.anim.anim_empty;
						}
					}
					
					((CustomGridViewAdapter) mAdapter).mAnimIds=mImageIds;
					((CustomGridViewAdapter) mAdapter).appearPositon=randomInt;
					
					mAdapter.notifyDataSetChanged();
				}
				
				
					
				
			}

			private void initAnimToEmpty() {
				for(int index=0;index<mImageIds.length;index++){
					
						mImageIds[index]=R.anim.anim_empty;
					
				}
				((CustomGridViewAdapter) mAdapter).mAnimIds=mImageIds;
				((CustomGridViewAdapter) mAdapter).appearPositon=-1;
				
				mAdapter.notifyDataSetChanged();
				
			}
		};
		
	}
	void stopTimer(){
		goTimer.cancel();
		timeRefleshTimer.cancel();
	}

	private void setTimer() {
		 goTimer = new Timer();  
		 goTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				//获取一个0-8的随机数
				Random rnd = new Random();
				int randomInt = rnd.nextInt(8);
				Message msg=mHandler.obtainMessage();
				msg.arg1=randomInt;
				mHandler.sendMessage(msg);
				
			}
		}, TIMER_DELAY, DURING_TIME);  
	     
	    timeRefleshTimer= new Timer(); 
	    timeRefleshTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Message msg=mHandler.obtainMessage();
				msg.arg1=TIME_REFLASH;
						
				mHandler.sendMessage(msg);
				
			}
		}, 0,1000);
	}

	private void setListener() {
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				if(position==mousePosition){
					mMediaPlayerBeat.start();
					long during=System.currentTimeMillis()-lastHitTime;
					//点击的不是同一个
					if(lastMousePosition!=mousePosition){
						mScore++;
					}else{
						//点击的是同一个，需要判断时间
						if(during>=DURING_TIME){
							mScore++;
						}
					}
					setMyText(SCORE_FLAG, mScore);
					lastMousePosition=position;
					lastHitTime=System.currentTimeMillis();
					for(int index=0;index<mImageIds.length;index++){
						if(index==position){
							mImageIds[index]=R.anim.anim_hit;
						}else{
							mImageIds[index]=R.anim.anim_empty;
						}
					}
					((CustomGridViewAdapter) mAdapter).mAnimIds=mImageIds;
					((CustomGridViewAdapter) mAdapter).appearPositon=-1;
					
					mAdapter.notifyDataSetChanged();
					
					/*//打完之后让他500m后消失
					Handler handler=new Handler();
					handler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							
							for(int index=0;index<mImageIds.length;index++){
									mImageIds[index]=R.drawable.empty;
								
							}
							((CustomGridViewAdapter) mAdapter).mImageIds=mImageIds;
							
							mAdapter.notifyDataSetChanged();
						}
					}, 100);*/
					
				}/*else{
					for(int index=0;index<mImageIds.length;index++){
						if(index==position){
							mImageIds[index]=R.drawable.beat_empty;
						}else if(index==mousePosition){
							mImageIds[index]=R.drawable.mouse;
						}else {
							mImageIds[index]=R.drawable.empty;
						}
					}
					((CustomGridViewAdapter) mAdapter).mImageIds=mImageIds;
					
					mAdapter.notifyDataSetChanged();
				}
				*/
				
				
			}
		});
		
	}
	
	void showCompleteDialog(){

		Intent intent =new Intent(mContex,DialogActivity.class);
		//startActivity(intent);
		startActivityForResult(intent, mRequestCode);
		
		 
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(mRequestCode==requestCode){
			replay();
		}
	}
	void replay(){
		setTimer();
		mousePosition=-1;
		lastMousePosition=-1;
		lastHitTime=-1;
		mScore=0;
		mTime=defaultTime;
	}

	private void initView() {
		gridView=(GridView) this.findViewById(R.id.gridView);
		tv_score=(TextView) this.findViewById(R.id.tv_score);
		tv_time=(TextView) this.findViewById(R.id.tv_time);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		setMyText(SCORE_FLAG,0);
		setMyText(1,60);
		mAdapter=new CustomGridViewAdapter(this,mImageIds);
		
		gridView.setAdapter( mAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

}
