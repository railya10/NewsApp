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

    private val titles = arrayListOf<PagerModel>(
        PagerModel(
            "Page 1",
            R.drawable.news1,
            "This is the first page of my ViewPager"
        ),
        PagerModel(
            "Page 2",
            R.drawable.news2,
            "This is the second page of my ViewPager"
        ),
        PagerModel(
            "Page 3",
            R.drawable.news3,
            "This is the third page of my ViewPager"
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
        holder.bind(titles[position])

    }


    override fun getItemCount() = titles.size


    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(titles : PagerModel) {

            binding.textTitle.text = titles.title
            binding.textDescription.text = titles.description
            Glide.with(binding.imageView).load(titles.image).into(binding.imageView)
            //binding.textTitle.text = titles position
            if (position == adapterPosition - 1) {
                binding.btnStart.visibility = View.VISIBLE
            } else {
                binding.btnStart.visibility = View.INVISIBLE
            }
            binding.btnStart.setOnClickListener {
                onCLickStart()

            }
        }
    }
}


