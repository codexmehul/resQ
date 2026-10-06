package com.resq.resqapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.resq.resqapp.api.RetrofitClient
import com.resq.resqapp.databinding.ActivityRegisterBinding
import com.resq.resqapp.model.NgoRegisterRequest
import com.resq.resqapp.model.UserRegisterRequest
import com.resq.resqapp.util.PrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchRole.setOnCheckedChangeListener { _, isChecked ->
            val visibility = if (isChecked) View.VISIBLE else View.GONE
            binding.tilName.visibility = visibility
            binding.tilSpecialization.visibility = visibility
            binding.tilLatitude.visibility = visibility
            binding.tilLongitude.visibility = visibility
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val role = if (binding.switchRole.isChecked) "NGO" else "USER"

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (role == "NGO") {
                    val name = binding.etName.text.toString()
                    val spec = binding.etSpecialization.text.toString()
                    val lat = binding.etLatitude.text.toString().toDoubleOrNull() ?: 0.0
                    val lon = binding.etLongitude.text.toString().toDoubleOrNull() ?: 0.0
                    if (name.isNotEmpty() && spec.isNotEmpty()) {
                        registerNgo(email, password, name, spec, lat, lon)
                    } else {
                        Toast.makeText(this, "Fill NGO fields", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    registerUser(email, password)
                }
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun registerUser(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitClient.getApiService(this@RegisterActivity)  // Use this to get the service with context
                val response = apiService.registerUser(UserRegisterRequest(email, password))
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "User registered", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegisterActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun registerNgo(email: String, password: String, name: String, spec: String, lat: Double, lon: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitClient.getApiService(this@RegisterActivity)  // Use this to get the service with context
                val response = apiService.registerNgo(NgoRegisterRequest(email, password, name, spec, lat, lon))
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "NGO registered", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegisterActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}