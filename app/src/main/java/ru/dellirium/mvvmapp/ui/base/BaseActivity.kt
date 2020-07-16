package ru.dellirium.mvvmapp.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import ru.dellirium.mvvmapp.R
import ru.dellirium.mvvmapp.data.errors.NoAuthException

abstract class BaseActivity<T, S: BaseViewState<T>> : AppCompatActivity() {

    companion object {
        const val RC_SIGN_IN = 1337
    }

    abstract val viewModel: BaseViewModel<T, S>
    abstract val binding: ViewDataBinding?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding?.lifecycleOwner = this

        viewModel.getViewState().observe(this, Observer {state ->
            state ?: return@Observer
            state.error?.let {e ->
                renderError(e)
                return@Observer
            }
            renderData(state.data)
        })
    }

    abstract fun renderData(data: T)

    protected fun renderError(error: Throwable?) {
        when(error) {
            is NoAuthException -> startLogin()
            else -> error?.message?.let {
                showError(it)
            }
        }
        error?.message?.let { message ->
            showError(message)
        }
    }

    private fun startLogin() {
        val providers = listOf(
                AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.android_robot)
                .setTheme(R.style.LoginTheme)
                .setAvailableProviders(providers)
                .build()

        startActivityForResult(intent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    protected fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}