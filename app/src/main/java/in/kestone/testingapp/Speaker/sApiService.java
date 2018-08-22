package in.kestone.testingapp.Speaker;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface sApiService {
    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
//    @Headers("Content-Type: application/json")
//
    @GET("/answers?page=1&pagesize=50&site=stackoverflow")
    Call<SpeakerList> getSpeaker();
}
