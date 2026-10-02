package com.prepmate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val signinButton =
            view.findViewById<Button>(R.id.signinButton)

        signinButton.setOnClickListener {

            val intent = Intent(
                requireContext(),
                TestSelectionActivity::class.java
            )

            startActivity(intent)
        }
    }
}