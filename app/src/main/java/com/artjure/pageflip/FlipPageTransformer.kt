package com.artjure.pageflip

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class FlipPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val width = page.width.toFloat()
        if (width == 0f) return

        page.cameraDistance = 20000f * page.resources.displayMetrics.density
        page.pivotY = page.height / 2f

        when {
            position < -1f -> {
                page.alpha = 0f
            }
            position <= 0f -> {
                page.alpha = 1f
                page.pivotX = width
                page.rotationY = 90f * abs(position)
                page.translationX = 0f
                darken(page, 1f + position)
            }
            position <= 1f -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = -90f * (1f - position)
                page.translationX = 0f
                darken(page, 1f - position)
            }
            else -> {
                page.alpha = 0f
            }
        }
    }

    private fun darken(page: View, depth: Float) {
        val shade = (0.35f + 0.65f * depth.coerceIn(0f, 1f))
        page.alpha = shade
    }
}
