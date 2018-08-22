package in.kestone.testingapp.Speaker;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.kestone.testingapp.R;
import in.kestone.testingapp.RetrofitExp.model.Contact;

class MyAdapter implements ListAdapter {
    List<Item> list;
    Context context;
    private LayoutInflater mInflater;
    ViewClickListener vcl;


    public MyAdapter(ActivitySpeaker activitySpeaker, List<Item> list) {
        this.context = activitySpeaker;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        vcl=(ViewClickListener)context;

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
    public int getCount() {
        return list.size();
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public Item getItem(int position) {
        return list.get(position);
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
        final MyAdapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = MyAdapter.ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (MyAdapter.ViewHolder) convertView.getTag();
        }

        final Item item = getItem(position);

        vh.textViewName.setText(item.getOwner().getDisplayName());
        vh.textViewEmail.setText(item.getOwner().getUserType());

        vh.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vcl.passInfo(item.getOwner().getLink().toString());
            }
        });
        vh.textViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vcl.passInfo(item.getOwner().getLink().toString());
            }
        });

        Picasso.with(context).load(item.getOwner().getProfileImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return list.size();
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

        public static MyAdapter.ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewEmail = (TextView) rootView.findViewById(R.id.textViewEmail);

            return new MyAdapter.ViewHolder(rootView, imageView, textViewName, textViewEmail);
        }
    }
}
