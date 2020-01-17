package com.example.jockeyapp.features

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.BaseUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

open class BaseViewModel(
    private val context: Context
) : ViewModel() {

    var loadingObservable = MutableLiveData<Boolean>()
    var throwObservable = MutableLiveData<Throwable?>()
    var disposables = ArrayList<Disposable>()

    fun <T> execute(useCase: BaseUseCase<T>, useCaseSubscriber: BaseObserver<T>) {
        val disposable = Observable.create<T> { emitter ->
            try {
                emitter.onNext(useCase.execute())
                emitter.onComplete()
            } catch (e: Exception) {
                useCaseSubscriber.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith<BaseObserver<T>>(useCaseSubscriber)
        disposables.add(disposable)
    }


    abstract class BaseObserver<T> : DisposableObserver<T>(), Observer<T> {

        var loadingObservable = MutableLiveData<Boolean>()
        var throwObservable = MutableLiveData<Throwable?>()


        override fun onStart() {
            super.onStart()
            loadingObservable.postValue(true)
        }

        override fun onNext(t: T) {
            loadingObservable.postValue(false)
        }

        override fun onComplete() {
            loadingObservable.postValue(false)
        }

        override fun onError(e: Throwable) {
            throwObservable.postValue(e)
            loadingObservable.postValue(false)
        }

    }

}