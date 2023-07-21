package com.example.mobimarket.fragments.profile
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.utils.UserHolder
import com.example.namespace.R
import com.example.namespace.databinding.ChangeProfileFragmentBinding

class ChangeProfileFragment : Fragment() {
    private lateinit var binding: ChangeProfileFragmentBinding

    companion object {
        private const val PICK_IMAGE_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChangeProfileFragmentBinding.inflate(inflater, container, false)

        binding.changeName.setText(UserHolder.name)
        binding.changeSurname.setText(UserHolder.surname)
        binding.changeDateBirth.setText(UserHolder.birthday)
        binding.changeUsername.setText(UserHolder.username)
        binding.profileEmail.setText(UserHolder.email)

        UserHolder.selectedImageUri?.let { uri ->
            binding.profilePhoto.setImageURI(uri)
        }

        binding.text2.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }

        binding.addPhoneNumberButton.setOnClickListener {
            findNavController().navigate(R.id.action_changeProfileFragment_to_addingNumberFragment)
        }

        binding.cancelEditButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.acceptEditButton.setOnClickListener {

            UserHolder.name = binding.changeName.text.toString()
            UserHolder.surname = binding.changeSurname.text.toString()
            UserHolder.birthday = binding.changeDateBirth.text.toString()
            UserHolder.username = binding.changeUsername.text.toString()

            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data

            UserHolder.selectedImageUri = selectedImageUri
            binding.profilePhoto.setImageURI(selectedImageUri)
        }
    }
}
