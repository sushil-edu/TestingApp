package in.kestone.testingapp.DownloadFile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.kestone.testingapp.R;

class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.MyViewHolder> {

    private List<DownloadFile> downloadList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public MyViewHolder(final View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.ChapName);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }

    }

    public DownloadAdapter(List<DownloadFile> downloadList) {
        this.downloadList = downloadList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.download_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DownloadFile downloadFile = downloadList.get(position);
        holder.title.setText(downloadFile.getName());
    }

    @Override
    public int getItemCount() {
        return downloadList.size();
    }

}
