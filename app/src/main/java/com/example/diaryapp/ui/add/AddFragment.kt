package com.example.diaryapp.ui.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.diaryapp.MyApplication
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentAddBinding
import com.example.diaryapp.domain.Diary
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val diaryRepository by lazy { (requireActivity().application as MyApplication).diaryRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        binding.submitButton.setOnClickListener {
            try {
                // Get current date
                val date = SimpleDateFormat("dd-mm-YYYY").format(Date());

                val newPost = Diary(
                    title = binding.addPostTitleInput.text.toString(),
                    content = binding.addPostContentInput.text.toString(),
                    date = date,
                    coordLat = "",
                    coordLong = "",
                    weather = ""
                )
                diaryRepository.add(newPost)
                requireActivity().supportFragmentManager.popBackStack()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AddFragment.
         */
        @JvmStatic
        fun newInstance() = AddFragment()
    }
}