package com.example.broadcast;

import com.example.service.MusicService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;

/**
 * 短信广播接收
 *
 * @author wu
 *
 * 2016-6-12
 */
public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		Object[] objects = (Object[]) intent.getExtras().get("pdus");

		for (Object object : objects) {
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) object);
			String body = sms.getMessageBody();
			String from = sms.getOriginatingAddress();
			if(from.equals("15555215556")){
				if(body.contains("#location")){
					sendLocation(context, from);
				}else if(body.contains("#alarm")){
					alarm(context);
				}
			}
		}
	}

	private void alarm(Context context) {

	}

	private void sendLocation(Context context, final String from) {
		LocationManager lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		String provider = lm.getBestProvider(criteria , true);
		LocationListener listener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {

			}

			@Override
			public void onProviderEnabled(String provider) {

			}

			@Override
			public void onProviderDisabled(String provider) {

			}

			@Override
			public void onLocationChanged(Location location) {
				double longitude = location.getLongitude();//获取经度
				double latitude = location.getLatitude();//获取纬度
				System.out.println(location + " " + latitude);
				SmsManager.getDefault().sendTextMessage(from, null, location + ":" + latitude, null, null);
			}
		};
		lm.requestLocationUpdates(provider, 0, 0, listener);
	}

}
