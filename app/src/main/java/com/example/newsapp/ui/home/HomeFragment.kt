package com.example.newsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.models.News

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private var changeable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter {
            val news = adapter.getItem(it)
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putSerializable("news", news)
            findNavController().navigate(R.id.navigation_news, bundle)
            changeable = true
        }
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
        Log.e("Home", "OnViewCreated")
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
                //}
                Log.e("Home", "text {$news.title} ${news.createdAt}")

            }
            binding.recyclerView.adapter = adapter
        }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            //binding = null
        }
    }
