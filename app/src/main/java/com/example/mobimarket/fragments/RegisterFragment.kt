package com.example.mobimarket.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.R
import com.example.mobimarket.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {
    private lateinit var binding:RegisterFragmentBinding
    lateinit var editTextUserName: EditText
    lateinit var editTextEmail: EditText
    lateinit var button: Button
    lateinit var textWatcher: TextWatcher

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater,container,false)
        editTextUserName = binding.loginUserName
        editTextEmail = binding.loginEmail
        button = binding.registerButton
        textWatcher = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val userName = editTextUserName.text.toString().trim()
                val password = editTextEmail.text.toString().trim()
                val enableButton = userName.isNotEmpty() && password.isNotEmpty()
                button.isEnabled = enableButton
                if (enableButton) {
                    button.setBackgroundResource(R.drawable.enabled_back)

                } else {
                    button.setBackgroundResource(R.drawable.back )
                }
            }

        }
        binding.registerButton.setOnClickListener {
            val userName = editTextUserName.text.toString().trim()
            val password = editTextEmail.text.toString().trim()
            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_registerFragment_to_createPasswordFragment)
            }
        }
        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        editTextUserName.addTextChangedListener(textWatcher)
        editTextEmail.addTextChangedListener(textWatcher)
        return binding.root
    }
}
