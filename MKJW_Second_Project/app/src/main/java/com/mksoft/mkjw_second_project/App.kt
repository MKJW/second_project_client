package com.mksoft.mkjw_second_project

import android.app.Application
import android.content.Context

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