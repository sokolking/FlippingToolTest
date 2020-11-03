package nl.vanzanden.flippingtooltest.presenter

interface IBaseView {
    fun showError(msg: String?) {}
    fun showError(t: Throwable?) {}
}