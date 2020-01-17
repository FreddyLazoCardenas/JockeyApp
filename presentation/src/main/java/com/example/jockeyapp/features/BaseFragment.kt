package com.example.jockeyapp.features

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment : Fragment() {

    abstract fun getViewModel(): BaseViewModel?
    abstract fun getLayout(): Int
    abstract fun setUpView()
    abstract fun observeViewModel()

    lateinit var mView: View
    protected var activity: BaseActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeCommons()
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(getLayout(), container, false)
        return mView
    }

    private fun observeCommons() {
        if (getViewModel() != null) {
            getViewModel()?.loadingObservable?.observe(
                this,
                Observer { isLoading -> showLoading(isLoading) })
            getViewModel()?.throwObservable?.observe(
                this,
                Observer { mThrow -> throwError(mThrow) })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpView()
    }

    open fun throwError(mThrow: Throwable?) {}

    open fun showLoading(isLoading: Boolean) {}
}