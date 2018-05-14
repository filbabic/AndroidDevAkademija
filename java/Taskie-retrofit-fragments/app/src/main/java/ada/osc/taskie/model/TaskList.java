package ada.osc.taskie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskList {

    @Expose
    @SerializedName("notes")
    public List<Task> mTaskList;
}
