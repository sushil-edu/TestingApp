package in.kestone.testingapp.RetrofitExp.model;

import com.google.gson.annotations.SerializedName;

public class SpeakerData {

    @SerializedName("scheduleid")
    private String id;

    private String speaker;
    private String scheduledate;
    private String scheduletime;
    private String status;
    private String location;
    private String description;
    private String detail;

    public SpeakerData(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public SpeakerData(String id, String speaker, String scheduledate, String scheduletime,
                       String status, String location, String description) {
        this.id = id;
        this.speaker = speaker;
        this.scheduledate = scheduledate;
        this.scheduletime = scheduletime;
        this.status = status;
        this.location = location;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getScheduledate() {
        return scheduledate;
    }

    public String getScheduletime() {
        return scheduletime;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
