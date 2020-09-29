package com.android.sample.viewpager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sample.viewpager.R
import com.android.sample.viewpager.model.IntroSliderModel

class IntroSliderAdapter(private val list: List<IntroSliderModel>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>() {
    private var selectedPosition = 0

    inner class IntroSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById<TextView>(R.id.slider_text_view_title)
        private val textDescription =
            itemView.findViewById<TextView>(R.id.slide_text_view_description)
        private val imageView = itemView.findViewById<ImageView>(R.id.slide_image_view)

        fun bind(model: IntroSliderModel, shouldChangeFocus: Boolean) {
            textTitle.text = model.title
            textDescription.text = model.description
            imageView.setImageResource(model.icon)

            if (shouldChangeFocus) {
                textTitle.postDelayed(
                {
                    textTitle.isFocusable = true
                    textTitle.isFocusableInTouchMode = true
                    textTitle.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                }, 200)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        return IntroSliderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slider_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        if (selectedPosition == position) {
            holder.bind(list[position], true)
        } else {
            holder.bind(list[position], false)
        }
    }

    fun updateAccessibility(position: Int) {
        selectedPosition = position
        notifyItemChanged(position)
    }
}