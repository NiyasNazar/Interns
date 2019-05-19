package remind.edu.myapplication.Central_exam.Servertime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_servertime {


        @SerializedName("time")
        @Expose
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }

