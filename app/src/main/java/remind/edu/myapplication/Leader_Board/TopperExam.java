package remind.edu.myapplication.Leader_Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopperExam {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mark")
    @Expose
    private String mark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
