package ada.osc.passwordstealer

import android.content.Context
import android.view.View
import android.widget.Toast


//ekstenzija na svaki View-tip koja primi lambdu i postavi onClickListener
inline fun View.onClick(crossinline onClickHandler: () -> Unit) {
    setOnClickListener { onClickHandler() }
}

fun Context?.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    if (this != null) {
        Toast.makeText(this, message, duration).show()
    }
}