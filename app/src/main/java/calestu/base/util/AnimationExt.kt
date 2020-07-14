package calestu.base.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.fadeIn(animatorListenerAdapter: AnimatorListenerAdapter? = null, duration: Long? = null) {
    alpha = 0f
    visibility = View.VISIBLE
    animate()
        .alpha(1f)
        .setDuration(duration ?: resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
        .setListener(animatorListenerAdapter)
}

fun View.fadeOut(animatorListenerAdapter: AnimatorListenerAdapter? = null, duration: Long? = null) {
    animate()
        .alpha(0f)
        .setDuration(
            duration ?: resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
        )
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = View.GONE
                animatorListenerAdapter?.let {
                    it.onAnimationEnd(animation)
                }
            }
        })
}