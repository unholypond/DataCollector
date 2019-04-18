package edu.metroState.ics372GroupProject3.dataCollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SiteAdapter extends BaseAdapter {

    protected LayoutInflater myInflator;
    protected String[] siteID;
    protected String[] readingType;
    protected String[] readingID;
    protected String[] readingValue;
    protected String[] readingDate;

    /*Adapter constructor */
    public SiteAdapter(Context context, String[] siteID, String[] readingType, String[]readingID,
            String[] readingValue, String[]readingDate){
        myInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.siteID = siteID;
        this.readingType = readingType;
        this.readingID = readingID;
        this.readingValue = readingValue;
        this.readingDate = readingDate;
    }


    @Override
    public int getCount() {
        return siteID.length;
    }

    @Override
    public Object getItem(int position) {
        return siteID[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = myInflator.inflate(R.layout.activity_import, null);
        /*implement the components of the view to be displayed*/

        return view;
    }
}
