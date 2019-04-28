package remind.edu.myapplication.Add_mock_result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_add_result {

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
