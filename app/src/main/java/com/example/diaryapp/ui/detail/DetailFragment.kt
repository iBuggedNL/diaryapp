package com.example.diaryapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.diaryapp.DiaryDetailVM
import com.example.diaryapp.DiaryDetailVMFactory
import com.example.diaryapp.MyApplication
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentDetailBinding
import com.example.diaryapp.domain.Diary

private const val ARG_DIARY_ID = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private val diaryRepository by lazy { (requireActivity().application as MyApplication).diaryRepository }

    private val DiaryDetailVM: DiaryDetailVM by viewModels {
        DiaryDetailVMFactory(diaryRepository, diaryId!!)
    }

    private var diaryId: Int? = null
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            diaryId = it.getInt(ARG_DIARY_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val dataObserver = Observer<Diary> {
            binding.diary = DiaryDetailVM.diary.value
        }
        DiaryDetailVM.diary.observe(requireActivity(), dataObserver)

        binding.deleteButton.setOnClickListener {
            diaryRepository.remove(binding.diary!!)
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.diary = DiaryDetailVM.diary.value

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DetailFragment.
         */
        @JvmStatic
        fun newInstance(postId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DIARY_ID, postId)
                }
            }
    }
}