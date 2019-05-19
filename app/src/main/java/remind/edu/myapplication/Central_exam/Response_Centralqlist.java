package remind.edu.myapplication.Central_exam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Centralqlist {
    @SerializedName("centralExams")
    @Expose
    private List<CentralExam> centralExams = null;

    public List<CentralExam> getExams() {
        return centralExams;
    }

    public void setExams(List<CentralExam> centralExams) {
        this.centralExams = centralExams;
    }
}
