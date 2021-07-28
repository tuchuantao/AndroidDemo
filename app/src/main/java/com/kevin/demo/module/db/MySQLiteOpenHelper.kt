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
        //const val DATABASE_NAME: String = "demo_db.db"
        val DATABASE_NAME: String? = null
        const val VERSION: Int = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate()")
        db.execSQL(StudentTable.CREATE_TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade() oldVersion=$oldVersion  newVersion=$newVersion")
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onDowngrade() oldVersion=$oldVersion  newVersion=$newVersion")
    }

    override fun onOpen(db: SQLiteDatabase) {
        Log.d(TAG, "onOpen()  path=${db.path}")
    }

    override fun onConfigure(db: SQLiteDatabase) {
        Log.d(TAG, "onConfigure()  path=${db.path}")
    }

    override fun setIdleConnectionTimeout(idleConnectionTimeoutMs: Long) {
        super.setIdleConnectionTimeout(idleConnectionTimeoutMs)
        Log.d(TAG, "setIdleConnectionTimeout()  idleConnectionTimeoutMs=$idleConnectionTimeoutMs")
    }

    override fun setLookasideConfig(slotSize: Int, slotCount: Int) {
        super.setLookasideConfig(slotSize, slotCount)
        Log.d(TAG, "setLookasideConfig()  slotSize=$slotSize   slotCount=$slotCount")
    }

    override fun setOpenParams(openParams: SQLiteDatabase.OpenParams) {
        super.setOpenParams(openParams)
        Log.d(TAG, "setOpenParams()  openParams=$openParams")
    }

    override fun setWriteAheadLoggingEnabled(enabled: Boolean) {
        super.setWriteAheadLoggingEnabled(enabled)
        Log.d(TAG, "setWriteAheadLoggingEnabled()  enabled=$enabled")
    }
}