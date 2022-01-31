package com.ukejee.prototechnicaltask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ukejee.prototechnicaltask.R
import com.ukejee.prototechnicaltask.data.api.Resource
import com.ukejee.prototechnicaltask.data.api.Status
import com.ukejee.prototechnicaltask.databinding.FragmentVideoListBinding
import com.ukejee.prototechnicaltask.ui.home.adapter.VideoListRvAdapter
import com.ukejee.prototechnicaltask.ui.home.model.UIVideo
import com.ukejee.prototechnicaltask.ui.home.model.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoListFragment : Fragment() {

    private var _binding: FragmentVideoListBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: VideoViewModel

    private lateinit var adapter: VideoListRvAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUi()
        observeData()
    }

    private fun setupUi() {
        adapter = VideoListRvAdapter()
        adapter.activity = requireActivity()
        binding.videoList.adapter = adapter
        binding.videoList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.swipeLayout.setOnRefreshListener { viewModel.getAllVideos() }
        adapter.listener = { item ->
            viewModel.getSelectedVideo().value = item
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, VideoDetailsFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity())[VideoViewModel::class.java]
        if (viewModel.getState().value?.status != Status.SUCCESS) {
            viewModel.getAllVideos()
        }
    }

    private fun renderUi(state: Resource<List<UIVideo>>) {
        when (state.status) {
            Status.LOADING -> {
                binding.swipeLayout.isRefreshing = true
                binding.errorMessage.visibility = View.GONE
            }
            Status.SUCCESS -> {
                binding.swipeLayout.isRefreshing = false
                binding.errorMessage.visibility = View.GONE
                state.data?.let { videoList ->
                    adapter.replaceList(videoList)
                }
            }
            Status.ERROR -> {
                binding.swipeLayout.isRefreshing = false
                binding.errorMessage.visibility = View.VISIBLE
                binding.errorMessage.text = state.message
            }
        }
    }

    private fun observeData() {
        viewModel.getState().observe(viewLifecycleOwner) { state ->
            renderUi(state)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
