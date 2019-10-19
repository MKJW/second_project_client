package com.mksoft.mkjw_second_project.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mksoft.mkjw_second_project.R


class FirebaseService : FirebaseMessagingService (){
    var serviceBindingModel: FCMServiceBindingModel =
        FCMServiceBindingModel()

    override fun onNewToken(firebaseToken: String) {
        super.onNewToken(firebaseToken)
        Log.d("sendToken", "testToken" + firebaseToken)
        serviceBindingModel.sendToken(firebaseToken)
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if(remoteMessage != null && remoteMessage.data.isNotEmpty()){
            //notification으로 보내기
            sendNotification(remoteMessage)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage){


        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        val channelID = "school network channel"
        val channelName = "message"

        var notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, channelName,  NotificationManager.IMPORTANCE_HIGH)
            channel.description = "This is school network channel"
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.setShowBadge(false)
            notificationManager.createNotificationChannel(channel)
        }
        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var notificationBuilder = NotificationCompat.Builder(this, channelID)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(notificationSound)
        notificationManager.notify(0, notificationBuilder.build())

    }
}