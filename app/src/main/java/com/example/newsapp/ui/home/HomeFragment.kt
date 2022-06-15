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

class HomeFragment : Fragment() {

    // private var _binding: FragmentHomeBinding? = null
    // private val binding get() = _binding!!

    private lateinit var binding: FragmentHomeBinding


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
        binding.fab.setOnClickListener {
        findNavController().navigate(R.id.navigation_news)
        }
        parentFragmentManager.setFragmentResultListener("rk_news", viewLifecycleOwner) { requestKey, bundle ->
            val text = bundle.getString("text")
            Log.e("Home", "text $text")
            Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // binding = null
    }
}