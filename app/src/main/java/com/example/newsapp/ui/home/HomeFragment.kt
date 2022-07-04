package com.example.newsapp.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.App
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.models.News
import com.example.newsapp.ui.news.NewsAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.FieldPosition

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var changeable: Boolean = false
    private val adapter = NewsAdapter(this::onClick, this::onLongClick)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.addItems(App.database.newsDao().sortAll())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.navigation_news)
        }
        parentFragmentManager.setFragmentResultListener(
            "rk_news",
            viewLifecycleOwner
        ) { requestKey, bundle ->
            val news = bundle.getSerializable("news") as News
            val position: Int? = null
            if (changeable) {
                position.let {
                    if (it != null) {
                        adapter.replaceItem(news, it)
                    }
                }
            } else {
                adapter.addItem(news)
                Log.e("Home", "text {$news.title} ${news.createdAt}")
            }

        }
    }

    private fun onClick(position: Int) {
        val news = adapter.getItem(position)
        Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putSerializable("news", news)
        findNavController().navigate(R.id.navigation_news, bundle)
        changeable = true
    }

    private fun onLongClick(news: News) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Notification")
            .setMessage("Do you really want to delete item?")
            .setNegativeButton("Cancel") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Delete") { dialog, which ->
                App.database.newsDao().deleteItem(news)
                adapter.removeItem(news)
                //вставить увдаление из arraylist
                adapter.addItems(App.database.newsDao().sortAll())
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null
    }

}

