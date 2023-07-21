package com.example.mobimarket.fragments.signedIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.view_model.LoginViewModel
import com.example.namespace.R
import com.example.namespace.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.loginButton.setOnClickListener {
            val email = binding.loginUserName.text.toString()
            val password = binding.loginPassword.text.toString()
            viewModel.login(email, password)
        }

        binding.loginUserName.addTextChangedListener {
            val email = binding.loginUserName.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()
            binding.loginButton.isEnabled = email.isNotEmpty() && password.isNotEmpty()
            if (binding.loginButton.isEnabled) {
                binding.loginButton.setBackgroundResource(R.drawable.enabled_back)
            } else {
                binding.loginButton.setBackgroundResource(R.drawable.back)
            }
        }

        binding.loginPassword.addTextChangedListener {
            val email = binding.loginUserName.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()
            binding.loginButton.isEnabled = email.isNotEmpty() && password.isNotEmpty()
            if (binding.loginButton.isEnabled) {
                binding.loginButton.setBackgroundResource(R.drawable.enabled_back)
            } else {
                binding.loginButton.setBackgroundResource(R.drawable.back)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                findNavController().navigate(R.id.action_loginFragment_to_mainBottomNavigationFragment)
            } else {

                Toast.makeText(
                    requireContext(),
                    "Login failed. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
