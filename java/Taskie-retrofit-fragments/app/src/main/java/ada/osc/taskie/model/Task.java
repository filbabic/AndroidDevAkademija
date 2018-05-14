package ada.osc.taskie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable{

	private static int sID = 0;

	@Expose
	@SerializedName("id")
	private String mId;
	@Expose
	@SerializedName("title")
	private String mTitle;
	@Expose
	@SerializedName("content")
	private String mDescription;
	private boolean mCompleted;
	private TaskPriority mPriority;

	public Task(String title, String description, TaskPriority priority) {
		mId = UUID.randomUUID().toString();
		mTitle = title;
		mDescription = description;
		mCompleted = false;
		mPriority = priority;
	}


	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public boolean isCompleted() {
		return mCompleted;
	}

	public void setCompleted(boolean completed) {
		mCompleted = completed;
	}

	public TaskPriority getPriority() {
		return mPriority;
	}

	public void setPriority(TaskPriority priority) {
		mPriority = priority;
	}
}
