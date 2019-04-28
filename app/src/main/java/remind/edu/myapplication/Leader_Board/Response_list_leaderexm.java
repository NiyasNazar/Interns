package remind.edu.myapplication.Leader_Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_list_leaderexm {

    @SerializedName("exams")
    @Expose
    private List<LExam> exams = null;

    public List<LExam> getExams() {
        return exams;
    }

    public void setExams(List<LExam> exams) {
        this.exams = exams;
    }

}
