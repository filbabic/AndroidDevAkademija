package ada.osc.taskie.model

data class TaskKotlin(val id: String = "",
                 val title: String = "",
                 val description: String = "",
                 val completed: Boolean = false,
                 val priority: TaskPriority = TaskPriority.HIGH)