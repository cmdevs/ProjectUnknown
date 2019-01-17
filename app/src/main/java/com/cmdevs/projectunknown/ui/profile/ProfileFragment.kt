package com.cmdevs.projectunknown.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cmdevs.projectunknown.databinding.FragmentProfileBinding
import com.cmdevs.projectunknown.util.viewModelProvder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    val profileViewmodelFactory by instance<ProfileViewModelProviderFactory>()
    lateinit var profileViewModel: ProfileViewModel

    companion object {
        private const val USER_AUTH_INFO_KEY = "UserAuthInfoKey"
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel = viewModelProvder(profileViewmodelFactory)
        val binding = FragmentProfileBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            setLifecycleOwner(this@ProfileFragment)
            viewmodel = profileViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("cylee","getBundle : ${arguments?.get(USER_AUTH_INFO_KEY)}")
    }
}