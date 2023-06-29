import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreatePasswordViewModel : ViewModel() {

    private val _passwordInput = MutableLiveData<String>()
    private val _confirmPasswordInput = MutableLiveData<String>()
    private val _isButtonEnabled = MutableLiveData<Boolean>()

    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    init {
        _passwordInput.value = ""
        _confirmPasswordInput.value = ""
        validatePasswords()
    }

    fun onPasswordTextChanged(text: CharSequence?) {
        _passwordInput.value = text?.toString()?.trim()
        validatePasswords()
    }

    fun onConfirmPasswordTextChanged(text: CharSequence?) {
        _confirmPasswordInput.value = text?.toString()?.trim()
        validatePasswords()
    }

    fun validatePasswords() {
        val password = _passwordInput.value
        val confirmPassword = _confirmPasswordInput.value

        val isPasswordsValid = isPasswordsMatching(password, confirmPassword)
                && isPasswordLengthValid(password)
                && isPasswordContainsDigits(password)
                && isPasswordContainsLetters(password)

        _isButtonEnabled.value = isPasswordsValid
    }


    private fun isPasswordLengthValid(password: String?): Boolean {
        return (password?.length ?: 0) >= 8
    }

    private fun isPasswordContainsDigits(password: String?): Boolean {
        return password?.contains(Regex(".*\\d.*")) ?: false
    }

    private fun isPasswordContainsLetters(password: String?): Boolean {
        return password?.contains(Regex(".*[a-z].*")) ?: false
    }
    private fun isPasswordsMatching(password: String?, confirmPassword: String?): Boolean {
        return password == confirmPassword
    }
}
