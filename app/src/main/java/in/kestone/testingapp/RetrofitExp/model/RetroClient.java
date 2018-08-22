package in.kestone.testingapp.RetrofitExp.model;

import in.kestone.testingapp.RetrofitExp.Api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
