package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.data.LoginRequest
import com.example.mobimarket.data.LoginResponse
import com.example.mobimarket.databinding.LoginFragmentBinding
import com.example.mobimarket.view_model.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.loginUserName.addTextChangedListener { text ->
            loginViewModel.onEmailTextChanged(text)
        }
        binding.loginPassword.addTextChangedListener { text ->
            loginViewModel.onPasswordTextChanged(text)
        }
        loginViewModel.isButtonEnabled.observe(viewLifecycleOwner) { isEnabled ->
            val button = binding.loginButton
            button.isEnabled = isEnabled
            button.setBackgroundResource(if (isEnabled) R.drawable.enabled_back else R.drawable.back)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginUserName.text.toString()
            val password = binding.loginPassword.text.toString()
            loginViewModel.login(email, password)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.loginSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                Toast.makeText(requireContext(), "You are logged in", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_mainBottomNavigationFragment)
            } else {
                Toast.makeText(requireContext(), "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        })

        loginViewModel.loginError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Please try again", Toast.LENGTH_SHORT).show()
        })
    }
}