package com.cmdevs.projectunknown.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cmdevs.projectunknown.MainActivity
import com.cmdevs.projectunknown.databinding.FragmentProfileBinding
import com.cmdevs.projectunknown.domain.result.EventObserver
import com.cmdevs.projectunknown.util.viewModelProivder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class ProfileFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    val viewModelFactory by instance<ProfileViewModelFactory>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val profileViewModel = viewModelProivder<ProfileViewModel>(viewModelFactory).apply {
            this.uid = arguments?.getString(ARG_UID)
            updateProfile(uid)
        }

        val binding = FragmentProfileBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@ProfileFragment)
            viewModel = profileViewModel
        }

        profileViewModel.profileData.observe(this, Observer {
            Timber.d("profile update success")
        })

        profileViewModel.startObservable.observe(this, EventObserver { startable ->
            if (startable) {
                startActivity(
                        Intent(requireActivity(), MainActivity::class.java).apply {
                            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                )
            }
        })
        return binding.root
    }

    companion object {
        const val ARG_UID = "uid"

        fun newInstance(uid: String): ProfileFragment {
            val bundle = Bundle().apply { putString(ARG_UID, uid) }
            return ProfileFragment().apply { arguments = bundle }
        }
    }
}