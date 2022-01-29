package com.ukejee.prototechnicaltask.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.ukejee.prototechnicaltask.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUi()
        observeData()
    }

    private fun setupUi() {
        binding.loginButton.setOnClickListener {
            viewModel.authenticateUser(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
    }

    private fun observeData() {
        viewModel.getUserAuthenticated().observe(viewLifecycleOwner) { userAuthenticated ->
            if (userAuthenticated) {
                Snackbar.make(requireContext(), binding.root, "Login Successful", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(requireContext(), binding.root, viewModel.getErrorMessage(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

}