package com.ozturkomer.mactopik.Screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.components.Shield
import com.ozturkomer.mactopik.repostitory.TeamViewModel
import com.ozturkomer.mactopik.utils.Teams

@Composable
fun TeamsScreen(
    viewModel: TeamViewModel = TeamViewModel(),
    navController: NavHostController
) {
    Teams(viewModel, navController)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Teams(viewModel: TeamViewModel, navController: NavHostController) {
    val teams = viewModel.teams.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val swipeRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = { viewModel.fetchTeams() }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
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
                color = Color(0xFF2255FF),
                textAlign = TextAlign.Center
            )
            Icon(
                Shield, "LeaderBoard",
                tint = Color(0xFF2255FF),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
            )
        }


        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .pullRefresh(state = swipeRefreshState)
        ) {

            if (isLoading) {
                Loading()
            } else {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 0.dp)
                ) {
                    items(teams.chunked(2)) { pair ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            pair.forEach { team ->
                                TeamCard(
                                    team = team,
                                    navController = navController,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            if (pair.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }


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
                navController.navigate("team_detail/${team.id}") // 📌 Sadece Card'a clickable ekleyin
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