package com.ozturkomer.mactopik.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ozturkomer.mactopik.repostitory.MatchViewModel


@Composable
fun MatchScreen() {
    val viewModel=MatchViewModel()
    Matches(viewModel)
}


@Composable
fun Matches(viewModel: MatchViewModel) {
    val matches=viewModel.matches.collectAsState().value
    var showSimulation by remember { mutableStateOf(false) }
    var selectedMatch by remember { mutableStateOf<Match?>(null) }
    val coroutineScope = rememberCoroutineScope()

    fun handleSimulationEnd(finalScore: String) {
        println("Simulation ended: $finalScore")
        showSimulation = false
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        // Group matches by date
        val groupedMatches = matches.groupBy { it.date }

        groupedMatches.forEach { (date, matchesOnDate) ->
            Text(
                text = date,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(8.dp).padding(top=10.dp,)
            )

            matchesOnDate.forEachIndexed { index, match ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (index % 2 == 0) Color(0xFF736D6D) else Color.Gray)
                        .clickable {
//                        selectedMatch = match
                            showSimulation = true
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
                        Text("--", color = Color.White, textAlign = TextAlign.Start, modifier = Modifier.weight(0.5f).align(Alignment.CenterVertically))
                       /* IconButton(onClick = {
//                        selectedMatch = match.score
                            showSimulation = true
                        }) {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Simulate Match", tint = Color.White, modifier = Modifier.size(10.dp))
                        }*/
                    } else {
                        Text(
                            text = if (match.score == "1") "--" else match.score,
                            color = if (match.score == "1") Color.Green else Color.White,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(0.75f).align(Alignment.CenterVertically)
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




