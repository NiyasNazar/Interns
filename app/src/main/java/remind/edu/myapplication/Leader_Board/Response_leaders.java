package remind.edu.myapplication.Leader_Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_leaders {
    @SerializedName("exams")
    @Expose
    private List<TopperExam> exams = null;

    public List<TopperExam> getExams() {
        return exams;
    }

    public void setExams(List<TopperExam> exams) {
        this.exams = exams;
    }
}
