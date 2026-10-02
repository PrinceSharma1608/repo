package com.prepmate

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class signupFragment : Fragment(R.layout.fragment_signup) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Get views
        val name = view.findViewById<EditText>(R.id.name)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)

        val signupButton =
            view.findViewById<Button>(R.id.signupButton)

        val loginButton =
            view.findViewById<TextView>(R.id.loginButton)
        loginButton.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainerView2,
                    loginFragment()
                )
                .addToBackStack(null)
                .commit()
        }
    }
}