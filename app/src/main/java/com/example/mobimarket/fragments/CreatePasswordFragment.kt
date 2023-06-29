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

        toRegUserPage()
        creationPassword()
        checkPassword()
        passwordVisibility()
    }

    private fun passwordVisibility() {

        val passwordVisibilityButton = binding.showPasswordButton
        passwordVisibilityButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            updatePasswordVisibility()
        }
    }

    private fun updatePasswordVisibility() {
        val passwordTransformation = if (isPasswordVisible) {
            null
        } else {
            PasswordTransformationMethod.getInstance() // Show the password as dots
        }

        val firstInputPassword = binding.createPassword
        val secondInputPassword = binding.confirmPassword
        firstInputPassword.transformationMethod = passwordTransformation
        secondInputPassword.transformationMethod = passwordTransformation
    }

    private fun checkPassword() {
        val firstInputPassword = binding.createPassword
        val secondInputPassword = binding.confirmPassword

        firstInputPassword.addTextChangedListener(passwordTextWatcher)
        secondInputPassword.addTextChangedListener(passwordTextWatcher)
    }

    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {

            val firstInputPassword = binding.createPassword
            val secondInputPassword = binding.confirmPassword
            val password1 = firstInputPassword.text.toString()
            val password2 = secondInputPassword.text.toString()

            if (password1 == password2) {
                validatePassword()
                firstInputPassword.setTextColor(Color.BLACK)
                secondInputPassword.setTextColor(Color.BLACK)

            } else {
                firstInputPassword.setTextColor(Color.RED)
                secondInputPassword.setTextColor(Color.RED)
            }
        }
    }

    private fun creationPassword() {
        binding.registerButton.setOnClickListener {
            val password1 = binding.createPassword.text.toString()
            val password2 = binding.confirmPassword.text.toString()

            if (password1 == password2) {
                viewModelHolder.password = password1
                registerUser()
            } else {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun registerUser() {
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
                    findNavController().navigate(R.id.action_createPasswordFragment_to_profileMainFragment)
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

    private fun validatePassword() {
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
    }

    private fun toRegUserPage() {
        binding.passwordBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_createPasswordFragment_to_registerFragment)
        }
    }
}
//import CreatePasswordViewModel
//import android.os.Bundle
//import android.text.method.PasswordTransformationMethod
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import com.example.mobimarket.R
//import com.example.mobimarket.data.RegisterRequest
//import com.example.mobimarket.data.RegisterResponse
//import com.example.mobimarket.databinding.CreatePasswordFragmentBinding
//import com.example.mobimarket.utils.ApiServiceHelper.apiService
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CreatePasswordFragment : Fragment() {
//    private lateinit var binding: CreatePasswordFragmentBinding
//    private lateinit var editTextPassword: EditText
//    private lateinit var editTextPasswordRepeat: EditText
//    private lateinit var button: Button
//    private val viewModel: CreatePasswordViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = CreatePasswordFragmentBinding.inflate(inflater, container, false)
//        editTextPassword = binding.createPassword
//        editTextPasswordRepeat = binding.confirmPassword
//        button = binding.registerButton
//
//        viewModel.isButtonEnabled.observe(viewLifecycleOwner) { isEnabled ->
//            button.isEnabled = isEnabled
//            if (isEnabled) {
//                button.setBackgroundResource(R.drawable.enabled_back)
//            } else {
//                button.setBackgroundResource(R.drawable.back)
//            }
//        }
//
//        button.setOnClickListener {
//            val password = editTextPassword.text.toString().trim()
//            val passwordRepeat = editTextPasswordRepeat.text.toString().trim()
//
//            if (password.isEmpty() || passwordRepeat.isEmpty()) {
//                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//            } else if (password != passwordRepeat) {
//                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
//            } else {
//                viewModel.validatePasswords() // Call validatePasswords() to perform password validation
//                val userName = arguments?.getString("userName")
//                val email = arguments?.getString("email")
//
//                val registerRequest = RegisterRequest(userName, email, password, passwordRepeat)
//                val registerCall = apiService.register(registerRequest)
//                registerCall.enqueue(object : Callback<RegisterResponse> {
//                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
//                        if (response.isSuccessful) {
//                            val registerResponse = response.body()
//                            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
//                            findNavController().navigate(R.id.action_createPasswordFragment_to_profileMainFragment)
//                        } else {
//                            val errorMessage = response.errorBody()?.string()
//                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                        Toast.makeText(context, "Registration failed. Please try again later.", Toast.LENGTH_SHORT).show()
//                    }
//                })
//            }
//        }
//        binding.passwordBackButton.setOnClickListener {
//            findNavController().navigate(R.id.action_createPasswordFragment_to_registerFragment)
//        }
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        viewModel.validatePasswords()
//
//        val firstInputPassword = binding.createPassword
//        val secondInputPassword = binding.confirmPassword
//        var isPasswordVisible = false
//        val passwordVisibilityButton = binding.showPasswordButton
//        passwordVisibilityButton.setOnClickListener {
//            isPasswordVisible = !isPasswordVisible
//            val passwordTransformation = if (isPasswordVisible) {
//                null
//            } else {
//                PasswordTransformationMethod.getInstance()
//            }
//            firstInputPassword.transformationMethod = passwordTransformation
//            secondInputPassword.transformationMethod = passwordTransformation
//        }
//    }
//}
//
