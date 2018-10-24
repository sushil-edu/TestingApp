package in.kestone.testingapp.ActionBar;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.kestone.testingapp.R;
import in.kestone.testingapp.Speaker.Item;
import in.kestone.testingapp.Speaker.ViewClickListener;

class NavMenuAdapter<T> implements ListAdapter {

    List<Item> list;
    Context context;
    ViewClickListener vcl;
    private LayoutInflater mInflater;

    public NavMenuAdapter(AwesomeBar2 awesomeBar2, List<Item> list) {
        this.context = awesomeBar2;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        vcl = (ViewClickListener) context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Item getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, final View view, final ViewGroup viewGroup) {
        final ViewHolder vh;
        if (view == null) {
            View view2 = mInflater.inflate(R.layout.layout_row_view_nav_menu, viewGroup, false);
            vh = ViewHolder.create((RelativeLayout) view2);
            view2.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        final Item item = getItem(i);

        vh.textViewName.setText(item.getOwner().getDisplayName());
        vh.textViewEmail.setText(item.getOwner().getUserType());

        vh.layoutDetail.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View v) {
                Animation animation1 = new AlphaAnimation(0.0f, 0.5f);
                animation1.setDuration(100);
                v.startAnimation(animation1);

//                Toast.makeText(AwesomeBar2.this, "fsdfsdfsdfsd"+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
//                TransitionManager.beginDelayedTransition(viewGroup);
//                visible = !visible;
//                vh.textViewName.setVisibility(visible ? View.VISIBLE : View.GONE);
                vcl.passInfo(item.getOwner().getDisplayName().toString());
            }
        });
//        vh.textViewEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vcl.passInfo(item.getOwner().getLink().toString());
//            }
//        });

        Picasso.with(context).load(item.getOwner().getProfileImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    @Override
    public int getItemViewType(int i) {
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
        public final RelativeLayout layoutDetail;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewEmail, RelativeLayout layoutDetail) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewEmail = textViewEmail;
            this.layoutDetail = layoutDetail;
        }

        public static NavMenuAdapter.ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewEmail = (TextView) rootView.findViewById(R.id.textViewEmail);
            RelativeLayout layoutDetail = rootView.findViewById(R.id.layout_detail);

            return new ViewHolder(rootView, imageView, textViewName, textViewEmail, layoutDetail);
        }
    }
}
