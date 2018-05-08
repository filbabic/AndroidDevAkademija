package ada.osc.taskie.persistance

import ada.osc.taskie.model.Task
import ada.osc.taskie.model.TaskGenerator


private const val INITIAL_TASK_COUNT = 10

object TaskRepository {

    private val database by lazy {
        val fakeDatabase = FakeDatabase()
        fakeDatabase.save(TaskGenerator.generate(INITIAL_TASK_COUNT))

        fakeDatabase
    }

    fun getTasks() = database.tasks

    fun saveTask(task: Task) = database.save(task)
    fun removeTask(task: Task) = database.delete(task)
}
