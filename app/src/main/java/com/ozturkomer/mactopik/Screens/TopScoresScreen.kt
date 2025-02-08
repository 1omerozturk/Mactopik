package com.ozturkomer.mactopik.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ozturkomer.mactopik.components.Leaderboard
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.repostitory.TopScorerViewModel
import com.ozturkomer.mactopik.utils.Scorer

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopScoresScreen(viewModel: TopScorerViewModel = viewModel()) {
    val scorers = viewModel.scorers.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val swipeRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = { viewModel.fetchScorers() }
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
                "Gol Krallığı",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF3C3C),
                textAlign = TextAlign.Center
            )
            Icon(
                Leaderboard, "LeaderBoard",
                tint = Color(0xFFFF3C3C),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
            )
        }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
           Loading()
        } else {
            LazyColumn(
                modifier = Modifier.
                fillMaxSize()
                    .pullRefresh(state = swipeRefreshState)
                ,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(scorers) { scorer ->
                    TopScorerItem(scorer)
                }
            }
        }
    }
    }
}

@Composable
fun TopScorerItem(scorer: Scorer) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("${scorer.rank}.", fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(10.dp))
        AsyncImage(
            model = scorer.teamLogo,
            contentDescription = "Team Logo",
            modifier = Modifier
                .size(60.dp)
                .border(1.dp,Color.Black,shape = RoundedCornerShape(50.dp))
                .padding(5.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "${scorer.playerName}", fontWeight = FontWeight.Bold)
            Text(text = "${scorer.teamName}")

        }
            Text(text =  "⚽ ${scorer.goals}", fontWeight = FontWeight.Bold)
    }
}
