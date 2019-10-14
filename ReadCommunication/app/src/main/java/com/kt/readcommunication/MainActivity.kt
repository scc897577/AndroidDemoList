package com.kt.readcommunication

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName
    var permissions = arrayOf(Manifest.permission.READ_CONTACTS)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Permissions.checkPermissions(this, permissions, object : Callback2() {
            override fun success() {
                Log.e(TAG, "success")
                showContacts()
            }

            override fun failure() {
                Log.e(TAG, "failure")
            }
        })

    }

    private fun showContacts() {
        val contacts = ContactUtils.getAllContacts(this@MainActivity)

        mTvShow.setText(contacts.toString())
        Log.e("scc", "contacts:$contacts")
    }
}
