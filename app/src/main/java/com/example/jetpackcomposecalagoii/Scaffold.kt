package com.example.jetpackcomposecalagoii

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScaffoldExample() { // Es como view, como si fuera un contenedor
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppBar(onClickIcon = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Has pulsado $it"
                    )
                }
            }, onClickDrawer = {coroutineScope.launch { scaffoldState.drawerState.open() }})
        }, scaffoldState = scaffoldState,
        bottomBar = { MybottomNavigation() },
        floatingActionButton = { MyFAB() },
        drawerContent = { MyDrawer() },
        drawerGesturesEnabled = false
    ) {

    }
}

@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit,onClickDrawer:()->Unit {  // como una toolbar
    TopAppBar(
        title = { Text(text = "Mi primera toolbar") },
        backgroundColor = Color.Red,
        contentColor = Color.White, elevation = 4.dp, navigationIcon = {
            IconButton(onClick = {onClickDrawer }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Buscando") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = { onClickIcon("Soy una estrellita") }) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Back")
            }
        }
    )


}

@Composable
fun MybottomNavigation() {
    var index by remember { mutableStateOf(0) }
    BottomNavigation(backgroundColor = Color.Red, contentColor = Color.White) {
        BottomNavigationItem(selected = index == 0, onClick = { index = 0 }, icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home"
            )
        }, label = { Text(text = "Home") })



        BottomNavigationItem(selected = index == 1, onClick = { index = 1 }, icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite"
            )
        }, label = { Text(text = "FAV") })


        BottomNavigationItem(selected = index == 2, onClick = { index = 2 }, icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person"
            )
        }, label = { Text(text = "Person") })


    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(onClick = {}, backgroundColor = Color.Yellow, contentColor = Color.Black) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun MyDrawer() {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Primera opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Segunda opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Tercera opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(
            text = "Cuarta opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}
