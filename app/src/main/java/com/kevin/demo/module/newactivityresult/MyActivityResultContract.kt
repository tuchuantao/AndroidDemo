package com.kevin.demo.module.newactivityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.kevin.demo.module.affinity.StandardActivity

/**
 * 已经预定义的Contract：
 *   StartActivityForResult()
 *   RequestMultiplePermissions()
 *   RequestPermission()
 *   TakePicturePreview()
 *   TakePicture()
 *   TakeVideo()
 *   PickContact()
 *   CreateDocument()
 *   OpenDocumentTree()
 *   OpenMultipleDocuments()
 *   OpenDocument()
 *   GetMultipleContents()
 *   GetContent()
 *
 * Created by tuchuantao on 2021/2/24
 */
class MyActivityResultContract : ActivityResultContract<String, String>() {

    override fun createIntent(context: Context, input: String?): Intent {
        var intent = Intent(context, StandardActivity::class.java)
        intent.putExtra("name", input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        var data = intent?.getStringExtra("result")
        if (resultCode == Activity.RESULT_OK) {
            return data ?: null
        }
        return null
    }
}