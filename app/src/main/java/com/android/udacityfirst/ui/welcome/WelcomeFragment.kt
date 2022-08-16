package com.android.udacityfirst.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.android.udacityfirst.R
import com.android.udacityfirst.databinding.FragmentWelcomeBinding
import com.android.udacityfirst.util.OnNavigate
import com.android.udacityfirst.util.setNavigateAction

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    private var emailArgs = ""
    private var passArgs = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome ,container, false)
        val view = binding.root
        emailArgs = WelcomeFragmentArgs.fromBundle(requireArguments()).email
        passArgs = WelcomeFragmentArgs.fromBundle(requireArguments()).password
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUserData()
        onClick()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    private fun initUserData(){
        binding.emailArgsData = emailArgs
    }

    private fun onClick(){
        binding.btnNavigate.setOnClickListener {
            setNavigateAction(OnNavigate.WELCOME_TO_INSTRUCTIONS)
        }
    }



}