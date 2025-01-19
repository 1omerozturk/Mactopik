package com.ozturkomer.mactopik.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.repostitory.TeamViewModel
import com.ozturkomer.mactopik.utils.Teams

@Composable
fun TeamsScreen(navController: NavHostController) {
    val viewModel = TeamViewModel()
    Teams(viewModel,navController)
}

@Composable
fun Teams(viewModel: TeamViewModel, navController: NavHostController) {
    val teams = viewModel.teams.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Takımlar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Icon(
                painter = rememberVectorPainter(Icons.Default.List),
                contentDescription = "Teams List",
                tint = Color.Gray,
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (!isLoading) {
            LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
                items(teams.chunked(2)) { pair ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        pair.forEach { team ->
                            TeamCard(
                                team = team,
                                navController = navController, // NavController gönderiliyor.
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (pair.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        } else {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}



@Composable
fun TeamCard(team: Teams, navController: NavHostController, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = modifier
            .padding(6.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
            .padding(2.dp)
            .clickable {
                navController.navigate("card_detail/${team.id}") // Parametre olarak ID gönderiliyor.
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(team.logo),
                contentDescription = "${team.name} Logo",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (team.name.count { it == ' ' } >= 1) {
                    team.name.split(" ").takeLast(2).joinToString(" ")
                } else {
                    team.name
                },
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}



