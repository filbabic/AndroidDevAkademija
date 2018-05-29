package ada.osc.taskie.ui.details

import ada.osc.taskie.R
import ada.osc.taskie.model.Task
import ada.osc.taskie.model.TaskKotlin
import ada.osc.taskie.ui.tasks.favorite.FavoriteTasksContract
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class TaskDetailsActivity : AppCompatActivity() {

    private var something: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        taskTitle.addTextChangedListener(SimpleTextWatcher { newText ->
            Toast.makeText(this, newText, Toast.LENGTH_LONG)
        })

        val myTask = TaskKotlin("nekiId", "nekiTite", "nekiDescr")

        val newMyTask = myTask.copy()


        val length = something?.length
    }

    class SimpleTextWatcher(private val onTextChangedMethod: (String) -> Unit) : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onTextChanged(string: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChangedMethod(string.toString())
        }

    }
}