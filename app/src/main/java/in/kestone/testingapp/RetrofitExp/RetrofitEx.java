package in.kestone.testingapp.RetrofitExp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindView;
import in.kestone.testingapp.R;
import in.kestone.testingapp.RetrofitExp.model.RootElement;
import in.kestone.testingapp.RetrofitExp.model.SpeakerData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitEx extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_ex);
//        Log.e("Read json ", loadJSONFromAsset());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.root_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Toast.makeText(this, "activity", Toast.LENGTH_SHORT).show();
        Api api = retrofit.create(Api.class);

        Call<List<RootElement>> callApi = api.getSpeaker();
        callApi.enqueue(new Callback<List<RootElement>>() {
            @Override
            public void onResponse(Call<List<RootElement>> call, Response<List<RootElement>> response) {
                Log.e("Name ", String.valueOf(response.body()));
//                List<SpeakerData> data = response.body();
//                for(SpeakerData speakerData : data){
//
//                }
            }

            @Override
            public void onFailure(Call<List<RootElement>> call, Throwable t) {
                Toast.makeText(RetrofitEx.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.e("Error ", t.getMessage().toString());
            }
        });


    }

    //read json file from assets folder
//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = this.getAssets().open("document.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
}
