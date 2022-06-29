package com.example.newsapp.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.PagerBoardBinding
import com.example.newsapp.models.PagerModel

class BoardAdapter(private val onCLickStart: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private val titlesSwipe = arrayListOf<PagerModel>(
        PagerModel(
            "Page 1",
            R.drawable.news1,
            "Swipe left"
        ),
        PagerModel(
            "Page 2",
            R.drawable.news2,
            "Swipe left again"
        ),
        PagerModel(
            "Page 3",
            R.drawable.news3,
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
            Glide.with(binding.imageView).load(titles.image).into(binding.imageView)

            if (adapterPosition != titlesSwipe.size -1) {
                binding.btnStart.visibility = View.INVISIBLE
            }
            binding.btnStart.setOnClickListener {
                onCLickStart()

            }
        }
    }
}


