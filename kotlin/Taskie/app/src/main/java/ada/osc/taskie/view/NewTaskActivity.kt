package ada.osc.taskie.view

import ada.osc.taskie.R
import ada.osc.taskie.model.Task
import ada.osc.taskie.model.TaskPriority
import ada.osc.taskie.view.TasksActivity.Companion.EXTRA_TASK
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_new_task.*

class NewTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        initUi()
    }

    private fun initUi() {
        setUpSpinnerSource()

        saveTask.setOnClickListener { saveNewTask() }
    }

    private fun saveNewTask() {
        val title = newTaskTitle.text.toString()
        val description = newTaskDescription.text.toString()
        val priority = newTaskPriority.selectedItem as? TaskPriority ?: TaskPriority.LOW

        val newTask = Task(title = title, description = description, priority = priority)

        val saveTaskIntent = Intent().apply { putExtra(EXTRA_TASK, newTask) }
        setResult(Activity.RESULT_OK, saveTaskIntent)
        finish()
    }

    private fun setUpSpinnerSource() {
        newTaskPriority.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, TaskPriority.values())

        newTaskPriority.setSelection(0)
    }
}
