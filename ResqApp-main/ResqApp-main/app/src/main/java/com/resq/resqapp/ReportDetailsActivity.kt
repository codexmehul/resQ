package com.resq.resqapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.resq.resqapp.api.RetrofitClient
import com.resq.resqapp.databinding.ActivityReportDetailsBinding
import com.resq.resqapp.model.ReportResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReportDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reportId = intent.getLongExtra("REPORT_ID", -1)
        if (reportId != -1L) {
            loadReportDetails(reportId)
        } else {
            Toast.makeText(this, "Invalid report ID", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadReportDetails(reportId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitClient.getApiService(this@ReportDetailsActivity)
                val response = apiService.getReport(reportId)
                if (response.isSuccessful) {
                    val report = response.body()
                    withContext(Dispatchers.Main) {
                        if (report != null) {
                            binding.tvSpecies.text = "Species: ${report.species}"
                            binding.tvDescription.text = "Description: ${report.description}"
                            binding.tvUrgency.text = "Urgency: ${report.urgency}"
                            binding.tvLocation.text = "Location: ${report.latitude}, ${report.longitude}"
                            binding.tvStatus.text = "Status: ${report.status}"
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ReportDetailsActivity, "Failed to load details", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ReportDetailsActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}