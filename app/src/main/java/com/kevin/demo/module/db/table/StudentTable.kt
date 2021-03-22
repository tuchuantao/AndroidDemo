package com.kevin.demo.module.db.table

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by tuchuantao on 2021/3/22
 */
class StudentTable(private val sqLiteOpenHelper: SQLiteOpenHelper) {
    companion object {
        const val TABLE_NAME = "student"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_GRADE = "grade"

        const val CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_AGE INTEGER," +
                "$COLUMN_GRADE TEXT" +
                ");"
    }

    fun getStudents(): List<Student> {
        var database = sqLiteOpenHelper.readableDatabase
        var cursor = database.query(TABLE_NAME, null, null, null, null, null, null)
//        var course = database.rawQuery("select * from student", null)
        var students = ArrayList<Student>()
        cursor?.let {
            while (cursor.moveToNext()) {
                var student = Student(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_GRADE))
                )
                students.add(student)
            }
        }
        cursor.close()
        database.close()
        return students
    }

    fun getLastStudent(): Student? {
        var database = sqLiteOpenHelper.readableDatabase
        var cursor =
            database.query(TABLE_NAME, null, null, null, null, null, "$COLUMN_ID DESC", "1")
        var student: Student? = null
        while (cursor.moveToNext()) {
            student = Student(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_GRADE))
            )
        }
        cursor.close()
        return student
    }

    fun insert(student: Student): Long {
        var database = sqLiteOpenHelper.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, student.name)
        contentValues.put(COLUMN_AGE, student.age)
        contentValues.put(COLUMN_GRADE, student.grade)
        return database.insert(TABLE_NAME, null, contentValues)
    }

    fun update(student: Student): Int {
        var database = sqLiteOpenHelper.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, student.name)
        contentValues.put(COLUMN_AGE, student.age)
        contentValues.put(COLUMN_GRADE, student.grade)
        return database.update(
            TABLE_NAME,
            contentValues,
            "$COLUMN_ID=?",
            arrayOf(student.id.toString())
        )
    }

    fun delete(student: Student): Int {
        var database = sqLiteOpenHelper.writableDatabase
        return database.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(student.id.toString()))
    }
}