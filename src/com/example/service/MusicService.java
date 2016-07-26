package com.example.service;

import com.example.phone_assistant.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

	private MediaPlayer player;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	
		player = MediaPlayer.create(getApplicationContext(), R.raw.yy);
		//Æô¶¯²¥·ÅÆ÷
		player.start();
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		player.stop();//Í£Ö¹
		player.release();//ÊÍ·Å
		super.onDestroy();
	}
	
	

}
