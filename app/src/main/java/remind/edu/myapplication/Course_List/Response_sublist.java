package remind.edu.myapplication.Course_List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Response_sublist {
    @SerializedName("subject")
@Expose
private List<Subjects> subject = null;

    public List<Subjects> getSubject() {
        return subject;
    }

    public void setSubject(List<Subjects> subject) {
        this.subject = subject;
    }
}
