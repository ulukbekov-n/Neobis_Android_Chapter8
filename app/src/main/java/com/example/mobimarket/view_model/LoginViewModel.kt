import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _emailInput = MutableLiveData<String>()
    val emailInput: LiveData<String> = _emailInput

    private val _passwordInput = MutableLiveData<String>()
    val passwordInput: LiveData<String> = _passwordInput

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    init {
        _emailInput.value = ""
        _passwordInput.value = ""
        updateButtonEnabledState()
    }

    fun onEmailTextChanged(text: CharSequence?) {
        _emailInput.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }

    fun onPasswordTextChanged(text: CharSequence?) {
        _passwordInput.value = text?.toString()?.trim()
        updateButtonEnabledState()
    }

    private fun updateButtonEnabledState() {
        val email = _emailInput.value
        val password = _passwordInput.value
        _isButtonEnabled.value = !email.isNullOrEmpty() && !password.isNullOrEmpty()
    }
}
