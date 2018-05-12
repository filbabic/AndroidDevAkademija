package ada.osc.taskie.persistance;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.model.Task;

public class FakeDatabase {

	private List<Task> mTasks;

	public FakeDatabase(){
		mTasks = new ArrayList<>();
	}

	public List<Task> getTasks(){
		return new ArrayList<>(mTasks);
	}

	public void save(Task task){
		mTasks.add(task);
	}

	public void save(List<Task> tasks){
		mTasks.addAll(tasks);
	}

	public void delete(Task task) {
		mTasks.remove(task);
	}
}
