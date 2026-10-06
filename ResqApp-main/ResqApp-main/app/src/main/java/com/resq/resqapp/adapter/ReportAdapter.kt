package com.resq.resqapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.resq.resqapp.R
import com.resq.resqapp.model.ReportResponse

class ReportAdapter(
    private val reports: List<ReportResponse>,
    private val onClick: (ReportResponse) -> Unit
) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSpecies: TextView = view.findViewById(R.id.tv_species)
        val tvStatus: TextView = view.findViewById(R.id.tv_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val report = reports[position]
        holder.tvSpecies.text = report.species
        holder.tvStatus.text = report.status
        holder.itemView.setOnClickListener { onClick(report) }
    }

    override fun getItemCount() = reports.size
}