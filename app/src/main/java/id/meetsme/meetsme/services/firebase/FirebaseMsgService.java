package id.meetsme.meetsme.services.firebase;

/**
 * Created by Ibam on 9/9/2017.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import id.meetsme.meetsme.R;
import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.messagelist.MessageListActivity;
import id.meetsme.meetsme.services.RealmServices;
import id.meetsme.meetsme.services.models.response.ChatIncomingModel;

public class FirebaseMsgService extends FirebaseMessagingService {
    private static final String TAG = "MyAndroidFCMService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String json = remoteMessage.getNotification().getBody();
        ChatIncomingModel newChat = Helper.jsonToObject(json, ChatIncomingModel.class);
        //Log data to Log Cat
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        //create notification
        createNotification(remoteMessage.getNotification().getBody());

        String name = randomName();
        // realm things
        RealmServices.createChatRoom(Integer.parseInt(newChat.getSender_id()), name, newChat
                .getMessage
                        ());

        RealmServices.createMessage(Integer.parseInt(newChat.getSender_id()), name, newChat.getMessage());
    }

    private String randomName() {
        String[] arrNames = getResources().getStringArray(R.array.anonymous_name);
        Random generator = new Random();
        int randomIndex = generator.nextInt(arrNames.length);
        return arrNames[randomIndex];
    }

    private void createNotification(String messageBody) {
        Intent intent = new Intent(this, MessageListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Message!")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }
}