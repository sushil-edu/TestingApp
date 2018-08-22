package in.kestone.testingapp.ReadJsonFromAssets;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpkList {

    @SerializedName("detail")
    private List<Detail> mDetail;
    @SerializedName("status")
    private Long mStatus;

    public List<Detail> getDetail() {
        return mDetail;
    }

    public void setDetail(List<Detail> detail) {
        mDetail = detail;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
