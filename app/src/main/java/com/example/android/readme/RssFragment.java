package com.example.android.readme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;


public class RssFragment extends Fragment implements AdapterView.OnItemClickListener
{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ProgressBar progressBar;
    private ListView listView;
    private View view;
    private Context context;

    public RssFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (view == null)
        {
            view = inflater.inflate(R.layout.fragment_rss, container, false);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            listView = (ListView) view.findViewById(R.id.listView);
            listView.setOnItemClickListener(this);
            startService();
        } else
            {
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);
            }
        return view;
    }

    private void startService()
    {
        Intent intent = new Intent(getActivity(), RssService.class);
        intent.putExtra(RssService.RECEIVER, resultReceiver);
        getActivity().startService(intent);
    }

    private final ResultReceiver resultReceiver = new ResultReceiver(new Handler())
    {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            List<RssStore> items = (List<RssStore>) resultData.getSerializable(RssService.ITEMS);
            if (items != null) {
                RssAdapter adapter = new RssAdapter(getActivity(), items);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
                        Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l)
    {
        /*
        RssAdapter adapter = (RssAdapter) parent.getAdapter();
        RssStore item = (RssStore) adapter.getItem(i);
        Uri uri = Uri.parse(item.getLink());

        Intent intent = new Intent(Intent.ACTION_VIEW, uri );
        startActivity(intent);
        */
        RssAdapter adapter = (RssAdapter) parent.getAdapter();
        RssStore item = (RssStore) adapter.getItem(i);
      //  Uri uri = Uri.parse(item.getLink());
        Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, item.getLink());
        startActivity(intent);

    }
}
