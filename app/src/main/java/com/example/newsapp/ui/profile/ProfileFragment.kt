package com.example.newsapp.ui.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener {
            getContent.launch("image/*")
        }
        textWatcher()
    }

   /* override fun onPause() { перезаписать две строчки
        super.onPause()

    }*/

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Glide.with(binding.imageView).load(uri).centerCrop().into(binding.imageView)
        }

    private fun textWatcher() {
        val pref = requireContext().getSharedPreferences("name", Context.MODE_PRIVATE)
        //val name = binding.editText.text
        val listener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                pref.edit().putString("name", p0.toString()).apply()
                Log.e("Tag", "textWatcher:${pref.getString("name", "defaultName")}")
                binding.textView.text = pref.getString("name", "defaultName")
            }


        }
        binding.editText.addTextChangedListener(listener)


    }
}






