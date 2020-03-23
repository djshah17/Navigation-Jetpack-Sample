package com.example.navigationjetpacksample.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationjetpacksample.R
import com.example.navigationjetpacksample.databinding.FragEditEmployeeBinding
import com.example.navigationjetpacksample.models.Employee
import kotlinx.android.synthetic.main.frag_edit_employee.*

class EditEmployeeFragment : Fragment() {

    private val args: EditEmployeeFragmentArgs by navArgs()

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
        val binding : FragEditEmployeeBinding = DataBindingUtil.inflate(inflater,
            R.layout.frag_edit_employee,container,false)
        binding.employee = args.employee
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_edit.setOnClickListener {
            val firstName = edt_first_name.text.toString()
            val lastName = edt_last_name.text.toString()
            val email = edt_email.text.toString()
            val phoneNo = edt_phone_no.text.toString()
            val age = edt_age.text.toString()
            val salary = edt_salary.text.toString()

            if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(salary)){
                val newEmployee = Employee(args.employee.id,firstName,lastName,email,phoneNo,age,salary)
                val action = EditEmployeeFragmentDirections.actionEditEmployeeFragmentToEmployeesListFragment(newEmployee,EditEmployeeFragment::class.java.name)
                it.findNavController().navigate(action)
            } else{
                Toast.makeText(ctx,"All fields are required",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDetach() {
        super.onDetach()
        this.ctx = null
    }

}