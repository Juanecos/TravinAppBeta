package com.example.travinappbeta

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TextField

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction


import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.rememberScrollState
import androidx.activity.viewModels
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.style.TextOverflow

import com.example.travinappbeta.data.User
import com.example.travinappbeta.data.UserViewModel



fun getColor(name : String): Color {
     when(name){
         "Green1" -> {
             return(Color(0xFF70C11D))
         }
         "Green2" -> {
             return(Color(0xFF92D050))
         }
         "Green3" -> {
             return(Color(0xFFB4E084))
         }
         "Green4" ->{
             return(Color(0xFFD4EAD3))
         }

     }
    return Color.Black
}
class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigator(userViewModel = userViewModel)
        }
    }
}

@Composable
fun AppNavigator(userViewModel: UserViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { InicioPantalla(navController) }
        composable("principal") { PrincipalPantalla("",userViewModel) }
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
                    .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ){
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(40.dp)

                ){
                    //Spacer(modifier = Modifier.height(50.dp))
                    Image(
                        painter = painterResource(R.drawable.logogreen),
                        contentDescription = "logo app",
                        modifier = Modifier
                            .size(130.dp, 130.dp)
                        //contentScale = ContentScale.FillWidth

                    )
                    Text(
                        text = "Bienvenido",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    //Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Empieza tu prueba, se tomarán unos datos y evaluaremos tu rendimiento",
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    //Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "TRAVIN Transformando vidas a través de la alimentación consciente",
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Button(
                        onClick = {
                            navController.navigate("principal")
                        },
//                    containerColor = Color(112,192,31),
//                    contentColor = Color.Black
                        colors = ButtonDefaults.buttonColors(
                            getColor("Green1")

                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        ) {
                        Text(text = "Empezar", fontSize = 18.sp, color = Color.Black)
                    }
                    //Spacer(modifier = Modifier.height(32.dp))

                    //Spacer(modifier = Modifier.height(100.dp))



                }
            }


        }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalPantalla(selectedTab: String , userViewModel: UserViewModel) {
    val navController2 = rememberNavController()
    var selectedT = selectedTab
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(getColor("Green2")),
                title = {

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){  Image(
                        painter = painterResource(R.drawable.logotravindark),
                        contentDescription = "logo title",
                        modifier = Modifier

                            .padding(horizontal = 0.dp)
                            .width(250.dp)


                    )}

                },
            )
        },
        bottomBar = {
            BottomAppBar(containerColor= getColor("Green3")) {
                NavigationBar(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = getColor("Green3")
                ) {
                    NavigationBarItem(
                        selected = selectedTab == "new",
                        label = { Text("Agregar") },
                        icon = { Icon(Icons.Filled.Star, contentDescription = null) },
                        onClick = {

                            navController2.navigate("new")
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
                        label = { Text("Mas Info") },
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
                composable("new") { NewData(navController2,userViewModel) }
                composable("result/{datosJson}") { backStackEntry ->
                    val datosJson = backStackEntry.arguments?.getString("datosJson") ?: ""
                    val datosMapa: Map<*, *>? = Gson().fromJson(datosJson, Map::class.java)
                    Result(navController2= navController2,data = datosMapa)
                }
                composable("register") { Register(navController2,userViewModel) }

                composable(
                    route = "updateWeight/{userId}"
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId")
                    val id = userId?.toIntOrNull()

                    if (id != null) {
                        UpdateWeightScreen(navController2 = navController2,userViewModel = userViewModel, userId = id)
                    }
                }


                composable("more") { More(navController2) }

            }

        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewData(navController2: NavHostController, userViewModel: UserViewModel){

    var nombre by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var pesoa by remember { mutableStateOf("") }

    fun guardarDatos() {
        val pesoAntes = convertDouble(pesoa)
        val newUser = User(
            firstName = nombre,
            cellphone = celular,
            beforew = pesoAntes,
            afterw = pesoAntes
        )
        userViewModel.addUser(newUser)
        navController2.navigate("register")
    }


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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){

            Text(text ="Nombre: ", fontSize = 15.sp, modifier = Modifier.size(70.dp,25.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = nombre,
                onValueChange = { nombre = it },
                colors = TextFieldDefaults.textFieldColors(containerColor = getColor("Green4")),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text= "Celular", fontSize = 15.sp, modifier = Modifier.size(70.dp,25.dp))

            TextField(
                value = celular,
                onValueChange = { input ->
                    // Filtrar el input para que solo contenga dígitos
                    val filteredInput = input.filter { it.isDigit() }
                    // Asignar el valor filtrado a la variable
                    celular = filteredInput
                },
                //label = { Text("Celular") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = TextFieldDefaults.textFieldColors(containerColor = getColor("Green4"))
            )

        }
//        Text(
//            text="Datos antes y despues de la actividad fisica:",
//            fontSize = 20.sp,
//            textAlign = TextAlign.Center
//        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text= "Peso (Kg)", fontSize = 15.sp,modifier = Modifier.size(70.dp,25.dp))
            TextField(
                value = pesoa,
                onValueChange = { input ->
                    // Filtrar para permitir solo dígitos y un solo punto decimal
                    val filteredInput = input.filter { it.isDigit() || it == '.' }
                    // Verificar si hay más de un punto decimal
                    if (filteredInput.count { it == '.' } <= 1) {
                        pesoa = filteredInput
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = getColor("Green4")),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { guardarDatos() })

            )
        }
        Button(
            onClick = {
                guardarDatos()
            },
            enabled = nombre != "" && celular != "" && pesoa != "",
            colors = ButtonDefaults.buttonColors(
                getColor("Green1")
            )
        ) {

            Text(text = "Guardar ", fontSize = 18.sp)
        }


    }
}
// esta funcion me da el numero ya convertido
fun convertDouble(numero:String): Double {
    return numero.toDoubleOrNull() ?: 0.0

}

//esta funcion retorna la variable dandole la clave- key de un json map
fun verify(data: Map<*, *>?, key : String): String {
    return data?.get(key) as? String ?: ""
}
@Composable
fun Result(navController2: NavHostController,data: Map<*, *>?){


    val nombre = verify(data= data, key = "nombre")
    val celular = verify(data= data, key = "celular")
    val peso1 = verify(data= data, key = "pesoa")
    val peso2 = verify(data= data, key = "pesob")
    // convertir peso a double
    val peso1D = convertDouble(peso1)
    val peso2D = convertDouble(peso2)


    //((peso inicial- peso final)/ peso inicial)*100
    val indicep : Double = ((peso1D - peso2D)/peso1D)*100.00F
    val indice2 = (Math.round(indicep*100))/100.00F


    //Text("$nombre , $celular , $peso1D , $peso2D, indice de perdida de peso en $indice2 %")
    val perdida: String = when {
        indice2 < 0.0 -> "Incorrecto"
        indice2 in 0.0.. 0.99 -> "0-1%"
        indice2 in 1.0..2.0 -> "1-2%"
        else -> " mayor al 2%"
    }




    val recomendacion: String = when (perdida) {
        "0-1%" -> {
            "Pérdida de peso dentro de lo esperable, sin impacto sobre el rendimiento."
        }
        "1-2%" -> {
            "Pérdida de peso aceptable, con impacto leve sobre el rendimiento"
        }
        else -> {
            "Pérdida de peso desaconsejada, con impacto negativo sobre el rendimiento"
        }
    }


    if ( perdida!= "Incorrecto"){
        Column {
            Text(text = "Tu perdida de peso fue de:", fontSize = 25.sp, textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            Text(text = "$indice2%", fontSize = 27.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth())
            Text(text="$nombre: Te encuentras dentro del $perdida", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(
                    when (perdida) {
                    "0-1%" -> {
                        R.drawable.resulthigh

                    }
                    "1-2%" -> {
                        R.drawable.resultmid
                    }
                    else -> {
                        R.drawable.resultlow

                    }
                }
                ),
                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp, 200.dp),
                alignment = Alignment.Center

            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text= recomendacion, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { navController2.navigate("more") },
                    colors = ButtonDefaults.buttonColors(getColor("Green1"))
                ) {
                    Text("Seguir ➜")

                }
            }






        }

    } else
    {
        Column {

            Text("Recuerda que el peso inicial es mayor al peso despues de la actividad fisica")
        }
    }



}

@Composable
fun Register(navController2: NavHostController,userViewModel: UserViewModel) {
    // Observar los datos de la base de datos como un estado
    val userList by userViewModel.readAllData.observeAsState(initial = emptyList())
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            //jacer esto scrollabl4
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registros",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de usuarios
        if (userList.isEmpty()) {
            Text("No hay registros disponibles.")
        } else {
            userList.forEach { user ->
                UserRow(user= user, navController2 = navController2)

            }
        }
    }
}

@Composable
fun UserRow(user: User, navController2: NavHostController) {
    val userId = user.id
    var backgroundC = Color.Black

    if (user.edited){
        backgroundC = getColor("Green1")
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            //.background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Button(
            onClick = {
                navController2.navigate("updateWeight/$userId")
            },
            colors = ButtonDefaults.buttonColors(backgroundC),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = user.firstName,
                    modifier = Modifier
                        .sizeIn(maxWidth = 150.dp)
                    , fontSize = 16.sp, softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(horizontalAlignment = Alignment.End) {
                    //Text(text = "$userId", fontSize = 12.sp, softWrap = false, textAlign = TextAlign.End)
                    Text(text = user.cellphone, fontSize = 12.sp, softWrap = false, textAlign = TextAlign.End)
                    Text(text = "${user.beforew} kg", fontSize = 12.sp, softWrap = false, textAlign = TextAlign.End)
                    //Text(text = "${user.edited} kg", fontSize = 12.sp, softWrap = false, textAlign = TextAlign.End)

                }


            }


        }
//            Button(onClick = {}) {
//                Text("Nombre: ${user.firstName}")
//                Text("Celular: ${user.cellphone}")
//                Text("Peso Inicial: ${user.beforew} kg")
//                Text("Peso Final: ${user.afterw} kg")
//
//            }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateWeightScreen(navController2: NavHostController,userViewModel: UserViewModel, userId: Int) {
    // Observar los datos del usuario
    val user by userViewModel.getUserById(userId).observeAsState()

    var alert by remember { mutableStateOf("") }
    var newWeight by remember { mutableStateOf("") }

    fun updateData(newWeight: String, user:User?){

        val weightfinal = newWeight.toDoubleOrNull()


        if (user != null) {
            if (weightfinal != null && weightfinal <= user.beforew) {
                userViewModel.updateAfterWeight(userId, weightfinal)
                userViewModel.updateEdited(userId, true)

                val data = mutableMapOf(
                    "nombre" to user.firstName,
                    "celular" to user.cellphone,
                    "pesoa" to "${user.beforew}",
                    "pesob" to newWeight
                )
                val datosJson = Gson().toJson(data)
                navController2.navigate("result/$datosJson")
            } else {
                alert =
                    "El peso actual no debe ser mayor que el peso inicial ... a menos que hayas ido a comer"

            }
        }
    }




    // Mostrar los datos del usuario solo si están disponibles
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        user?.let { it ->



            Text(text = it.firstName, textAlign = TextAlign.Center, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = it.cellphone, fontSize = 20.sp)
            Text(text = "${it.beforew} kg", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Input para el nuevo peso
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text= "Peso Final (Kg)", fontSize = 15.sp,modifier = Modifier.size(105.dp,25.dp))
                TextField(
                    value = newWeight,
                    onValueChange = { input ->
                        // Filtrar para permitir solo dígitos y un solo punto decimal
                        val filteredInput = input.filter { it.isDigit() || it == '.' }
                        // Verificar si hay más de un punto decimal
                        if (filteredInput.count { it == '.' } <= 1) {
                            newWeight = filteredInput
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = getColor("Green4")) ,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { updateData(newWeight, it) })

                )

            }

             if (newWeight ==""){
                 alert = ""
             }
            Text(text = alert, color =  Color.Red)


            Button(
                colors = ButtonDefaults.buttonColors(
                getColor("Green1")
            ),
                onClick = {
                    updateData(newWeight,user)
                },
                enabled = newWeight != "",
            )
            {
                Text("Calcular")
            }

        } ?: run {
            // Mostrar un mensaje si no se encuentra el usuario
            Text("Usuario no encontrado. Id: $userId")
            
        }
    }
}


@Composable
fun More(navController2: NavHostController){

    Column {

        Text(text = "Transformando vidas a través de la alimentación consciente", fontSize = 20.sp, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Siguenos en Instagram!!", fontSize = 20.sp, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(R.drawable.arrowdown),
            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth()
                .size(20.dp, 20.dp),
            alignment = Alignment.Center

        )
        Image(
            painter = painterResource(R.drawable.arrowdown),
            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth()
                .size(20.dp, 20.dp),
            alignment = Alignment.Center

        )
        Spacer(modifier = Modifier.height(10.dp))


        Image(
            painter = painterResource(R.drawable.codigoqr),
            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp, 300.dp),
            alignment = Alignment.Center

        )
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = { navController2.navigate("new") },
                colors = ButtonDefaults.buttonColors(getColor("Green1"))
            ) {
                Text("Terminar tour ")

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(){
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            Column(
                modifier = Modifier
                    .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)

            ){
                //Spacer(modifier = Modifier.height(50.dp))
                Image(
                    painter = painterResource(R.drawable.logogreen),
                    contentDescription = "logo app",
                    modifier = Modifier
                        .size(180.dp, 180.dp)
                    //contentScale = ContentScale.FillWidth

                )
                Text(
                    text = "Bienvenido",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                //Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Empieza tu prueba, se tomarán unos datos y evaluaremos tu rendimiento",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                //Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "TRAVIN Transformando vidas a través de la alimentación consciente",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = {

                    },

                    colors = ButtonDefaults.buttonColors(
                        getColor("Green1")

                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    ) {
                    Text(text = "Empezar", fontSize = 18.sp, color = Color.Black)
                }
                //Spacer(modifier = Modifier.height(32.dp))

                //Spacer(modifier = Modifier.height(100.dp))



            }
        }


    }


}
@Preview(showBackground = true)
@Composable
fun Nowpreview(){
    Test()
}