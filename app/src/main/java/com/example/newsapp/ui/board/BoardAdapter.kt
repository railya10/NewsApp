package com.example.newsapp.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.PagerBoardBinding
import com.example.newsapp.models.News

class BoardAdapter(private val onCLickStart: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private val titles = arrayListOf("Salam", "Privet", "Hello")


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
        holder.bind(position)
    }


    override fun getItemCount() = titles.size


    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.textTitle.text = titles[position]
            if (position == titles.size - 1) {
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


