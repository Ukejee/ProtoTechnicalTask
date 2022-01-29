package com.ukejee.prototechnicaltask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ukejee.prototechnicaltask.R
import com.ukejee.prototechnicaltask.databinding.FragmentVideoDetailsBinding
import com.ukejee.prototechnicaltask.ui.home.model.UIVideo
import com.ukejee.prototechnicaltask.ui.home.model.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoDetailsFragment : Fragment() {

    private var _binding: FragmentVideoDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUi()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity())[VideoViewModel::class.java]
    }

    private fun setupUi() {
        binding.backBtn.setOnClickListener { requireActivity().onBackPressed() }
        viewModel.getSelectedVideo().value?.let { renderUi(it) }
    }

    private fun renderUi(video: UIVideo) {
        binding.id.text = video.id
    }
}
