package in.kestone.testingapp.ReadJsonFromAssets;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.kestone.testingapp.R;
import in.kestone.testingapp.Speaker.Item;

class MyAdapternew implements ListAdapter {
    ArrayList<Detail> aryList;
    Activity activity;
    private LayoutInflater mInflater;
    public MyAdapternew(SpkActivity spkActivity, ArrayList<Detail> aryList) {
        this.activity=spkActivity;
        this.aryList=aryList;
        this.mInflater = LayoutInflater.from(spkActivity);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return aryList.size();
    }

    @Override
    public Detail getItem(int position) {
        return aryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyAdapternew.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = MyAdapternew.ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (MyAdapternew.ViewHolder) convertView.getTag();
        }

        final Detail item = getItem(position);

        vh.textViewName.setText(item.getSpeaker());
        vh.textViewEmail.setText(item.getLocation());

//        vh.textViewName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vcl.passInfo(item.getOwner().getLink().toString());
//            }
//        });
//        vh.textViewEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vcl.passInfo(item.getOwner().getLink().toString());
//            }
//        });

//        Picasso.with(activity).load(item.getOwner().getProfileImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return aryList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewEmail;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewEmail) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewEmail = textViewEmail;
        }

        public static MyAdapternew.ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewEmail = (TextView) rootView.findViewById(R.id.textViewEmail);

            return new MyAdapternew.ViewHolder(rootView, imageView, textViewName, textViewEmail);
        }
    }
}
