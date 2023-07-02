package com.example.mobimarket.fragments

import CreatePasswordViewModel
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.data.RegisterRequest
import com.example.mobimarket.databinding.CreatePasswordFragmentBinding
import com.example.mobimarket.utils.RetrofitInstance
import com.example.mobimarket.view_model.UserViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePasswordFragment : Fragment() {

    private lateinit var binding: CreatePasswordFragmentBinding
    val viewModel: CreatePasswordViewModel by viewModels()
    val viewModelHolder: UserViewModel by activityViewModels()
    private var isPasswordVisible = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreatePasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.passwordBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.registerButton.setOnClickListener {
            val password = binding.createPassword.text.toString()
            val cPassword = binding.confirmPassword.text.toString()

            if (password == cPassword) {
                viewModelHolder.password = password
                toRegister()
            } else {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val inputPassword = binding.createPassword
        val inputCPassword = binding.confirmPassword

        inputPassword.addTextChangedListener(passwordTextWatcher)
        inputCPassword.addTextChangedListener(passwordTextWatcher)

        val passwordVisibilityButton = binding.showPasswordButton
        passwordVisibilityButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            val passwordTransformation = if (isPasswordVisible) {
                null
            } else {
                PasswordTransformationMethod.getInstance()
            }

            val inputPassword = binding.createPassword
            val inputCPassword = binding.confirmPassword
            inputPassword.transformationMethod = passwordTransformation
            inputCPassword.transformationMethod = passwordTransformation
        }

        val userName = arguments?.getString("userName")
        val email = arguments?.getString("email")
        viewModelHolder.username = userName
        viewModelHolder.email = email
    }


    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {

            val inputPassword = binding.createPassword
            val inputCPassword = binding.confirmPassword
            val password = inputPassword.text.toString()
            val cPassword = inputCPassword.text.toString()

            if (password == cPassword) {
                binding.createPassword.addTextChangedListener { text ->
                    viewModel.onPasswordTextChanged(text)
                }
                binding.confirmPassword.addTextChangedListener { text ->
                    viewModel.onConfirmPasswordTextChanged(text)
                }
                viewModel.isButtonEnabled.observe(viewLifecycleOwner) { isEnabled ->
                    binding.registerButton.isEnabled = isEnabled
                    if (isEnabled) {
                        binding.registerButton.setBackgroundResource(R.drawable.enabled_back)
                    } else {
                        binding.registerButton.setBackgroundResource(R.drawable.enabled_back)
                    }

                }
                inputPassword.setTextColor(Color.BLACK)
                inputCPassword.setTextColor(Color.BLACK)

            } else {
                inputPassword.setTextColor(Color.RED)
                inputCPassword.setTextColor(Color.RED)
            }
        }
    }


    private fun toRegister() {
        val api = RetrofitInstance.apiConsumer
        val requestBody = RegisterRequest(
            username = viewModelHolder.username ?: "",
            email = viewModelHolder.email ?: "",
            password = viewModelHolder.password ?: "",
            password_repeat = viewModelHolder.password ?: ""
        )

        val call = api.register(requestBody)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(
                call: Call<Unit>,
                response: Response<Unit>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_createPasswordFragment_to_mainBottomNavigationFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
            }
        })
    }


}
