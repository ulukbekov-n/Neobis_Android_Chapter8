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
import com.example.mobimarket.data.LoginRequest
import com.example.mobimarket.data.LoginResponse
import com.example.mobimarket.databinding.LoginFragmentBinding
import com.example.mobimarket.utils.ApiClient
import com.example.mobimarket.utils.ApiService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var editTextUserName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var button: Button
    private lateinit var textWatcher: TextWatcher
    private val apiService = ApiClient.createService(ApiService::class.java)
    private fun loginUser(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)
        val loginCall = apiService.login(loginRequest)
        loginCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val token = loginResponse?.token
                    findNavController().navigate(R.id.action_loginFragment_to_mainBottomNavigationFragment)
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        editTextUserName = binding.loginUserName
        editTextPassword = binding.loginPassword
        button = binding.loginButton
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val userName = editTextUserName.text.toString().trim()
                val password = editTextPassword.text.toString().trim()
                val enableButton = userName.isNotEmpty() && password.isNotEmpty()
                button.isEnabled = enableButton
                if (enableButton) {
                    button.setBackgroundResource(R.drawable.enabled_back)
                } else {
                    button.setBackgroundResource(R.drawable.back)
                }
            }
        }
        binding.loginButton.setOnClickListener {
            val userName = editTextUserName.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(userName, password)
            }
        }
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        editTextUserName.addTextChangedListener(textWatcher)
        editTextPassword.addTextChangedListener(textWatcher)
        return binding.root
    }


}

