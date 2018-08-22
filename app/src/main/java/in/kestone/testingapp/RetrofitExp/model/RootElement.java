package in.kestone.testingapp.RetrofitExp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootElement {

    @SerializedName("detail")
    @Expose
    private ArrayList<SpeakerData> speakerData = new ArrayList<>();

    /**
     * @return The contacts
     */
    public ArrayList<SpeakerData> getSpeakerData() {
        return speakerData;
    }

    /**
     * @param speakerData The contacts
     */
    public void setSpeakerData(ArrayList<SpeakerData> speakerData) {
        this.speakerData = speakerData;
    }

    @SerializedName("status")
    private String status;

}
