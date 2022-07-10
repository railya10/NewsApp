package com.example.newsapp.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.PagerBoardBinding
import com.example.newsapp.models.PagerModel

class BoardAdapter(private val onCLickStart: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private val titlesSwipe = arrayListOf(
        PagerModel(
            "Page 1",
            R.raw.lottie_one,
            "Swipe left"
        ),
        PagerModel(
            "Page 2",
            R.raw.lottie_two,
            "Swipe left again"
        ),
        PagerModel(
            "Page 3",
            R.raw.lottie_three,
            "Welcome to my NewsApp"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagerBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(titlesSwipe[position])

    }

    override fun getItemCount() = titlesSwipe.size


    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(titles: PagerModel) {

            binding.textTitle.text = titles.title
            binding.textDescription.text = titles.description
            binding.animationView.setAnimation(titles.image)

            if (adapterPosition != titlesSwipe.size - 1) {
                binding.btnStart.visibility = View.INVISIBLE
            }
            binding.btnStart.setOnClickListener {
                onCLickStart()
            }
        }
    }
}


