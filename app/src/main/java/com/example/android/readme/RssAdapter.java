package com.example.android.readme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vox on 6/01/2015.
 */
public class RssAdapter extends BaseAdapter
{

    private int[] colors = new int[] { 0x778899, 0x2f4f4f};
    private final List<RssStore> items;
    private final Context context;

    public RssAdapter(Context context, List<RssStore> items)
    {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public Object getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.rss_store, null);
            holder = new ViewHolder();
            holder.storeFront = (TextView) convertView.findViewById(R.id.storeFront);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.storeFront.setText(items.get(position).getTitle());

        int colorPos = position % colors.length;
        convertView.setBackgroundColor(colors[colorPos]);
        return convertView;
    }

    static class ViewHolder
    {
        TextView storeFront;
    }
}