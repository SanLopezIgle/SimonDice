package com.dam.simondicesandra

import android.util.Log
import android.view.View
//import androidx.compose.ui.text.style.TextForegroundStyle.Unspecified.alpha
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Objects

class VM : ViewModel(){

    val TAG = "ejecutando"


    fun espera(segundos: Long){
        viewModelScope.launch {//EL launch es el que lanza la corutina.  LAnza la corutina dentro del viewmodel porque tengo el scope ahi y el launch. Le mando una funcion al launch.
            Log.d("corutina","Esperando en el ViewModel...")//Manda un mensaje al logcat
            delay(segundos)//Espera los segundos que le pasamos por parametro
            Log.d("corutina", "Listo en el ViewModel!!!")//Manda un mensaje al logcat
            //ESto lo voy a hacer cuando el usuario pulse un boton
        }
    }

    /**
     * Inicializa el juego
     */
    fun inicializarJuego() {
        Log.d(TAG, "InicializarJuego: ")
        reiniciarRonda()
        reiniciarSecuencia()
        reiniciarSecuenciaUsuario()
        Datos.estadoJuego = EstadoJuego.INICIO
    }

    /**
     * Reinicia la ronda
     */
    fun reiniciarRonda() {
        Datos.ronda.value = 0
    }

    /**
     * Reinicia la secuencia
     */
    fun reiniciarSecuencia() {
        Datos.secuencia.clear()
    }

    /**
     * Reinicia la secuencia introducida por el usuario
     */
    fun reiniciarSecuenciaUsuario() {
        Datos.secuenciaUsuario.clear()
        Datos.secuenciaUsuario= mutableListOf()
    }

    /**
     * Aumenta la secuencia de colores
     */
    fun aumentarSecuencia() {
        Log.d(TAG, "AumentarSecuencia: ")
        Datos.estadoJuego = EstadoJuego.SECUENCIA
        generarColor()
        mostrarSecuencia()
        //Datos.estadoJuego = EstadoJuego.ESPERANDO

    }

    /**
     * Genera un color aleatorio (usando el enum Colores(arrays))
     */
    fun generarColor() {//Añado los numeros aleatorios a la lista de secuencia
        Log.d(TAG, Datos.secuencia.toString())
        Datos.secuencia.add(generarNumeroAleatorio(3))
    }

    fun generarNumeroAleatorio(maximo: Int): Int {
        return (0..maximo-1).random()
    }


    /*

    */
    /**
     * Muestra la secuencia de colores al usuario
     */

    fun mostrarSecuencia() {
        viewModelScope.launch {
            for (color in Datos.secuencia) {//Estás recorriendo cada elemento en la lista Datos.secuencia, y en cada iteración, color toma el valor de uno de los elementos de la lista.

                val colorOriginal = Colores.values()[color].color
                val colorOscuro = colorOriginal.copy(alpha = 0.8f)
                Log.d(TAG, "Mostrar secuencia: "+color.toString())
                val aux: String = Colores.values()[color].txt.value//Guardo el valor del color en una variable auxiliar
                Colores.values()[color].txt.value = " " //Pongo el valor del color a un espacio en blanco
                delay(800L)
                Colores.values()[color].txt.value = aux
                delay(800L)
            }
        }
    }

    /**
     * Aumentar la secuencia del usuario
     * @param color Color introducido por el usuario
     */
    fun aumentarSecuenciaUsuario(color: Int) {
        Datos.estadoJuego = EstadoJuego.ENTRADA
        Datos.secuenciaUsuario.add(color)
    }

    /**
     * Comprueba si la secuencia introducida por el usuario es correcta
     */
    fun comprobarSecuencia() {
        Datos.estadoJuego = EstadoJuego.COMPROBANDO
        viewModelScope.launch {
            if (Datos.secuencia == Datos.secuenciaUsuario) {
                Log.d(TAG,"Ronda: "+Datos.ronda.toString())
                Datos.ronda.value++
                if (Datos.ronda.value > Datos.record) {
                    Datos.record = Datos.ronda.value
                }
                reiniciarSecuenciaUsuario()
                Log.d(TAG, "Correcto")
                aumentarSecuencia()
            } else {
                reiniciarRonda()
                Datos.estadoJuego = EstadoJuego.FINALIZANDO
                Log.d(TAG, "Incorrecto")
            }
        }
    }

    fun getEstadoJuego(): EstadoJuego {
        return Datos.estadoJuego
    }

    /**
     * Obtener las rondas del juego.
     */
    fun getRonda(): Int {
        return Datos.ronda.value
    }
}