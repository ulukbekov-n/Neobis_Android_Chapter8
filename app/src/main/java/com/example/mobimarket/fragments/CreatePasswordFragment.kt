package com.example.mobimarket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.data.RegisterRequest
import com.example.mobimarket.data.RegisterResponse
import com.example.mobimarket.databinding.CreatePasswordFragmentBinding
import com.example.mobimarket.utils.ApiServiceHelper.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePasswordFragment : Fragment() {
    private lateinit var binding: CreatePasswordFragmentBinding
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPasswordRepeat: EditText
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreatePasswordFragmentBinding.inflate(inflater, container, false)
        editTextPassword = binding.createPassword
        editTextPasswordRepeat = binding.confirmPassword
        button = binding.registerButton


        binding.registerButton.setOnClickListener {
            val password = editTextPassword.text.toString().trim()
            val passwordRepeat = editTextPasswordRepeat.text.toString().trim()

            if (password.isEmpty() || passwordRepeat.isEmpty()) {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (password != passwordRepeat) {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                val userName = arguments?.getString("userName")
                val email = arguments?.getString("email")

                val registerRequest = RegisterRequest(userName, email, password, passwordRepeat)
                val registerCall = apiService.register(registerRequest)
                registerCall.enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.isSuccessful) {
                            val registerResponse = response.body()
                        } else {
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(context, "Network error occurred", Toast.LENGTH_SHORT)
                            .show()

                    }
                })
            }
        }

        binding.passwordBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_createPasswordFragment_to_registerFragment)
        }

        return binding.root
    }
}
