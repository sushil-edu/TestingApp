package in.kestone.testingapp.Speaker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {
    @SerializedName("scheduleid")
    @Expose
    private Integer scheduleid;
    @SerializedName("speaker")
    @Expose
    private String speaker;
    @SerializedName("scheduledate")
    @Expose
    private String scheduledate;
    @SerializedName("scheduletime")
    @Expose
    private String scheduletime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getScheduledate() {
        return scheduledate;
    }

    public void setScheduledate(String scheduledate) {
        this.scheduledate = scheduledate;
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