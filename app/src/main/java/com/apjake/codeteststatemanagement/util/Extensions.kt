package com.apjake.codeteststatemanagement.util

import android.content.Context
import java.lang.Error
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import android.telephony.TelephonyManager
import com.apjake.codeteststatemanagement.R


fun String?.validDateFormat(): Boolean {
    try {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = formatter.parse(this!!)
        return true
    } catch (e: Exception){}
    return false
}

fun String?.validPhonePrefix(): Boolean{
    val pattern = Regex("""^\+[1-9][0-9]{0,5}$""")
    return pattern matches this.orEmpty()
}

fun Long?.dateString(): String{
    if(this == null) return ""
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(this))
}

fun Context.getUserPhonePrefix(): String {
    try {
        var countryCode = ""
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val simCountry = tm.simCountryIso
        if (simCountry != null && simCountry.length == 2) { // SIM country code is available
            countryCode =  simCountry.uppercase(Locale.US)
        } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
            val networkCountry = tm.networkCountryIso
            if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                countryCode = networkCountry.uppercase(Locale.US)
            }
        }
        val countryList: Array<String> = this.resources.getStringArray(R.array.country_codes)
        for(index in countryList.indices){
            val countryAndCode = countryList[index].split(",").toTypedArray()
            if (countryAndCode[1].trim { it <= ' ' } == countryCode.trim { it <= ' ' }) {
                return "+${countryAndCode[0]}"
            }
        }
    } catch (e: Exception) {
    }
    return ""
}