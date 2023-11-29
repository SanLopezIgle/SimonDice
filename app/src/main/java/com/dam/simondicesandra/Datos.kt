package com.dam.simondicesandra

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * CLASE QUE CONTIENE LOS DATOS DEL JUEGO
 * @property ronda Numero de ronda en la que se encuentra el juego
 * @property secuencia Secuencia de colores
 * @property secuenciaUsuario Secuencia de colores introducida por el usuario
 * @property record Record del juego
 * @property estadoJuego Estado del juego
 */
object Datos {
    var ronda = mutableStateOf(0)
    var secuencia = mutableListOf<Int>()
    var secuenciaUsuario = mutableListOf<Int>()
    var record = 0
    var estadoJuego = EstadoJuego.INICIO
}


enum class EstadoJuego {
    INICIO,
    SECUENCIA,
    ESPERANDO,
    ENTRADA,
    COMPROBANDO,
    FINALIZANDO
}

enum class Colores(val color: Color, val txt: MutableState<String>) {

    CLASE_VERDE(color = Color(34, 153, 84), txt = mutableStateOf("VERDE")),
    CLASE_AZUL(color = Color( 36, 113, 163), txt = mutableStateOf("AZUL")),
    CLASE_LILA(color = Color(142, 68, 173), txt = mutableStateOf("LILA")),
    CLASE_NARANJA(color = Color(211, 84, 0), txt = mutableStateOf("NARANJA"));

}