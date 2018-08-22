package in.kestone.testingapp.ReadJsonFromAssets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.kestone.testingapp.R;

public class SpkActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;
    MyAdapternew adapter;
    ArrayList<Detail> list = new ArrayList<>();
    ArrayList<SpkList> aryList = new ArrayList<>();
    SpkList ls = new SpkList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spk);
        ButterKnife.bind(this);

        String json = loadJSONFromAsset();

        try {
            JSONObject object = new JSONObject(json);
            ls.setStatus(object.getLong("status"));
            JSONArray array = object.getJSONArray("detail");
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob = array.getJSONObject(i);
                Detail d = new Detail();
                d.setScheduleid(ob.getLong("scheduleid"));
                d.setSpeaker(ob.getString("speaker"));
                d.setScheduledate(ob.getString("scheduledate"));
                d.setScheduletime(ob.getString("scheduletime"));
                d.setStatus(ob.getString("status"));
                d.setLocation(ob.getString("location"));
                d.setDescription(ob.getString("description"));
                list.add(d);

            }
            ls.setDetail(list);
            aryList.add(ls);
            adapter = new MyAdapternew(SpkActivity.this, list);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        System.out.println(gson.toJson(json));


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("document.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
