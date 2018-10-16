package com.cmdevs.projectunknown.ui.more

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdevs.projectunknown.data.Profile
import com.cmdevs.projectunknown.databinding.FragmentProfiledetailBinding
import com.cmdevs.projectunknown.viewmodels.ProfileViewModel

class ProfileDetailFragment : Fragment() {

    lateinit var viewModel: ProfileViewModel

    companion object {
        fun newInstance() = ProfileDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as ProfileDetailActivity).obtainViewModel()
        val binding = FragmentProfiledetailBinding.inflate(inflater, container, false).apply {
            viewmodel = this@ProfileDetailFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("cylee", "get bundle - ${arguments?.getSerializable("profile")}")

        val profile = arguments?.getSerializable("profile") as Profile
        viewModel.getLiveProfile().value = profile
        subscribeUi()
    }

    fun subscribeUi() {
        viewModel.run {
            getLiveProfile().observe(this@ProfileDetailFragment, Observer {
                it?.let {
                    name.value = it.name
                    photoUrl.value = it.photoUrl
                }
            })
        }
    }

}
