package com.example.newsapp.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBoardBinding
import com.example.newsapp.databinding.FragmentHomeBinding


class BoardFragment : Fragment() {
    private lateinit var binding: FragmentBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter {
            findNavController().navigateUp()

        }
        binding.viewPager.adapter = adapter
        val dotsIndicator = binding.indicator
        val viewPager = binding.viewPager
        dotsIndicator.setViewPager2(viewPager)


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == 2) {
                    binding.btnSkip.visibility = View.INVISIBLE
                } else {
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }
        })



        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })

        binding.btnSkip.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
