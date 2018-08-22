package in.kestone.testingapp.Speaker;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.kestone.testingapp.RetrofitExp.Api;
import in.kestone.testingapp.RetrofitExp.model.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {
    /**
     * Get Retrofit Instance
     */
//    static Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com/2.2/")
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static sApiService getApiService() {
        return getRetrofitInstance().create(sApiService.class);
    }
}
