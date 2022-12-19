package com.example.jetpackcomposecalagoii

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecalagoii.model.Futbollers

@Composable
fun FutbollersView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        items(getFootbollers()) { footboller ->
            ItemFootboler(futbollers = footboller)
            {Toast.makeText(context, it.fubollerName, Toast.LENGTH_SHORT).show()}
        }
    }
}

@Composable
fun ItemFootboler(futbollers: Futbollers, onItemSelected: (Futbollers) -> Unit) {
    Card(
        border = BorderStroke(
            2.dp, Color.Green
        ),
        modifier = Modifier
            .width(200.dp)
            .clickable { onItemSelected(futbollers) }
    ) {
        Column() {
            Image(
                painter = painterResource(id = futbollers.photo),
                contentDescription = "Foto Futbolista",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = futbollers.fubollerName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = futbollers.footballTeam,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = futbollers.bestChampion,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 18.sp
            )
        }
    }
}

fun getFootbollers(): List<Futbollers> {
    return listOf(
        Futbollers("Messi", "PSG", "WorldChampions", R.drawable.messi1),
        Futbollers("Xavi", "F.C. Barcelona", "WorldChampions", R.drawable.xavi1),
        Futbollers("Iniesta", "F.C. Barcelona", "WorldChampions", R.drawable.iniesta)
    )
}
