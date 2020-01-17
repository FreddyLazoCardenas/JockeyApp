package com.example.jockeyapp.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity() {
    abstract fun getViewModel(): BaseViewModel?
    abstract fun getLayout(): Int
    abstract fun setUpView()

    var mDisposable : CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        observeCommons()
        this.setUpView()
    }

    private fun observeCommons() {
        if(getViewModel() != null){
            getViewModel()?.loadingObservable?.observe(this, Observer { isLoading -> showLoading(isLoading) })
            getViewModel()?.throwObservable?.observe(this, Observer { mThrow -> throwError(mThrow) })
        }
    }

    open fun throwError(mThrow: Throwable?) {}

    open fun showLoading(isLoading: Boolean) { }

    override fun onDestroy() {
        if(getViewModel() != null){
            getViewModel()?.let {
                getViewModel()!!.disposables.forEach {
                    it.dispose()
                }
            }
        }
        if (mDisposable != null) {
            mDisposable!!.clear()
        }
        super.onDestroy()
    }
}