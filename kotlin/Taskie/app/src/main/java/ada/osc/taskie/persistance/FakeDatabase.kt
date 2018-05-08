package ada.osc.taskie.persistance

import ada.osc.taskie.model.Task

class FakeDatabase {

    val tasks = mutableListOf<Task>()

    fun save(task: Task) = tasks.add(task)
    fun save(tasks: List<Task>) = this.tasks.addAll(tasks)
    fun delete(task: Task) = tasks.remove(task)
}
