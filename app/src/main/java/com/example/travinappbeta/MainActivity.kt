package com.example.travinappbeta

import android.app.FragmentManager.BackStackEntry

import com.google.gson.Gson
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star

import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.tooling.preview.Preview
import com.example.travinappbeta.ui.theme.TravinAppBetaTheme

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


data class DatosUsuario(
    val nombre: String,
    val celular: String,
    val peso: String
)




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { InicioPantalla(navController) }
        composable("principal") { PrincipalPantalla("") }
    }
}
@Composable
fun InicioPantalla(navController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.mi_imagen_fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(R.drawable.logogreen),
                contentDescription = "logo app",
                modifier = Modifier
                    .size(250.dp)
                    .padding(0.dp),
                //contentScale = ContentScale.FillWidth

            )
            Text(
                text = "Bienvenido",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Empieza tu prueba con TRAVIN, se tomarán unos datos y evaluaremos tu rendimiento",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate("principal")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112,192,31),
                    contentColor = Color.Black

                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                ) {
                Text(text = "Empezar", fontSize = 18.sp)
            }


        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalPantalla(selectedTab: String ) {
    val navController2 = rememberNavController()
    var selectedT = selectedTab
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.logotravingreen2),
                        contentDescription = "logo title",
                        modifier = Modifier

                            .padding(horizontal = 0.dp)
                            .width(200.dp)


                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                NavigationBar(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent
                ) {
                    NavigationBarItem(
                        selected = selectedTab == "new",
                        label = { Text("Nueva prueba") },
                        icon = { Icon(Icons.Filled.Star, contentDescription = null) },
                        onClick = {navController2.navigate("new")
                            selectedT = "new"
                        }
                    )
                    NavigationBarItem(
                        selected = selectedTab == "reg",
                        label = { Text("Registros") },
                        icon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
                        onClick = {navController2.navigate("register")
                            selectedT = "reg"
                        }
                    )
                    NavigationBarItem(
                        selected = selectedTab == "more",
                        label = { Text("$selectedT") },
                        icon = { Icon(Icons.Filled.Info, contentDescription = null) },
                        onClick = {navController2.navigate("more")
                            selectedT = "more"
                        }

                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            NavHost(navController = navController2, startDestination = "new") {
                composable("new") { NewData(navController2) }
                composable("result/{data}") { backStackEntry ->
                    val datos = backStackEntry.arguments?.getString("data") ?: ""
                    Result(name = datos)
                }
                composable("register") { Register( ) }
                composable("more") { More() }

            }

        }
    }
}




@Composable
fun NewData(navController2: NavHostController){

    var nombre by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var pesoa by remember { mutableStateOf("") }
    var pesob by remember { mutableStateOf("") }
    //var data = mutableMapOf()

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text = "Ingrese sus datos:",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(text ="Nombre: ", fontSize = 15.sp, modifier = Modifier.size(70.dp,25.dp))

            TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre", modifier = Modifier.size(70.dp,25.dp)) },
            //modifier = Modifier.fillMaxWidth()
        )}
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {   Text(text= "Celular", fontSize = 15.sp, modifier = Modifier.size(70.dp,25.dp))

            TextField(
                value = celular,
                onValueChange = { celular = it },
                label = { Text("Celular") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                //keyboardActions = KeyboardActions.() convertir la accion de enter a pasar al siguiente campo
            )

        }
        Text(
            text="Datos antes y despues de la actividad fisica:",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text= "Peso antes", fontSize = 15.sp,modifier = Modifier.size(100.dp,25.dp))
            TextField(
                value = pesoa,
                onValueChange = { pesoa = it },
                label = { Text("Peso (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )

        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text= "Peso después", fontSize = 15.sp, modifier = Modifier.size(100.dp,25.dp))
            TextField(
                value = pesob,
                onValueChange = { pesob = it },
                label = { Text("Peso (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)

            )

        }




        Button(
            onClick = {
                //val datosUsuario = DatosUsuario(nombre, celular, peso)
                //val datosUsuarioJson = Gson().toJson(datosUsuario)
                //navController2.navigate("result/$nombre")
                //val datosUsuario = DatosUsuario(nombre, celular, peso)
                //val datosUsuarioJson = Gson().toJson(datosUsuario)
                //navController2.navigate("result/$datosUsuarioJson")
                //var ty = mutableMapOf("llave" : "valor")
                //Text("$ty")

                var data = mutableMapOf(
                    "nombre" to nombre,
                    "celular" to celular,
                    "pesoa" to pesoa,
                    "pesob" to pesob
                )
                var datosJson = Gson().toJson(data)



                navController2.navigate("result/$datosJson")

                //resultado = "Nombre: $nombre, Celular: $celular, Peso: $peso"
            },
            enabled = nombre != "" && celular != "" && pesoa != "" && pesob != ""

            ) {

            Text(text = "Calcular ", fontSize = 18.sp)
        }


    }
}
@Composable
fun Result(name: String){
    Box(
        modifier = Modifier
            .padding(1.dp),
    )
    {
        Button(onClick = {}) {
            Text(text = "Result $name",color= Color.Black)
        }
    }
}

@Composable
fun Register(){
    Text("registros de database here")
}
@Composable
fun More(){
    Text("More, the QR here")
}
//@Preview(showBackground = true)
@Preview
@Composable
fun nowpreview(){
    PrincipalPantalla("new")
}