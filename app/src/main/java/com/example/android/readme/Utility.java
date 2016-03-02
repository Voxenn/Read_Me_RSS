package com.example.android.readme;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Vox on 6/14/2015.
 */
public class Utility
{
    public static String getPreferredUrl(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(context.getString(R.string.pref_link_key),
                context.getString(R.string.pref_link_default));
    }
}
