package com.example.navigationjetpacksample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationjetpacksample.R
import com.example.navigationjetpacksample.databinding.FragEmployeesListRowBinding
import com.example.navigationjetpacksample.fragments.EmployeesListFragmentDirections
import com.example.navigationjetpacksample.models.Employee

class EmployeesListAdapter (private val context: Context, private val list: MutableList<Employee>) : RecyclerView.Adapter<EmployeesListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: FragEmployeesListRowBinding = DataBindingUtil.inflate(inflater, R.layout.frag_employees_list_row,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val getEmployee = list[position]
        holder.itemBinding.employee = getEmployee

        holder.itemBinding.root.setOnClickListener {
            val action = EmployeesListFragmentDirections.actionEmployeesListFragmentToEmployeeDetailsFragment(getEmployee)
            it.findNavController().navigate(action)
        }

        holder.itemBinding.imgEdit.setOnClickListener {
            val action = EmployeesListFragmentDirections.actionEmployeesListFragmentToEditEmployeeFragment(getEmployee)
            it.findNavController().navigate(action)
        }

        holder.itemBinding.imgDelete.setOnClickListener {
            val action = EmployeesListFragmentDirections.actionEmployeesListFragmentToDeleteEmployeeFragment(getEmployee)
            it.findNavController().navigate(action)
        }

    }

    class MyViewHolder(val itemBinding: FragEmployeesListRowBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : FragEmployeesListRowBinding? = null

        init {
            this.binding = itemBinding
        }

    }

}