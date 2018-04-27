package ada.osc.passwordstealer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {

    //static dio svake klase
    companion object {
        private val TAG = PasswordActivity::class.java.simpleName
    }

    /**
     * Obzirom da koristimo KotlinAndroidExtensions
     * svi view-ovi koji imaju ID u XML-u su automatski bindani za activity/fragment
     * mozemo ih koristiti na nacin da ih pozovemo preko ID-a (npr passwordDisplay).
     *
     * Samim time je bolje view ID-ove nazvati camelCased i po njihovoj funkciji
     * jer su to ipak automatski i nase varijable
     * **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        //ekstenzija za onClickListener
        savePassword.onClick {
            val text = passwordEntry.text.toString()

            passwordDisplay.text = text
            passwordEntry.text = null

            toastMessage(text)
            logMessage(text)
        }
    }

    private fun logMessage(message: String) {
        Log.d(TAG, message)
        Log.w(TAG, message)
        Log.e(TAG, message)
    }

    private fun toastMessage(message: String) {
        //klasican toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        //toast preko ekstenzija
        toast(message)
    }
}
