package ada.osc.taskie.model

import java.io.Serializable
import java.util.Objects

class Task(var id: Int = 0,
           val title: String = "",
           val description: String = "",
           val priority: TaskPriority = TaskPriority.LOW,
           val isCompleted: Boolean = false) : Serializable {

    init {
        id = sID++
    }

    companion object {

        private var sID = 0
    }
}
