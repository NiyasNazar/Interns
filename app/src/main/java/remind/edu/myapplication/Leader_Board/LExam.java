package remind.edu.myapplication.Leader_Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class LExam {
    @SerializedName("exam_id")
    @Expose
    private String examId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("title")
    @Expose
    private String title;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
