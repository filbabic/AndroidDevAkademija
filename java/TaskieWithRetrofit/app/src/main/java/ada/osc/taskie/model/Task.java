package ada.osc.taskie.model;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable{

	private static int sID = 0;

	int mId;
	private String mTitle;
	private String mDescription;
	private boolean mCompleted;
	private TaskPriority mPriority;

	public Task(String title, String description, TaskPriority priority) {
		mId = sID++;
		mTitle = title;
		mDescription = description;
		mCompleted = false;
		mPriority = priority;
	}

	public int getId() {
		return mId;
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
