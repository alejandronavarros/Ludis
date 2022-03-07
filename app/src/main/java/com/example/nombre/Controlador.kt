package com.example.nombre

import android.app.AlertDialog
import androidx.fragment.app.Fragment

class Controlador : Fragment(){
    fun setAlert(alertnum: Int) {
        val alert = getString(alertnum)
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(alert)?.setTitle(R.string.error)
            ?.setNeutralButton("Ok", null)
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }
    private fun setAlert(alert: String) {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(alert)?.setTitle(R.string.error)
            ?.setNeutralButton("Ok", null)
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }
}
