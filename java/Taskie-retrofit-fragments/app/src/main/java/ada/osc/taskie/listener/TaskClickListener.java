package ada.osc.taskie.listener;

import ada.osc.taskie.model.Task;

public interface TaskClickListener {
	void onClick(Task task);
	void onLongClick(Task task);
}
