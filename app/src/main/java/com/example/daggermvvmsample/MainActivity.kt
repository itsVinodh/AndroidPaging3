package com.example.daggermvvmsample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.daggermvvmsample.adapter.MovieAdapter
import com.example.daggermvvmsample.adapter.MovieLoadStateAdapter
import com.example.daggermvvmsample.databinding.ActivityMainBinding
import com.example.daggermvvmsample.ui.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //added Comment

    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //adding comment2
        //test2
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = MovieAdapter()
        binding.apply {
            rclrViewMovie.setHasFixedSize(true)
            rclrViewMovie.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter { adapter.retry() },
                footer = MovieLoadStateAdapter { adapter.retry() }
            )
        }

        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}