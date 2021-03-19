package com.evermos.awesome.ui.base

import android.content.Context
import com.androidnetworking.error.ANError

open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    private val TAG = "BasePresenter"
    private var mMvpView: V? = null

    constructor (){}

    override fun onAttach(mvpView: V) {
        mMvpView = mvpView
    }

    override fun onDetach() {
        mMvpView = null
    }

    fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    fun getMvpView(): V {
        return mMvpView!!
    }

    fun getContext(): Context {
        return mMvpView!!.getContext()
    }

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    override fun handleApiError(error: ANError) {

    }

    override fun setUserAsLoggedOut() {

    }

    class MvpViewNotAttachedException :
        RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")
}