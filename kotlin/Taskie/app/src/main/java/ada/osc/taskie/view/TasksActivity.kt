package ada.osc.taskie.view

import ada.osc.taskie.R
import ada.osc.taskie.common.getIntent
import ada.osc.taskie.common.toast
import ada.osc.taskie.model.Task
import ada.osc.taskie.persistance.TaskRepository
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_tasks.*

class TasksActivity : AppCompatActivity() {

    private val taskAdapter: TaskAdapter by lazy { TaskAdapter(taskClickListener) }

    private val taskClickListener: TaskClickListener = object : TaskClickListener {
        override fun onClick(task: Task) = showTaskMessage(task)

        override fun onLongClick(task: Task) {
            TaskRepository.removeTask(task)
            updateTasks()
        }
    }

    companion object {
        private val TAG = TasksActivity::class.java.simpleName
        private const val REQUEST_NEW_TASK = 10

        const val EXTRA_TASK = "task"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        initUi()
        updateTasks()
    }

    private fun initUi() {
        setUpRecyclerView()

        addNewTask.setOnClickListener { startActivityForResult(getIntent<NewTaskActivity>(), REQUEST_NEW_TASK) }
    }

    private fun setUpRecyclerView() {
        val orientation = LinearLayoutManager.VERTICAL

        val layoutManager = LinearLayoutManager(this, orientation, false)
        val decoration = DividerItemDecoration(this, orientation)
        val animator = DefaultItemAnimator()

        allTasks.apply {
            setLayoutManager(layoutManager)
            addItemDecoration(decoration)
            itemAnimator = animator
            adapter = taskAdapter
        }
    }

    private fun updateTasks() {
        val tasks = TaskRepository.getTasks()
        taskAdapter.updateTasks(tasks)

        tasks.forEach { task -> Log.d(TAG, task.title) }
    }

    private fun showTaskMessage(task: Task) = toast(task.title + "\n" + task.description)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_NEW_TASK && resultCode == Activity.RESULT_OK) {
            if (data?.hasExtra(EXTRA_TASK) == true) {
                val task = data.getSerializableExtra(EXTRA_TASK) as Task
                TaskRepository.saveTask(task)
                updateTasks()
            }
        }
    }
}
