package com.example.daggermvvmsample.adapter


import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvmsample.R
import com.example.daggermvvmsample.model.UiModel
import com.example.daggermvvmsample.separator.MovieViewHolder
import com.example.daggermvvmsample.separator.SeparatorViewHolder

class MovieAdapter : PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(UIMODEL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            MovieViewHolder.create(parent)
        } else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.Movie -> 0

            is UiModel.Separator -> 1

            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            when (uiModel) {
                is UiModel.Separator -> (holder as SeparatorViewHolder).bind(uiModel.originalTitle)
                is UiModel.Movie -> (holder as MovieViewHolder).bind(uiModel.movie)

            }
        }
    }

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.Movie && newItem is UiModel.Movie &&
                        oldItem.movie.originalTitle == newItem.movie.originalTitle) ||
                        (oldItem is UiModel.Separator && newItem is UiModel.Separator &&
                                oldItem.originalTitle == newItem.originalTitle)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                oldItem == newItem
        }
    }
}