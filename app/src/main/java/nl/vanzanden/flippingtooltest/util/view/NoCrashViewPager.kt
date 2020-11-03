package nl.vanzanden.flippingtooltest.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * This class fixes "pointerIndex out of range" crash.
 * @see <a href="https://stackoverflow.com/questions/16459196/java-lang-illegalargumentexception-pointerindex-out-of-range-exception-dispat/16519902">StackOverflow</a>
 */
open class NoCrashViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent?): Boolean = try {
        super.onTouchEvent(ev)
    } catch (e: IllegalArgumentException) {
        false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = try {
        super.onInterceptTouchEvent(ev)
    } catch (e: IllegalArgumentException) {
        false
    }
}
