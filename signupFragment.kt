package com.prepmate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val name = view.findViewById<EditText>(R.id.name)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)

        val signupButton =
            view.findViewById<Button>(R.id.signupButton)

        val loginButton =
            view.findViewById<TextView>(R.id.loginButton)

        signupButton.setOnClickListener {

            val nameText = name.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString()

            if (
                nameText.isEmpty() ||
                emailText.isEmpty() ||
                passwordText.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(
                emailText,
                passwordText
            ).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser!!.uid

                    val user = hashMapOf(
                        "name" to nameText,
                        "email" to emailText,
                        "createdAt" to System.currentTimeMillis()
                    )

                    db.collection("users")
                        .document(uid)
                        .set(user)
                        .addOnSuccessListener {

                            Toast.makeText(
                                requireContext(),
                                "Registration successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(
                                requireContext(),
                                TestSelectionActivity::class.java
                            )

                            startActivity(intent)
                        }
                        .addOnFailureListener {

                            Toast.makeText(
                                requireContext(),
                                "Failed to save user data",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                } else {

                    Toast.makeText(
                        requireContext(),
                        task.exception?.message
                            ?: "Registration failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        loginButton.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainerView2,
                    LoginFragment()
                )
                .addToBackStack(null)
                .commit()
        }
    }
}