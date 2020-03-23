package com.example.navigationjetpacksample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.navigationjetpacksample.R
import com.example.navigationjetpacksample.databinding.FragEmployeeDetailsBinding

class EmployeeDetailsFragment : Fragment() {

    private val args : EmployeeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragEmployeeDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.frag_employee_details,container,false)
        binding.employee = args.employee
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}