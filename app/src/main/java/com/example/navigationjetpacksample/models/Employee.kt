package com.example.navigationjetpacksample.models

import java.io.Serializable

data class Employee(val id: Int, val firstName: String, val lastName: String, val email: String, val phoneNo:String, val age:String, val salary:String) : Serializable