package com.example.android.readme;

/**
 * Created by Vox on 6/01/2015.
 */
public class RssStore
{
    private final String title;
    private final String link;

    public RssStore(String title, String link)
    {
        this.title = title;
        this.link = link;
    }

    public String getTitle()
    {
        return title;
    }

    public String getLink()
    {
        return link;
    }
}
