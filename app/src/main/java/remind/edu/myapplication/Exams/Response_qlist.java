package remind.edu.myapplication.Exams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_qlist {
    @SerializedName("exams")
    @Expose
    private List<Exam> exams = null;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
