package com.kevin.demo.module.db

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityDbBinding
import com.kevin.demo.module.db.table.Student
import com.kevin.demo.module.db.table.StudentTable
import java.lang.StringBuilder

/**
 * Created by tuchuantao on 2021/3/22
 */
class DBActivity : BaseActivity<ActivityDbBinding>() {
    var table: StudentTable? = null

    override fun getLayoutResId(): Int {
        return R.layout.activity_db
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.content.movementMethod = ScrollingMovementMethod()
        binding.btnCreateDb.setOnClickListener {
            var openHelper = MySQLiteOpenHelper(this)
            table = openHelper.studentTable
        }

        var index = 1
        binding.btnInsert.setOnClickListener {
            var student = Student(index, "张三 $index", 18 + index, "班级 $index")
            index++
            table?.let {
                var result = it.insert(student)
                binding.content.text = "Insert result id=$result"
            }
        }

        binding.btnQuery.setOnClickListener {
            table?.let {
                var studentList = it.getStudents()
                if (studentList.isEmpty()) {
                    binding.content.text = "student table is empty !!!"
                } else {
                    var builder = StringBuilder()
                    studentList.forEach { student ->
                        builder.append(student.toString())
                            .append("\n")
                            .append("\n")
                    }
                    binding.content.text = builder.toString()
                }
            }
        }

        binding.btnQueryLast.setOnClickListener {
            table?.let {
                var student = it.getLastStudent()
                if (student == null) {
                    binding.content.text = "student table is empty !!!"
                } else {
                    binding.content.text = "Last item =$student"
                }
            }
        }

        binding.btnUpdate.setOnClickListener {
            table?.let {
                var student = it.getLastStudent()
                if (student == null) {
                    binding.content.text = "student table is empty !!!"
                } else {
                    student.name = "李斯"
                    it.update(student)
                }
            }
        }

        binding.btnDelete.setOnClickListener {
            table?.let {
                var student = it.getLastStudent()
                if (student == null) {
                    binding.content.text = "student table is empty !!!"
                } else {
                    it.delete(student)
                }
            }
        }
    }
}