package remind.edu.myapplication.Sub_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_sub_details {
    @SerializedName("documents")
    @Expose
    private List<Documentss> documents = null;
    @SerializedName("videos")
    @Expose
    private List<Vide> videos = null;

    public List<Documentss> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Documentss> documents) {
        this.documents = documents;
    }

    public List<Vide> getVideos() {
        return videos;
    }

    public void setVideos(List<Vide> videos) {
        this.videos = videos;
    }
}
