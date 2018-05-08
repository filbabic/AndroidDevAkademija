package ada.osc.taskie.view

import ada.osc.taskie.model.Task

interface TaskClickListener {
    fun onClick(task: Task)
    fun onLongClick(task: Task)
}
