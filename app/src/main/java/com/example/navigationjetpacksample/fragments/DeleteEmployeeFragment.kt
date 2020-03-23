package com.example.navigationjetpacksample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationjetpacksample.R
import com.example.navigationjetpacksample.databinding.FragDeleteEmployeeBinding
import com.example.navigationjetpacksample.utils.Constants
import kotlinx.android.synthetic.main.frag_delete_employee.*

class DeleteEmployeeFragment : Fragment() {

    private val args : DeleteEmployeeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragDeleteEmployeeBinding = DataBindingUtil.inflate(inflater,
            R.layout.frag_delete_employee,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_cancel.setOnClickListener {
            it.findNavController().popBackStack()
        }

        btn_submit.setOnClickListener {
            Constants.listEmployees.remove(args.employee)
            it.findNavController().navigate(DeleteEmployeeFragmentDirections.actionDeleteEmployeeFragmentToEmployeesListFragment2())
        }

    }

}