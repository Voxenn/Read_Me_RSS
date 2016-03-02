package com.example.android.readme;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * Created by Vox on 6/03/2015.
 */
public class RssService extends IntentService
{


    private static final String TAG = "";
    private static String RSS_LINK = "http://www.kotaku.com/vip.xml";
    public static final String ITEMS = "items";
    public static final String RECEIVER = "receiver";



    public RssService() {
        super("RssService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {


        Log.d(TAG, "Service started");
        List<RssStore> rssProduct = null;
        try {
            RssParser parser = new RssParser();
            rssProduct = parser.parse(getInputStream(RSS_LINK));
        } catch (XmlPullParserException e) {
            Log.w(e.getMessage(), e);
        } catch (IOException e) {
            Log.w(e.getMessage(), e);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEMS, (Serializable) rssProduct);
        ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
        receiver.send(0, bundle);
    }

    public InputStream getInputStream(String link) {
        try {
            URL url = new URL(link);
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            Log.w( TAG, "Exception while retrieving the input stream", e);
            return null;
        }
    }
}