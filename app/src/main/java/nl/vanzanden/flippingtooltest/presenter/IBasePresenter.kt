package nl.vanzanden.flippingtooltest.presenter

interface IBasePresenter<in V : IBaseView> {
    fun detachView()
    fun attachView(view: V)
}