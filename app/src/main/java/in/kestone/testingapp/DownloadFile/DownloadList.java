package in.kestone.testingapp.DownloadFile;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import in.kestone.testingapp.R;

public class DownloadList extends AppCompatActivity {
    ArrayList<DownloadFile> downloadFiles = new ArrayList<>();
    private RecyclerView recyclerView;
    private DownloadAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_download_list);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        mAdapter = new DownloadAdapter(downloadFiles);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        getFileList();

//
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e("dwnload file ", downloadFiles.get( position ).getPath());
                openPdf(downloadFiles.get(position).getPath());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void getFileList() {
        try {
            File file = new File( Environment.getExternalStorageDirectory() + "/Android/data/ek.alltest");
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
                Log.e("File Name/Path", fileName + "  " + files[i].getAbsolutePath());
                DownloadFile dfile = new DownloadFile();
                dfile.setName(fileName);
                dfile.setPath(files[i].getAbsolutePath());
                downloadFiles.add(dfile);
            }
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    private void openPdf(String filepath) {
        File file = new File(filepath);
        Uri path = Uri.fromFile(file);
        Log.e("file ", path.toString());
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/pdf");
        try {
            startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {
        }
    }
}
