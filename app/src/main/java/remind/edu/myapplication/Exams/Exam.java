package remind.edu.myapplication.Exams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exam {
     @SerializedName("exam_id")
     @Expose
     private String examId;
     @SerializedName("title")
     @Expose
     private String title;
     @SerializedName("mark")
     @Expose
     private String mark;
     @SerializedName("Question")
     @Expose
     private String question;

     public String getExamId() {
         return examId;
     }

     public void setExamId(String examId) {
         this.examId = examId;
     }

     public String getTitle() {
         return title;
     }

     public void setTitle(String title) {
         this.title = title;
     }

     public String getMark() {
         return mark;
     }

     public void setMark(String mark) {
         this.mark = mark;
     }

     public String getQuestion() {
         return question;
     }

     public void setQuestion(String question) {
         this.question = question;
     }
}
