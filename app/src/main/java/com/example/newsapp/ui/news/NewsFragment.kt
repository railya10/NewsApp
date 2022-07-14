package com.example.newsapp.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newsapp.App
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.models.News
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private var news: News? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        news = arguments?.getSerializable("news") as News?

        news?.let {
            binding.editText.setText(it.title)
        }

        binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val text = binding.editText.text.toString().trim()

        if (news == null) {
            news = News(0, text, System.currentTimeMillis())
            App.database.newsDao().insert(news!!)
            addToFirestore (news!!)

        } else {
            news?.title = text
        }
        val bundle = Bundle()
        bundle.putSerializable("news", news)
        // findNavController().navigate(R.id.navigation_home, bundle)
        parentFragmentManager.setFragmentResult("rk_news", bundle)

    }

    private fun addToFirestore(news: News) {
        Firebase.firestore.collection("news").add(news).addOnSuccessListener {
            documentReference ->
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
}
