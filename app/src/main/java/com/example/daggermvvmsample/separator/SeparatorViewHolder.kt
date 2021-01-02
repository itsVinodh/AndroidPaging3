package com.example.daggermvvmsample.separator

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvmsample.R

class SeparatorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var description: TextView? = null

    fun bind(separatorText: String) {
        description?.text = separatorText
    }

    init {
        description = view.findViewById(R.id.separator_description)
    }

    companion object {
        fun create(parent: ViewGroup): SeparatorViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.seperator_view_item, parent, false)
            return SeparatorViewHolder(view)
        }
    }
}