package com.example.android.readme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Vox on 6/15/2015.
 */
public class DetailActivity extends ActionBarActivity
{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu item){
        getMenuInflater().inflate(R.menu.menu_detail, item);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class DetailFragment extends Fragment
    {

        public DetailFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View v = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();
            WebView vw = (WebView) v.findViewById(R.id.webview);

            if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
                String postLink = intent.getStringExtra(Intent.EXTRA_TEXT);

                vw.getSettings().setJavaScriptEnabled(true);
                vw.loadUrl(postLink);
            }

            return v;
        }

        public boolean onOptionsItemSelected(MenuItem item)
        {
            int id = item.getItemId();
            if(id == R.id.action_refresh)
            {

                String link = Utility.getPreferredUrl(getActivity());
                RssService serve = new RssService();

                return true;
            }

            return super.onOptionsItemSelected(item);
        }


    }


}
