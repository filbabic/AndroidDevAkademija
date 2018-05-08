package ada.osc.taskie.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ada.osc.taskie.R
import ada.osc.taskie.model.Task
import ada.osc.taskie.model.TaskPriority
import kotlinx.android.synthetic.main.item_task.view.*

internal class TaskAdapter(private val taskClickListener: TaskClickListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    fun updateTasks(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.displayData(tasks[position], taskClickListener)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    internal inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun displayData(task: Task, listener: TaskClickListener) = with(itemView) {
            taskTitle.text = task.title
            taskDescription.text = task.description

            taskPriority.setImageResource(when (task.priority) {
                TaskPriority.LOW -> R.color.taskpriority_low
                TaskPriority.MEDIUM -> R.color.taskpriority_medium
                TaskPriority.HIGH -> R.color.taskpriority_high
            })

            setOnClickListener { listener.onClick(task) }
            setOnLongClickListener{
                listener.onLongClick(task)
                true
            }
        }
    }
}
