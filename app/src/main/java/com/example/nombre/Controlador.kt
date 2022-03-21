package com.example.nombre

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.nombre.clases.Deporte
import com.example.nombre.clases.Usuario

class Controlador : Fragment(){
    private var user: Usuario? = null
    private var deporte: Deporte? = null

    fun setDeporte(deporte: Deporte){
        this.deporte = deporte
    }

    fun getDeporte(): Deporte?{
        return deporte
    }

    fun delDeporte(){
        deporte = null
    }

    fun setUser(user: Usuario) {
        this.user = user
    }

    fun getUser(): Usuario? {
        return user
    }

    fun delUser() {
        user = null
    }

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
