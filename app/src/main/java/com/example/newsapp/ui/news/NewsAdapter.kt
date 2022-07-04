package com.example.newsapp.ui.news

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
    private val onClick: (position: Int) -> Unit,
    private val onLongClick: (news: News) -> Unit
) :
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
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(Color.GRAY)
        }
        holder.itemView.setOnClickListener {
            onClick(position)
        }
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun addItem(news: News) {
        list.add(0, news)
        notifyItemInserted(0)
        //notifyItemInserted(list.size - 1)
        //notifyItemInserted(list.indexOf(news))
    }

    fun addItems(list: List<News>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(pos: Int): News {
        return list[pos]
    }

    fun getList() {
        notifyDataSetChanged()
    }

    fun replaceItem(news: News, position: Int) {
        list.set(position, news)
        notifyItemChanged(position)
    }


    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.textTitle.text = news.title
            binding.textDate.text = getDate(news.createdAt, "dd MMM yyyy")
            binding.textTime.text = getDate(news.createdAt, "HH:mm,")

            itemView.setOnLongClickListener {
                onLongClick(news)
                return@setOnLongClickListener true
            }
        }

        fun getDate(milliSeconds: Long, dateFormat: String): String {
            val formatter = SimpleDateFormat(dateFormat)
            val calendar = Calendar.getInstance();
            calendar.timeInMillis = milliSeconds;
            return formatter.format(calendar.time);
        }

    }
}