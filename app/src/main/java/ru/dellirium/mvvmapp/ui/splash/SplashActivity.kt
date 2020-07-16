package ru.dellirium.mvvmapp.ui.splash

import androidx.lifecycle.ViewModelProvider
import ru.dellirium.mvvmapp.ui.base.BaseActivity
import ru.dellirium.mvvmapp.ui.base.BaseViewModel
import ru.dellirium.mvvmapp.ui.main.MainActivity

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {
    override val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }
    override val binding = null

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }


}