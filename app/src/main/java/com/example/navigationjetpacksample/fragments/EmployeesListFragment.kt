package com.example.navigationjetpacksample.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationjetpacksample.R
import com.example.navigationjetpacksample.adapters.EmployeesListAdapter
import com.example.navigationjetpacksample.databinding.FragEmployeesListBinding
import com.example.navigationjetpacksample.models.Employee
import com.example.navigationjetpacksample.utils.Constants.listEmployees
import kotlinx.android.synthetic.main.frag_employees_list.*

class EmployeesListFragment : Fragment() {

    private val args : EmployeesListFragmentArgs by navArgs()
    private var ctx: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragEmployeesListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.frag_employees_list, container, false
        )

        args.employee?.let {mEmployee ->
            if (args.previousScreen == AddEmployeeFragment::class.java.name) {
                listEmployees.add(mEmployee)
            } else {
                var pos = -1
                listEmployees.forEach {
                    pos++
                    if (it.id == mEmployee.id) {
                        listEmployees[pos] = mEmployee
                    }
                }
            }
        }

        binding.recyclerEmployees.layoutManager = LinearLayoutManager(ctx)
        val adapter = ctx?.let { EmployeesListAdapter(it,listEmployees) }
        binding.recyclerEmployees.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_add.setOnClickListener {
            it.findNavController().navigate(EmployeesListFragmentDirections.actionEmployeesListFragmentToAddEmployeeFragment())
        }

    }

    override fun onDetach() {
        super.onDetach()
        this.ctx = null
    }

}