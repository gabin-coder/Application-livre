package com.artjure.pageflip

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var pageIndicator: TextView
    private var pageCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        pageIndicator = findViewById(R.id.pageIndicator)

        val fileNames = assets.list("pages")
            ?.filter { it.endsWith(".png") }
            ?.sorted()
            ?: emptyList()
        pageCount = fileNames.size

        viewPager.adapter = PageAdapter(this, fileNames)
        viewPager.setPageTransformer(FlipPageTransformer())
        viewPager.offscreenPageLimit = 2

        updateIndicator(0)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicator(position)
            }
        })

        findViewById<android.view.View>(R.id.tapNext).setOnClickListener {
            val next = viewPager.currentItem + 1
            if (next < pageCount) {
                viewPager.setCurrentItem(next, true)
            }
        }

        findViewById<android.view.View>(R.id.tapPrev).setOnClickListener {
            val prev = viewPager.currentItem - 1
            if (prev >= 0) {
                viewPager.setCurrentItem(prev, true)
            }
        }
    }

    private fun updateIndicator(position: Int) {
        pageIndicator.text = getString(
            R.string.page_indicator_format,
            position + 1,
            pageCount
        )
    }
}
