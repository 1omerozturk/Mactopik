package com.ozturkomer.mactopik.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.components.Scoreboard
import com.ozturkomer.mactopik.repostitory.MatchViewModel
import com.ozturkomer.mactopik.utils.Match

@Composable
fun MatchScreen(navController: NavHostController) {
    val viewModel = MatchViewModel()
    var selectedWeek by remember { mutableStateOf<String?>(null) }
    Column(modifier = Modifier.fillMaxSize()) {
        WeekInput { week ->
            selectedWeek = week
            viewModel.fetchMatches(week)
        }
        Matches(viewModel, navController)
    }
}

@Composable
fun WeekInput(onWeekSelected: (String) -> Unit) {
    val context = LocalContext.current

    var selectedNumber by remember { mutableStateOf("Hafta Seç") }
    val numberOptions = (1..34).map { it.toString() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Maçlar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3CBBFF),
                textAlign = TextAlign.Center
            )
            Icon(
                Scoreboard, "LeaderBoard",
                tint = Color(0xFF3CBBFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
            )
        }

        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxWidth(0.8f)) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = selectedNumber, color = Color.Black)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                numberOptions.forEach { number ->
                    DropdownMenuItem(
                        text = { Text(text = number + ". Hafta") },
                        onClick = {
                            selectedNumber = number
                            expanded = false
                            Toast.makeText(context, "$number. Hafta", Toast.LENGTH_SHORT).show()
                            onWeekSelected(number)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Matches(viewModel: MatchViewModel, navController: NavHostController) {
    val matches = viewModel.matches.collectAsState().value
    val week = viewModel.week.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val swipeRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = { viewModel.fetchMatches() }
    )
    var showSimulation by remember { mutableStateOf(false) }
    var selectedMatch by remember { mutableStateOf<Match?>(null) }

    fun handleSimulationEnd(finalScore: String) {
        showSimulation = false
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(Color.White)
            .pullRefresh(state = swipeRefreshState)
    ) {
        if (isLoading) {
            item {
                Loading()
            }
        } else {
            item {
                // Maçları tarihiyle gruplandır
                val groupedMatches = matches.groupBy { it.date }
                // Her tarihi ve maçlarını sırasıyla listele
                groupedMatches.forEach { (date, matchesOnDate) ->
                    // Tarih başlığını ekle
                    Text(
                        text = date,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .padding(top = 10.dp)
                    )
                    // Maçları tıklanabilir öğeler olarak ekle
                    matchesOnDate.forEachIndexed { index, match ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)
                                .clickable {
                                    // Her maçın sıraya göre tıklanabilirliğini sağlayın
                                    navController.navigate(
                                        "match_detail/${week}/${
                                            matches.indexOf(
                                                match
                                            )
                                        }"
                                    )
                                }
                                .padding(8.dp)
                        ) {
                            Text(
                                text = match.time,
                                color = Color.White,
                                modifier = Modifier.weight(0.75f)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(2f)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(match.homeLogo),
                                    contentDescription = match.homeTeam,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = match.homeTeam,
                                    color = Color.White,
                                    modifier = Modifier.weight(1.5f)
                                )
                            }
                            if (match.score == "0") {
                                Text(
                                    "--",
                                    color = Color.White,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .align(Alignment.CenterVertically)
                                )
                            } else {
                                Text(
                                    text = if (match.score == "1") "--" else match.score,
                                    color = if (match.score == "1") Color.Green else Color.White,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .weight(0.75f)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(2f)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(match.awayLogo),
                                    contentDescription = match.awayTeam,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = match.awayTeam,
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Show MatchSimulation component if showSimulation is true
    if (showSimulation && selectedMatch != null) {
        MatchSimulation(
            homeTeam = selectedMatch!!.homeTeam,
            awayTeam = selectedMatch!!.awayTeam,
            isOpen = showSimulation,
            onClose = { showSimulation = false },
            onSimulationEnd = { handleSimulationEnd(it) }
        )
    }

}

@Composable
fun MatchSimulation(
    homeTeam: String,
    awayTeam: String,
    isOpen: Boolean,
    onClose: () -> Unit,
    onSimulationEnd: (String) -> Unit
) {
    // Your MatchSimulation implementation here
}

data class Match(
    val date: String,
    val time: String,
    val homeTeam: String,
    val homeLogo: Int,
    val score: String,
    val awayTeam: String,
    val awayLogo: Int
)




