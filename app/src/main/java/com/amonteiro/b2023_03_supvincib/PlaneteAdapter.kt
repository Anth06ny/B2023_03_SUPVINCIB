package com.amonteiro.b2023_03_supvincib

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.amonteiro.b2023_03_supvincib.databinding.RowPlaneteBinding

class PlaneteAdapter : ListAdapter<PlaneteBean, PlaneteAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(val binding : RowPlaneteBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    class Comparator() : DiffUtil.ItemCallback<PlaneteBean>(){
        override fun areItemsTheSame(oldItem: PlaneteBean, newItem: PlaneteBean)
        = oldItem === newItem

        override fun areContentsTheSame(oldItem: PlaneteBean, newItem: PlaneteBean)
                = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(RowPlaneteBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvville.setText(item.name)
        holder.binding.tvtemp.setText("${item.temperature ?: "-"}°")

        holder.binding.main.setOnClickListener{
            Toast.makeText(holder.binding.main.context, item.name, Toast.LENGTH_LONG).show()
        }
    }

}