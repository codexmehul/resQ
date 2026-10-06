package com.resq.resqapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.resq.resqapp.adapter.ReportAdapter
import com.resq.resqapp.api.RetrofitClient
import com.resq.resqapp.databinding.ActivityMainBinding
import com.resq.resqapp.model.ReportResponse
import com.resq.resqapp.util.PrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var reports: List<ReportResponse> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (PrefUtils.getToken(this) == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.rvReports.layoutManager = LinearLayoutManager(this)
        loadReports()

        binding.btnCreateReport.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }

    private fun loadReports() {
        val userId = PrefUtils.getUserId(this)
        if (userId == -1L) {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitClient.getApiService(this@MainActivity)
                val response = apiService.getUserReports(userId)
                if (response.isSuccessful) {
                    reports = response.body() ?: emptyList()
                    withContext(Dispatchers.Main) {
                        binding.rvReports.adapter = ReportAdapter(reports) { report ->
                            val intent = Intent(this@MainActivity, ReportDetailsActivity::class.java)
                            intent.putExtra("REPORT_ID", report.id)
                            startActivity(intent)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Failed to load reports", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}