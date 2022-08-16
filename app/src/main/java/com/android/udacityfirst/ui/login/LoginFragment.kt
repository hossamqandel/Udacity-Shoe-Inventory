package com.android.udacityfirst.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.udacityfirst.R
import com.android.udacityfirst.databinding.FragmentLoginBinding
import com.android.udacityfirst.util.MySharedPref
import com.android.udacityfirst.util.OnNavigate
import com.android.udacityfirst.util.setNavigateAction


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val view = binding.root

        return view
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()

    }

    override fun onStart() {
        super.onStart()
        checkIfCurrentUserLoginStatus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    private fun onClicks() {
        binding.apply {
            btnLogin.setOnClickListener { repeatedAuthOperation() }
            btnSignup.setOnClickListener { repeatedAuthOperation() }
        }
    }

    private fun repeatedAuthOperation() {
        binding.apply {
            val email = etEmail.text.toString().trim().lowercase()
            val password = etPassword.text.toString().trim()
            onValidate(email, password)
        }
    }

    private fun onValidate(email: String, password: String) {
        binding.apply {
            if (email.isBlank()) {
                emailTextField.error = getString(R.string.blank)
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailTextField.error = getString(R.string.email_pattern)
                return
            }

            if (password.length < 8) {
                passwordTextField.error = getString(R.string.password_length)
                return
            }

            if (password.isBlank()) {
                passwordTextField.error = getString(R.string.blank)
                return
            } else {
                emailTextField.error = null
                passwordTextField.error = null
                saveUserDataIntoSharedPref(email, password)
                navigateUser(email, password)
            }
        }
    }

    private fun navigateUser(email: String, password: String) {
        binding.apply {
            progress.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                progress.visibility = View.GONE
                val action =
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(email, password)
                findNavController().navigate(action)
            }, 2000L)

        }
    }

    private fun saveUserDataIntoSharedPref(email: String, password: String) {
        MySharedPref.shEmail = email
        MySharedPref.shPassword = password
    }

    private fun checkIfCurrentUserLoginStatus() {
        if (!MySharedPref.shEmail.toString().isBlank() && !MySharedPref.shPassword.toString()
                .isBlank()
        ) {
            setNavigateAction(OnNavigate.LOGIN_TO_SHOES)
        } else {

        }
    }
}