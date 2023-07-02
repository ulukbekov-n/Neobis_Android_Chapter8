import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreatePasswordViewModel : ViewModel() {

    private val inputPasswords = MutableLiveData<String>()
    private val inputCPasswords = MutableLiveData<String>()
    private val _isButtonEnabled = MutableLiveData<Boolean>()

    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    init {
        inputPasswords.value = ""
        inputCPasswords.value = ""
        validatePasswords()
    }

    fun onPasswordTextChanged(text: CharSequence?) {
        inputPasswords.value = text?.toString()?.trim()
        validatePasswords()
    }

    fun onConfirmPasswordTextChanged(text: CharSequence?) {
        inputCPasswords.value = text?.toString()?.trim()
        validatePasswords()
    }

    private fun validatePasswords() {
        val password = inputPasswords.value
        val confirmPassword = inputCPasswords.value

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
