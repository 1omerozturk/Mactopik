package com.ozturkomer.mactopik.Screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.repostitory.MatchDetailViewModel
import com.ozturkomer.mactopik.utils.MatchDetail
import com.ozturkomer.mactopik.utils.Team

@Composable
fun MatchDetailScreen(navController:NavHostController, week: String, id: Int) {
    val viewModel = remember { MatchDetailViewModel(week, id) }
    val matchDetail = viewModel.details.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Loading()
        }
    } else {
        matchDetail?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                IconButton(onClick = { navController.navigate("main") }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                MatchDetailView(it)
            }
        }
    }
}

@Composable
fun MatchDetailView(matchDetail: MatchDetail) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TeamLogo(matchDetail.team1)
            Text(text = matchDetail.score, style = MaterialTheme.typography.h5, modifier = Modifier.padding(horizontal = 16.dp))
            TeamLogo(matchDetail.team2)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GoalsSection("Goals", matchDetail)
    }
}

@Composable
fun GoalsSection(title: String, matchDetail: MatchDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (matchDetail.goals.isNotEmpty()) {
                GoalList(matchDetail.goals[0], Modifier.weight(1f))  // İlk takımın golleri
                if (matchDetail.goals.size > 1) {
                    GoalList(matchDetail.goals[1], Modifier.weight(1f))  // İkinci takımın golleri
                }
            }
        }
    }
}


@Composable
fun GoalList(goals: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(2.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            goals.split("(F)", "(H)","(K)","(P)","(S)")
                .filter { it.isNotBlank() }
                .forEach { goal ->
                    val parts = goal.split(",")
                    if (parts.size >= 2) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = parts[0].trim(),
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(0.7f)
                            )
                            Text(
                                text = parts[1].trim(),
                                style = MaterialTheme.typography.body2.copy(androidx.compose.ui.graphics.Color.Gray),
                                modifier = Modifier.weight(0.3f)
                            )
                        }
                        Divider(color = androidx.compose.ui.graphics.Color.LightGray, thickness = 0.5.dp)
                    } else {
                        Text(
                            text = goal.trim(),
                            modifier = Modifier.padding(4.dp),
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
        }
    }
}


@Composable
fun TeamLogo(team: Team) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = team.logo,
            contentDescription = team.name,
            modifier = Modifier.size(64.dp)
        )
        Text(text = team.name, style = MaterialTheme.typography.body1, modifier = Modifier.padding(top = 4.dp))
    }
}
