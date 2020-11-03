package nl.vanzanden.flippingtooltest.presenter

open class BasePresenterImpl<V : IBaseView> : IBasePresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}