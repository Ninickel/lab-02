package com.example.listycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity.ui.theme.ListyCityTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cityRepository = CityRepository()
        setContent {
            ListyCityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CityListScreen(
                        cities = cityRepository.cities,
                        onAddCity = {cityRepository.addCity(it)},
                        onDelCity = {cityRepository.delCity(it)},
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun CityListScreen(cities: List<String>,
                   onAddCity: (String) -> Unit,
                   onDelCity: (String) -> Unit,
                   modifier: Modifier = Modifier
){
    var newCity by remember {mutableStateOf("")}
    Column(modifier = Modifier.padding(16.dp)){
        OutlinedTextField(
            value = newCity,
            onValueChange = {newCity = it},
            label = {Text("City Name")}
        )
        Row(){
            Button(onClick = {
                if(newCity.isNotBlank()) {
                    onAddCity(newCity)
                }
            }){
                Text("Add City")
            }
            Button(onClick = {
                if(newCity.isNotBlank()) {
                    onDelCity(newCity)
                }
            }){
                Text("Delete City")
            }
        }
        LazyColumn(modifier = modifier.fillMaxSize()) {

            items(cities){ city ->
                CityRow(city = city)
            }
        }
    }

}

@Composable
fun CityRow(city: String){
    Text(
        text = city,
        fontSize = 28.sp,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 14.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListyCityTheme {
        Greeting("Android")
    }
}