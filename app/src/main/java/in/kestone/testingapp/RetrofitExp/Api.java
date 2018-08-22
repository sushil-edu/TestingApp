package in.kestone.testingapp.RetrofitExp;

import java.util.List;

import in.kestone.testingapp.RetrofitExp.model.RootElement;
import in.kestone.testingapp.RetrofitExp.model.SpeakerData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String root_url="https://jsoneditoronline.org/";

    @GET("?id=e3873258727c4861aeae3db894b74ba0")
    Call<List<RootElement>> getSpeaker();

    String URL = "http://pratikbutani.x10.mx";

}
