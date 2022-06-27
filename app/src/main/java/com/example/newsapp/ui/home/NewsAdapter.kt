package com.example.newsapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val onClick: (position : Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val list = arrayListOf<News>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount() = list.size
    fun addItem(news: News) {
        list.add(0, news)
        notifyItemInserted(0)
        //notifyItemInserted(list.size - 1)
        //notifyItemInserted(list.indexOf(news))
    }

    fun getItem(pos: Int): News {
        return list[pos]
    }

    fun replaceItem(news: News,position: Int){
        list.set(position,news)
        notifyItemChanged(position)
    }

    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.textTitle.text = news.title
            binding.textDate.text = getDate(news.createdAt, "dd MMM yyyy")
            binding.textTime.text = getDate(news.createdAt, "HH:mm,")

            if (position % 2 ==0){
                binding.root.setBackgroundColor(Color.WHITE)
            }else{
                binding.root.setBackgroundColor(Color.GRAY)
            }
            itemView.setOnClickListener {
                onClick(position)
            }
        }
        fun getDate(milliSeconds: Long, dateFormat: String): String {
            val formater = SimpleDateFormat(dateFormat)

            val calendar = Calendar.getInstance();
            calendar.timeInMillis = milliSeconds;
            return formater.format(calendar.time);
        }

    }
}