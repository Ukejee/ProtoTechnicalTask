package com.ukejee.prototechnicaltask.ui.home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukejee.prototechnicaltask.data.api.Resource
import com.ukejee.prototechnicaltask.data.api.Status
import com.ukejee.prototechnicaltask.data.api.videos.VideoRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val videoRepository: VideoRepositoryContract) : ViewModel(){

    private val state: MutableLiveData<Resource<List<UIVideo>>> = MutableLiveData()
    private val selectedVideo: MutableLiveData<UIVideo> = MutableLiveData()

    fun getState() = state

    fun getSelectedVideo() = selectedVideo

    fun getAllVideos() {
        state.value = Resource.loading(null)
        viewModelScope.launch {
            val response = videoRepository.getAllVideos()

            when (response.status) {
                Status.SUCCESS -> {
                    if (response.data != null) {
                        state.value = Resource.success(response.data.map { video ->
                            UIVideo.fromApiResponse((video))
                        })
                    } else {
                        state.value = Resource.error(null, "Something went wrong")
                    }
                }
                Status.ERROR -> {
                    state.value = Resource.error(null, response.message ?: "Something went wrong")
                }
            }
        }

    }

}
