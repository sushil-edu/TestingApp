package in.kestone.testingapp.Speaker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpeakerDetails {

    @SerializedName("scheduleid")
    @Expose
    public String scheduleid;

    @SerializedName("scheduledate")
    @Expose
    public String scheduledate;

    @SerializedName("speaker")
    @Expose
    public String speaker;

    @SerializedName("scheduletime")
    @Expose
    public String scheduletime;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("location")
    @Expose
    public String location;

    @SerializedName("description")
    @Expose
    public String description;

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getScheduledate() {
        return scheduledate;
    }

    public void setScheduledate(String scheduledate) {
        this.scheduledate = scheduledate;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getScheduletime() {
        return scheduletime;
    }

    public void setScheduletime(String scheduletime) {
        this.scheduletime = scheduletime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
