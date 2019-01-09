package net.techpda.gudle

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {

    private val PREFS_FILENAME = "prefs"
    private val pref: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    private val UNDEFINED: String = "undefined"
    private val UNDERFLOW: Int = -4096

    fun getString(name: String) : String { return pref!!.getString(name, UNDEFINED) }
    fun putString(name: String, content: String) { pref.edit().putString(name, content)}

    fun getInt(name: String) : Int { return pref!!.getInt(name, UNDERFLOW) }
    fun putInt(name: String, content: Int) { pref.edit().putInt(name, content)}

    fun getFloat(name: String) : Float { return pref!!.getFloat(name, UNDERFLOW.toFloat())}
    fun putFloat(name: String, content: Float) { pref.edit().putFloat(name, content)}

    fun getBoolean(name: String) : Boolean { return pref!!.getBoolean(name, false) }
    fun putBoolean(name: String, content: Boolean) { pref.edit().putBoolean(name, content)}


    var test: String
        get() = getString("test")
        set(value) = pref.edit().putString("test", value).apply()

    var deviceId: String
        get() = getString("deviceId")
        set(value) = pref.edit().putString("deviceId", value).apply()

    var deviceName: String
        get() = getString("deviceName")
        set(value) = pref.edit().putString("deviceName", value).apply()

    var pushKey: String
        get() = getString("pushKey")
        set(value) = pref.edit().putString("pushKey", value).apply()


}