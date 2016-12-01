
package com.example.hitmouse.activity;

import java.io.IOException;

import com.example.hitmouse.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * @Description:
 * 
 * @author ũΤ��
 * @date 2016��9��25��
 */
public class FlushActivity extends Activity {
	ImageView iv_title, iv_dog, iv_hammer;
	Context mContext;
	Handler mHandler;
	MediaPlayer mp_titleAppear;
	MediaPlayer mp_dogHit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_flush);
		initView();
		initAnimotion();
		initMediaPlayer();
		mp_titleAppear.start();

	}

	/**
	 * @Description:
	 * 
	 */
	private void initMediaPlayer() {
		mp_titleAppear = MediaPlayer.create(mContext, R.raw.flush_bg);
		mp_dogHit = MediaPlayer.create(mContext, R.raw.beat);
		try {
			mp_titleAppear.prepare();
			mp_dogHit.prepare();

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Description:
	 * 
	 */
	private void initAnimotion() {
		final AnimationSet animationSet = new AnimationSet(true);

		// ���Ŷ���
		final ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, AnimationSet.RELATIVE_TO_SELF, 0.5f,
				AnimationSet.RELATIVE_TO_SELF, 0.5f);

		scaleAnimation.setDuration(1000);
		animationSet.addAnimation(scaleAnimation);
		iv_title.startAnimation(animationSet);

		// ��ת����
		final RotateAnimation rotateAnimation = new RotateAnimation(0, 360, AnimationSet.RELATIVE_TO_SELF, 0.5f,
				AnimationSet.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(1000);

		// ����֡����

		scaleAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// ��ʼ�������Ŷ���ʱ���ű�������

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// �������Ŷ������֮�󣬲���һ����ת�Ķ���
				// scaleAnimation.cancel();
				animationSet.getAnimations().clear();
				animationSet.addAnimation(rotateAnimation);
				iv_title.startAnimation(animationSet);

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}
		});
		// ��ת��������
		rotateAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// �������֮�����ֹͣ
				animationSet.getAnimations().clear();
				// ��ʼ�������ӵĶ���
				iv_dog.setBackgroundResource(R.anim.anim_appear);
				AnimationDrawable ad_dog = (AnimationDrawable) iv_dog.getBackground();
				ad_dog.start();

				// ��������֮�󣬲�����������
				mHandler = new Handler();
				mHandler.postDelayed(palyHammerAnimation, 500);
			}
		});

	}

	Runnable palyHammerAnimation = new Runnable() {

		@Override
		public void run() {
			// ��������
			iv_hammer.setBackgroundResource(R.anim.anim_hammer);
			AnimationDrawable ad_hammer = (AnimationDrawable) iv_hammer.getBackground();
			ad_hammer.start();
			mp_dogHit.start();
			mHandler.postDelayed(playHitAnimation, 200);

		}
	};
	Runnable startLevelActivity = new Runnable() {

		@Override
		public void run() {
			Intent intent = new Intent(mContext, LevelActivity.class);

			startActivity(intent);
			finish();

		}
	};
	Runnable playHitAnimation = new Runnable() {

		@Override
		public void run() {

			iv_dog.setBackgroundResource(R.anim.anim_hit);
			AnimationDrawable ad_dog = (AnimationDrawable) iv_dog.getBackground();
			ad_dog.start();
			iv_hammer.setBackground(null);
			mHandler.postDelayed(startLevelActivity, 600);

		}
	};

	private void initView() {
		iv_title = (ImageView) this.findViewById(R.id.iv_title);
		iv_dog = (ImageView) this.findViewById(R.id.iv_dog);
		iv_hammer = (ImageView) this.findViewById(R.id.iv_hammer);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {

		super.onDestroy();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {

		super.onPause();
		mp_dogHit.stop();
		mp_titleAppear.stop();
	}

}
