package com.example.jetpackcomposecalagoii

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecalagoii.model.Superhero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    val myList = listOf<String>("Jose", "Manuel", "Elena", "Adrian", "Aitana")
    LazyColumn {
        item { Text(text = "Primer Item") }
        items(7) {
            Text(text = "Este es el iten $it")
        }
        items(myList) {
            Text(text = "Hola me llamo $it")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero)
            { Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show() }
        }
    })

}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero)
            { Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show() }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superhero = getSuperheroes().groupBy { it.publisher }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {

        superhero.forEach { (publisher, mySuperhero) ->
            stickyHeader {
                Text(
                    text = publisher, modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White

                )
            }
            items(mySuperhero) { superhero ->
                ItemHero(superhero = superhero)
                { Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show() }
            }
        }


    }
}

@Composable
fun SuperHeroSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScope = rememberCoroutineScope()
    Column {
        LazyColumn(
            state = rvState, verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheroes()) { superhero ->
                ItemHero(superhero = superhero)
                { Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show() }
            }
        }

        val showbutton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        if (showbutton) {
            Button(
                onClick = {
                    coroutinesScope.launch {
                        rvState.animateScrollToItem(0)
                    }

                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Soy un boton cool")
            }
        }


    }

}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(superhero) }
            .padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "SuperHero Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.reaName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(4.dp)
            )
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero("Spiderman", "Peter Parker", "marvel", R.drawable.spiderman),
        Superhero("wolverine", "Logan", "Marvel", R.drawable.logan),
        Superhero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        Superhero("Thor", " thor Son Odin", "Marvel", R.drawable.thor),
        Superhero("Flash", "Barry Allen", "DC", R.drawable.flash),
        Superhero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        Superhero("Wonder Woman", "Princes Diana", "DC", R.drawable.wonder_woman)
    )
}