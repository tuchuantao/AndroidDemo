package com.kevin.demo.module.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.kevin.demo.module.db.table.StudentTable

/**
 * Created by tuchuantao on 2021/3/22
 */
class MySQLiteOpenHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    var studentTable: StudentTable = StudentTable(this)

    companion object {
        const val TAG: String = "MySQLiteOpenHelper"
        const val DATABASE_NAME: String = "DemoDB.db"
        const val VERSION: Int = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(StudentTable.CREATE_TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade() oldVersion=$oldVersion  newVersion=$newVersion")
    }
}