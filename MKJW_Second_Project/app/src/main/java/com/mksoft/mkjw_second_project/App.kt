package com.mksoft.mkjw_second_project

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import javax.inject.Inject

class App :Application(){

    init{
        instance = this

    }

    companion object {
        private var instance: App? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }


    }
}