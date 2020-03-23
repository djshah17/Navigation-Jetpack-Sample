# Navigation-Jetpack-Sample
This is a navigation jetpack architecture components sample in kotlin.


## Add the below dependencies in your app level build.gradle file
``` gradle
apply plugin: 'androidx.navigation.safeargs'

android{
compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
implementation 'androidx.navigation:navigation-fragment-ktx:2.2.1'
implementation 'androidx.navigation:navigation-ui-ktx:2.2.1'
}
```

## Add the below dependency in your top level build.gradle file
``` gradle
dependencies {
classpath  'android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0'
}
```

## Create a navigation graph
To add a navigation graph to your project, do the following:

1. In the Project window, right-click on the res directory and select New > Android Resource File. The New Resource File dialog appears.
2. Type a name in the File name field, such as "nav_graph".
3. Select Navigation from the Resource type drop-down list, and then click OK.

## Add a NavHost to an activity
``` xml
<androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".activities.MainActivity">

        <fragment
            android:id="@+id/nav_host_fragment"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:name="androidx.navigation.fragment.NavHostFragment"
            app:defaultNavHost="true"
            app:navGraph="@navigation/nav_graph"/>

    </androidx.constraintlayout.widget.ConstraintLayout>
```

## Add destinations to the navigation graph
To add a new destination using the Navigation Editor, do the following:

1. In the Navigation Editor, click the New Destination icon , and then click Create new destination.
2. In the New Android Component dialog that appears, create your fragment.

Output will be
``` xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/employeesListFragment">

    <fragment
        android:id="@+id/employeesListFragment"
        android:name="com.example.navigationjetpacksample.fragments.EmployeesListFragment"
        android:label="EmployeesListFragment"
        tools:layout="@layout/frag_employees_list"/>
    
</navigation>
```

## Connect destinations
You can use the Navigation Editor to connect two destinations by doing the following:
1. In the Design tab, hover over the right side of the destination that you want users to navigate from. A circle appears over the right side of the destination
2. Click and drag your cursor over the destination you want users to navigate to, and release. The resulting line between the two destinations represents an action

Output will be
``` xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/employeesListFragment">

    <fragment
        android:id="@+id/employeesListFragment"
        android:name="com.example.navigationjetpacksample.fragments.EmployeesListFragment"
        android:label="EmployeesListFragment"
        tools:layout="@layout/frag_employees_list">
        <action
            android:id="@+id/action_employeesListFragment_to_employeeDetailsFragment"
            app:destination="@id/employeeDetailsFragment" />
        <action
            android:id="@+id/action_employeesListFragment_to_addEmployeeFragment"
            app:destination="@id/addEmployeeFragment" />
        <action
            android:id="@+id/action_employeesListFragment_to_editEmployeeFragment"
            app:destination="@id/editEmployeeFragment" />
        <action
            android:id="@+id/action_employeesListFragment_to_deleteEmployeeFragment"
            app:destination="@id/deleteEmployeeFragment" />
    </fragment>

</navigation>
```

## Navigate to a destination
**Kotlin:**

* Fragment.findNavController()
* View.findNavController()
* Activity.findNavController(viewId: Int)

**Java:**

* NavHostFragment.findNavController(Fragment)
* Navigation.findNavController(Activity, @IdRes int viewId)
* Navigation.findNavController(View)

``` kotlin
fab_add.setOnClickListener {
            it.findNavController().navigate(R.id.addEmployeeFragment)
}
```

## Pass data between destinations with Safe Args
To pass data between destinations, first define the argument by adding it to the destination that receives it by following these steps:

1. In the Navigation editor, click on the destination that receives the argument.
2. In the Attributes panel, click Add (+).
3. In the Add Argument Link window that appears, enter the argument name, argument type, whether the argument is nullable, and a default value, if needed.
4. Click Add. Notice that the argument now appears in the Arguments list in the Attributes panel.

Output will be
``` xml
<fragment
    android:id="@+id/employeeDetailsFragment"
    android:name="com.example.navigationjetpacksample.fragments.EmployeeDetailsFragment"
    android:label="EmployeeDetailsFragment"
    tools:layout="@layout/frag_employee_details">
    <argument
        android:name="employee"
        app:argType="com.example.navigationjetpacksample.models.Employee" />
</fragment>
```

In your sending destination’s code
``` kotlin
holder.itemBinding.root.setOnClickListener {
            val action = EmployeesListFragmentDirections.actionEmployeesListFragmentToEmployeeDetailsFragment(getEmployee)
            it.findNavController().navigate(action)
}
```

In your receiving destination’s code
``` kotlin
val args : EmployeeDetailsFragmentArgs by navArgs()

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
```

## Animate transitions between destinations
 To add animations to an action, do the following:

1. In the Navigation editor, click on the action where the animation should occur.
2. In the Animations section of the Attributes panel, click the dropdown arrow next to the animation you'd like to add. You can choose between the following types:
Entering a destination
Exiting a destination
Entering a destination via a pop action
Exiting a destination via a pop action
3. Choose an animation from the list of project animations that appears.

Output will be
``` xml
<fragment
    android:id="@+id/employeesListFragment"
    android:name="com.example.navigationjetpacksample.fragments.EmployeesListFragment"
    android:label="EmployeesListFragment"
    tools:layout="@layout/frag_employees_list">
    <action
        android:id="@+id/action_employeesListFragment_to_employeeDetailsFragment"
        app:destination="@id/employeeDetailsFragment"
        app:enterAnim="@anim/enter_from_right"
        app:exitAnim="@anim/exit_to_left"
        app:popEnterAnim="@anim/enter_from_left"
        app:popExitAnim="@anim/exit_to_right"/>
</fragment>
```
