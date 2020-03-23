package com.example.navigationjetpacksample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationjetpacksample.R
import com.example.navigationjetpacksample.databinding.ActivityMainBinding
import com.example.navigationjetpacksample.models.Employee
import com.example.navigationjetpacksample.utils.Constants.listEmployees

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        listEmployees = mutableListOf()
        listEmployees.add(Employee(1,"Roberto","Sanchez","rob@san.com","423424244","37","324324"))
        listEmployees.add(Employee(2,"Luis","Hernandez","lui@her.com","368562411","32","789218"))
        listEmployees.add(Employee(3,"David","Robertson","dav@rob.com","640234284","35","565787"))

    }
}
