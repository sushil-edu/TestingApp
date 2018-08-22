package in.kestone.testingapp.ReadJsonFromAssets;

import com.google.gson.annotations.SerializedName;


public class Detail {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("scheduledate")
    private String mScheduledate;
    @SerializedName("scheduleid")
    private Long mScheduleid;
    @SerializedName("scheduletime")
    private String mScheduletime;
    @SerializedName("speaker")
    private String mSpeaker;
    @SerializedName("status")
    private String mStatus;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getScheduledate() {
        return mScheduledate;
    }

    public void setScheduledate(String scheduledate) {
        mScheduledate = scheduledate;
    }

    public Long getScheduleid() {
        return mScheduleid;
    }

    public void setScheduleid(Long scheduleid) {
        mScheduleid = scheduleid;
    }

    public String getScheduletime() {
        return mScheduletime;
    }

    public void setScheduletime(String scheduletime) {
        mScheduletime = scheduletime;
    }

    public String getSpeaker() {
        return mSpeaker;
    }

    public void setSpeaker(String speaker) {
        mSpeaker = speaker;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
