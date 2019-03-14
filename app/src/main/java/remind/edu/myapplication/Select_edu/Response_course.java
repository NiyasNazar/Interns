package remind.edu.myapplication.Select_edu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_course {
    @SerializedName("course")
    @Expose
    private List<Course> course = null;

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

}

