package remind.edu.myapplication.Generate_otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_gen_otp {
    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

