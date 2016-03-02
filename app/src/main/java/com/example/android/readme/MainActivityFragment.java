package com.example.android.readme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
{

    public MainActivityFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        Intent intent = getActivity().getIntent();

        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
            String postLink = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((WebView) v.findViewById(R.id.webview)).getSettings().setJavaScriptEnabled(true);
            ((WebView) v.findViewById(R.id.webview)).loadUrl(postLink);
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
