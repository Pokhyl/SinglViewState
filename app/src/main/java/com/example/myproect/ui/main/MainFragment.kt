package com.example.myproect.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.myproect.R
import com.example.myproect.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
      viewModel.viewState1.observe(viewLifecycleOwner, Observer {
          binding.progressBar.visibility = if(it.isLoading)View.VISIBLE else View.GONE
          println("$it !!!!!!!!!!!")
      })
        viewModel.viewState.observe(viewLifecycleOwner, {
            println(it)
        })
        viewModel.loadMore()
        viewModel.startUpload()
    }
    private fun render(viewState: UploadViewState) {
        when (viewState) {
            Initial -> {
                println("Initial")
//                uploadProgressText.isVisible = false
//                progressBar.isVisible = false
//                uploadDoneIcon.isVisible = false
//                uploadStatusText.isVisible = false
//                retryUploadButton.isVisible = false
            }
            is UploadInProgress -> {
                println("UploadInProgress ${viewState.percentage}")
//                uploadProgressText.isVisible = true
//                progressBar.isVisible = true
//                uploadDoneIcon.isVisible = false
//                uploadStatusText.isVisible = false
//                retryUploadButton.isVisible = false
//                progressBar.setProgressWithAnimation(
//                    viewState.percentage.toFloat(),
//                    ANIMATION_DURATION
//                )
//                uploadProgressText.text = "${viewState.percentage}%"
            }
           UploadFailed -> {
               println("UploadFailed ")
//                uploadProgressText.isVisible = false
//                progressBar.isVisible = false
//                uploadDoneIcon.isVisible = false
//                uploadStatusText.isVisible = true
//                uploadStatusText.text = "Sorry, something went wrong."
//                retryUploadButton.isVisible = true
            }
            UploadSuccess -> {
                println(" UploadSuccess")
//                uploadProgressText.isVisible = false
//                progressBar.isVisible = false
//                uploadDoneIcon.isVisible = true
//                uploadStatusText.isVisible = true
//                uploadStatusText.text = "Upload complete!"
//                retryUploadButton.isVisible = false
            }
        }
    }

}