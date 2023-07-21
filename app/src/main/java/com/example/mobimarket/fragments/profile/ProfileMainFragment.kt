package com.example.mobimarket.fragments.profile
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobimarket.utils.UserHolder
import com.example.namespace.R
import com.example.namespace.databinding.ProfileMainFragmentBinding

class ProfileMainFragment : Fragment() {
    private lateinit var binding: ProfileMainFragmentBinding
    private lateinit var exitAlertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileMainFragmentBinding.inflate(inflater, container, false)

        binding.profileName.text = "${UserHolder.name} ${UserHolder.surname}"

        binding.profilePhoto.setImageURI(UserHolder.selectedImageUri)

        binding.likedButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileMainFragment_to_changeProfileFragment)
        }

        binding.changeProfileButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileMainFragment_to_changeProfileFragment)
        }

        binding.logoutButton.setOnClickListener {
            showExitAlertDialog()
        }

        return binding.root
    }

    private fun showExitAlertDialog() {
        val view = layoutInflater.inflate(R.layout.layout_cutom_dialog, null)
        val confirmExitButton = view.findViewById<Button>(R.id.confirmExitButton)
        val cancelExitButton = view.findViewById<TextView>(R.id.cancelExitButton)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)

        exitAlertDialog = builder.create()

        confirmExitButton.setOnClickListener {
            activity?.finish()
        }

        cancelExitButton.setOnClickListener {
            exitAlertDialog.dismiss()
        }

        exitAlertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::exitAlertDialog.isInitialized) {
            exitAlertDialog.dismiss()
        }
    }
}
