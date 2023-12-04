package com.dam.simondicesandra

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.simondicesandra.ui.theme.SimonDiceSandraTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.dam.simondicesandra.MainActivity

@Composable
fun Botonera(miViewModel: VM) {
    val iuScope = rememberCoroutineScope()
    var texto = remember { mutableStateOf("START") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(vertical = 10.dp, horizontal = 45.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = if (miViewModel.getEstadoJuego() == EstadoJuego.FINALIZANDO) "Perdiste!" else "",
            modifier = Modifier
                .offset(x = 110.dp)
                .offset(y = 420.dp),
            color = Color.Red
        )
        Text(
            text = "Ronda: ${miViewModel.getRonda()}", modifier = Modifier
                .offset(y = 380.dp)
                .offset(x = 110.dp)
        )

        Row{
            Button(
                onClick = {
                    miViewModel.inicializarJuego()
                    miViewModel.aumentarSecuencia()
                },
                modifier = Modifier
                    .offset(x = -27.dp)
                    .size(100.dp, 60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    ),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            )
            {
                Text(text = "INICIO")
            }

            Button(
                onClick = {
                    miViewModel.comprobarSecuencia()
                },
                modifier = Modifier
                    .offset(x = 27.dp)
                    .size(100.dp, 60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    ),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            ) {
                Text("ENVIAR", fontFamily = FontFamily.SansSerif)
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Boton(Colores.CLASE_NARANJA, modifier = Modifier.size(150.dp), miViewModel = miViewModel)
            Boton(Colores.CLASE_VERDE, modifier = Modifier.size(150.dp), miViewModel = miViewModel)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Boton(Colores.CLASE_AZUL, modifier = Modifier.size(150.dp), miViewModel = miViewModel)
            Boton(Colores.CLASE_LILA, modifier = Modifier.size(150.dp), miViewModel = miViewModel)
        }

    }


}




@Composable
fun Boton(enum_color: Colores, modifier: Modifier, miViewModel: VM) {
    Button(
        colors = ButtonDefaults.buttonColors(enum_color.color),
        onClick = {
            miViewModel.aumentarSecuenciaUsuario(enum_color.ordinal)
        },
        modifier = modifier
            .size((100).dp, (100).dp)
            .padding(8.dp)

    ) {
        Text(text = enum_color.txt.value)
    }
}

@Composable
fun Botonera2(vm: VM) {
    // definimos un scope para la IU
    val iuScope = rememberCoroutineScope()
    var texto = remember { mutableStateOf("Click me!") }

    Button(
        modifier = Modifier
            .size((120).dp, (40).dp),
        onClick = {
            if (false) {
                vm.espera(5000L)
                Log.d("corutina", "IU no para!")
                iuScope.launch {
                    Log.d("corutina", "IU: voy a parar 2sgs")
                    texto.value = "Voy a parar"
                    delay(3000L)
                    Log.d("corutina", "Ahora sigo")
                    texto.value = "Sigo"
                }
            } else {


            }
        }
    ) {
        Text(text = texto.value, fontSize = 10.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimonDiceSandraTheme {
        Botonera(miViewModel = VM())
    }
}