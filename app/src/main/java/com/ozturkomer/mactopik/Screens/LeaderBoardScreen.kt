package com.ozturkomer.mactopik.Screens


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
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
import coil.compose.rememberAsyncImagePainter
import com.ozturkomer.mactopik.components.Leaderboard
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.repostitory.StandingsViewModel
import com.ozturkomer.mactopik.utils.Standing

@Composable
fun LeaderBoardScreen() {
    val viewModel = StandingsViewModel()
    StandingsScreen(viewModel)

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StandingsScreen(viewModel: StandingsViewModel) {
    val standings = viewModel.standings.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val swipeRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = { viewModel.fetchStandings() }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                "Puan Durumu",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB2C036),
                textAlign = TextAlign.Center
            )
            Icon(
                Leaderboard, "LeaderBoard",
                tint = Color(0xFFB2C036),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(state = swipeRefreshState)
        )
        {

            if (!isLoading) {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 0.dp)
                ) {
                    items(standings) { standing ->
                        StandingCard(standing)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

            } else {
                Loading()
            }
        }
    }
}

@Composable
fun StandingCard(standing: Standing) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${standing.position}.",
                fontSize = 20.sp,
                modifier = Modifier.width(32.dp),
                textAlign = TextAlign.Center
            )

            Image(
                painter = rememberAsyncImagePainter(standing.logo),
                contentDescription = "${standing.team} Logo",
                modifier = Modifier.size(35.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = standing.team, fontSize = 16.sp, color = Color.Black)
                Text(
                    text = "Oynanan: ${standing.played}, Kazanılan: ${standing.won}, Beraberlik: ${standing.drawn}, Kaybedilen: ${standing.lost}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = "${standing.points}", fontSize = 16.sp, color = Color.Black)
                Text(
                    text = "Gol: +${standing.goalsFor} / -${standing.goalsAgainst} (${standing.goalDifference})",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}