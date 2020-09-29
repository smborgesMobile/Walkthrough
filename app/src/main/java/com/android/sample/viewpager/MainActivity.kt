package com.android.sample.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.android.sample.viewpager.adapter.IntroSliderAdapter
import com.android.sample.viewpager.model.IntroSliderModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSliderModel(
                "Hogwarts",
                "Whether you come back by page or by the big screen, Hogwarts will always be there to welcome you home.",
                R.drawable.onboarding
            ),
            IntroSliderModel(
                "Harry Potter",
                "\"It does not do well to dwell on dreams and forget to live.\" ― Albus Dumbledore",
                R.drawable.onboarding_two
            ),
            IntroSliderModel(
                "Ron Weasley",
                "\"You're a little scary sometimes, you know that? Brilliant ... but scary.\"",
                R.drawable.onboarding_three
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = introSliderAdapter

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                introSliderAdapter.updateAccessibility(position)
                super.onPageSelected(position)
            }
        }

        )
        TabLayoutMediator(tab_layout, view_pager)
        { _, _ -> }.attach()
    }
}