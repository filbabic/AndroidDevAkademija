package ada.osc.taskie.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Task extends RealmObject implements Serializable{

	private static int sID = 0;

	@Required
	@PrimaryKey
	private String mId;
	private String mTitle;
	private String mDescription;
	private boolean mCompleted;
	private String mPriority;

	public Task() {
	}

	public Task(String title, String description, TaskPriority priority) {
		mId = UUID.randomUUID().toString();
		mTitle = title;
		mDescription = description;
		mCompleted = false;
		mPriority = convertTaskPriorityEnumToString(priority);
	}

	public String getId() {
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

	public void setTaskPriorityEnum(TaskPriority taskPriority) {
		this.mPriority = taskPriority.toString();
	}

	public TaskPriority getTaskPriorityEnum() {
		return TaskPriority.valueOf(mPriority);
	}

	public String convertTaskPriorityEnumToString(TaskPriority taskPriority) {
		return String.valueOf(taskPriority.toString());
	}
}
