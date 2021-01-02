package com.example.daggermvvmsample.separator

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.daggermvvmsample.R
import com.example.daggermvvmsample.model.Movie

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var title: TextView? = null
    private var imageView: ImageView? = null

    init {
        title = view.findViewById(R.id.tv_movie_title)
        imageView = view.findViewById(R.id.iv_movie_poster)
    }

    fun bind(movie: Movie) {
        Glide.with(itemView).load("${movie.baseUrl}${movie.posterPath}")
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_error)
            .into(imageView!!)

        title?.text = movie.originalTitle
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MovieViewHolder(view)
        }
    }
}