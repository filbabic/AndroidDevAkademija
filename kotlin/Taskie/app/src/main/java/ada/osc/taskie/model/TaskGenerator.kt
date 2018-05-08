package ada.osc.taskie.model

import java.util.*

object TaskGenerator {

    private val generator = Random()

    private val titles = arrayOf("Osc", "Potato", "Homework", "Shopping", "Gaming", "Reading", "Android", "Studying", "Question", "Ideas", "Party", "Nothing")
    private val descriptions = arrayOf("Do it.", "Play it", "Drink it", "Answer it", "Shut it down", "Try it.", "Don't do it.", "Ignore it.")

    fun generate(taskCount: Int): List<Task> = (0..taskCount).map { counter ->
        val title = titles[generator.nextInt(titles.size)]
        val description = descriptions[generator.nextInt(descriptions.size)]

        val prioritySelector = generator.nextInt(TaskPriority.values().size)
        val priority = TaskPriority.values()[prioritySelector]

        Task(id = counter, title = title, description = description, priority = priority)
    }
}

