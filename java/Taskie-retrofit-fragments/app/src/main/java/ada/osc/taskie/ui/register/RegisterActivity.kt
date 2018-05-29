package ada.osc.taskie.ui.register

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast

import ada.osc.taskie.App
import ada.osc.taskie.R
import ada.osc.taskie.model.RegistrationToken
import ada.osc.taskie.presentation.RegisterPresenter
import ada.osc.taskie.presentation.RegisterPresenterImpl
import ada.osc.taskie.ui.login.LoginActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class RegisterActivity : AppCompatActivity(), RegisterView {

    @BindView(R.id.user_email)
    internal var mUserEmail: EditText? = null

    @BindView(R.id.username)
    internal var mUsername: EditText? = null

    @BindView(R.id.user_password)
    internal var mUserPwd: EditText? = null

    private var presenter: RegisterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_register)
        presenter = RegisterPresenterImpl(App.getApiInteractor())
        presenter!!.setView(this)

        ButterKnife.bind(this)
    }

    @OnClick(R.id.button_login)
    internal fun onLoginButtonClick() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    @OnClick(R.id.button_register)
    internal fun onRegisterButtonClick() = registerUser()

    private fun registerUser() {
        val user = RegistrationToken(mUsername!!.text.toString(),
                mUserEmail!!.text.toString(),
                mUserPwd!!.text.toString())

        presenter!!.registerUser(user)
    }

    override fun showUserInvalidError() {
        Toast.makeText(this, "User is not valid", Toast.LENGTH_SHORT).show()
    }

    override fun onUserRegistered() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun showNetworkError() {
        Toast.makeText(this, "Network error, please try again", Toast.LENGTH_SHORT).show()
    }
}
