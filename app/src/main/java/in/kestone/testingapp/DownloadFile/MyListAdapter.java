package in.kestone.testingapp.DownloadFile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;

import java.io.File;
import java.util.ArrayList;

import in.kestone.testingapp.R;

class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    ProgressDialog progressDialog;

    public MyListAdapter(Activity context, String[] maintitle) {
        super(context, R.layout.row_item, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;


    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate( R.layout.row_item, null,true);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        final TextView titleText = (TextView) rowView.findViewById(R.id.tv_label);
        final ImageView delete = rowView.findViewById( R.id.iv_delete );
        delete.setVisibility( View.GONE );

        titleText.setText(maintitle[position]);

            titleText.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!checkFile( titleText.getText().toString() )) {
                        downLoadFile( context, "https://drive.google.com/uc?export=download&id=1sbER2mh7h_woK5u1-YaZQcyJqU7b6EzM",
                                maintitle[position] + ".pdf", delete );
                    }else {
//                        Toast.makeText( context, "File already downloaded.", Toast.LENGTH_SHORT ).show();
                        openPdf( maintitle[position]+".pdf" );

                    }

                }
            } );
       // }
        if(!checkFile( titleText.getText().toString() )){
            delete.setVisibility( View.GONE );
        }else {
            delete.setVisibility( View.VISIBLE );
        }

        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getFileList(titleText.getText().toString())){
                    delete.setVisibility( View.GONE );
                }else {
                    delete.setVisibility( View.VISIBLE );
                }
            }
        } );

        return rowView;

    };


    private void downLoadFile(final Context context, String Url, final String fileName, final ImageView delete) {
        showProgress(context);
        File file = new File( Environment.getExternalStorageDirectory() + "/Android/data/ek.alltest");
        if (!file.exists())
            file.mkdirs();

        AndroidNetworking.download(Url, file.getPath(), fileName)
                .setTag("downloadTest")
                .setPriority( Priority.HIGH)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        Log.e("Progress", bytesDownloaded + "");
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.e("Complete", "Complete");
                        progressDialog.dismiss();
                        Toast.makeText(context, "Download Complete", Toast.LENGTH_SHORT).show();
                        delete.setVisibility( View.VISIBLE );
                        openPdf(fileName);
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("Fail", "Fail");
                        progressDialog.dismiss();
                        Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showProgress(Context context) {
        if (progressDialog != null) {
            progressDialog.setMessage("Downloading...");
            progressDialog.show();
        } else {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Downloading...");
            progressDialog.show();
        }
    }

    private boolean getFileList(String filenm) {
        try {
            File file = new File( Environment.getExternalStorageDirectory() + "/Android/data/ek.alltest");
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
                Log.e("File Name/Path", files[i].getAbsolutePath());
                if(fileName.equalsIgnoreCase( filenm )){
                    Toast.makeText( context, "File deleted", Toast.LENGTH_SHORT ).show();
                    files[i].delete();
                    return true;

                }

            }
           // mAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
        return false;
    }
    private boolean checkFile(String filenm) {
        try {
            File file = new File( Environment.getExternalStorageDirectory() + "/Android/data/ek.alltest");
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
                Log.e("File Name/Path", files[i].getAbsolutePath());
                if(fileName.equalsIgnoreCase( filenm )){

                    return true;

                }

            }
            // mAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
        return false;
    }

    private void openPdf(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/ek.alltest/"+fileName);
        Uri path = Uri.fromFile( file );
        Log.e("File path ",path.toString());
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/pdf");
        try {
            context.startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {
            Log.e("Excp ", e.getMessage().toString());
        }
    }

}
