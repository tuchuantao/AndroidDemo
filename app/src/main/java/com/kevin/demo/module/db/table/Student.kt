package com.kevin.demo.module.db.table

/**
 * Created by tuchuantao on 2021/3/22
 */
data class Student(var id: Int, var name: String, var age: Int, var grade: String) {

    override fun toString(): String {
        return "Student[id=$id, name=$name, age=$age, grade=$grade]"
    }
}