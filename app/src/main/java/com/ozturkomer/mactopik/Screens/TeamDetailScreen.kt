package com.ozturkomer.mactopik.Screens

import android.widget.Toast
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ozturkomer.mactopik.components.FixtureIcon
import com.ozturkomer.mactopik.components.LineUpIcon
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.components.Shirt
import com.ozturkomer.mactopik.components.UserCircle
import com.ozturkomer.mactopik.repostitory.TeamDetailViewModel
import com.ozturkomer.mactopik.repostitory.TeamDetailViewModelFactory
import com.ozturkomer.mactopik.utils.Fixture
import com.ozturkomer.mactopik.utils.Match
import com.ozturkomer.mactopik.utils.Player

@Composable
fun TeamDetailScreen(teamId: String) {
    val viewModel: TeamDetailViewModel = viewModel(
        factory = TeamDetailViewModelFactory(teamId)
    )
    val fixtures = viewModel.fixtures.collectAsState().value
    val players = viewModel.players.collectAsState().value
    val isLoadingPlayers = viewModel.isLoadingPlayer.collectAsState().value
    val isLoadingFixtures = viewModel.isLoadingFixtures.collectAsState().value
    var selectedTab by remember { mutableStateOf("Fikstür") } // Seçilen sekmeyi takip eden durum


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        // Başlıklar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabItem(
                title = "Fikstür",
                isSelected = selectedTab == "Fikstür",
                icon = FixtureIcon,
                onClick = { selectedTab = "Fikstür" }
            )
            TabItem(
                title = "Oyuncular",
                isSelected = selectedTab == "Oyuncular",
                icon = LineUpIcon,
                onClick = { selectedTab = "Oyuncular" }
            )
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical=16.dp)
                .animateContentSize() // Geçişler için animasyon
        ) {
            when (selectedTab) {
                "Fikstür" -> FixtureContent(id = teamId, fixtures = fixtures, isLoadingFixtures)
                "Oyuncular" -> PlayersContent(id = teamId, players = players, isLoadingPlayers)
            }
        }
    }
}

@Composable
fun TabItem(
    title: String,
    isSelected: Boolean,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        backgroundColor = if (isSelected) Color.Black else Color.White,
        modifier = Modifier
            .width(200.dp)
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = if (isSelected) Color.White else Color.Gray,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun FixtureContent(id: String, fixtures: List<Fixture>, isLoading: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(vertical = 16.dp)
    ) {
        if (isLoading) {
            Loading()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(fixtures) { fixture ->
                    FixtureCard(fixture)
                    Spacer(modifier = Modifier.height(5.dp))

                    /* Card(
                         shape = RoundedCornerShape(8.dp),
                         backgroundColor = Color.White,
                         elevation = 4.dp,
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(vertical = 4.dp)
                     ) {
                         Column(
                             modifier = Modifier.padding(16.dp)
                         ) {
                             Text(
                                 text = "${fixture.home} vs ${fixture.away}",
                                 fontSize = 16.sp,
                                 fontWeight = FontWeight.Bold,
                                 color = Color.Black
                             )
                             Text(
                                 text = "Tarih: ${fixture.date}",
                                 fontSize = 14.sp,
                                 color = Color.Gray
                             )
                             Text(
                                 text = "Sonuç: ${fixture.result}",
                                 fontSize = 14.sp,
                                 color = Color.Gray
                             )
                             Text(
                                 text = "Organizasyon: ${fixture.organization}",
                                 fontSize = 14.sp,
                                 color = Color.Gray
                             )
                         }
                     }*/
                }
            }

        }
    }
}

@Composable
fun PlayersContent(id: String, players: List<Player>, isLoading: Boolean) {
    val context= LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(vertical = 20.dp)
    ) {
        if (isLoading) {
            Loading()
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(bottom = 25.dp)
            ) {
                items(players.chunked(2)) { pair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        pair.forEach { player ->
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                backgroundColor = Color.White,
                                elevation = 4.dp,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .clickable { Toast.makeText(context,player.name,Toast.LENGTH_SHORT).show() }
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Icon(Shirt, "usericon", tint = Color.Black, modifier = Modifier.size(60.dp))
                                    Text(
                                        text = player.name,
                                        maxLines = 1,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                        // Eğer son sıradaki grup eksikse boşluk bırakır.
                        if (pair.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FixtureCard(fixture: Fixture) {
    val context= LocalContext.current
//    val result = fixture.result.split("-")
//    val homeScore = result[0].toIntOrNull() ?: 0
//    val awayScore = result[1].toIntOrNull() ?: 0
//    val color = when {
//        homeScore > awayScore -> Color.Green
//        homeScore < awayScore -> Color.Red
//        else -> Color.Gray
//    }

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(20))
            .padding(vertical = 2.dp)
            .clickable { Toast.makeText(context,fixture.organization,Toast.LENGTH_SHORT).show() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    overflow = TextOverflow.Ellipsis,
                    text = fixture.home, fontSize = 14.sp,
                )

            }

            Text(
                text = fixture.result,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
            )


            Spacer(modifier = Modifier.width(12.dp))



            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = fixture.away,
                    fontSize = 14.sp,
                )

            }
        }
        MatchDateText(matchDate = fixture.date)
        Spacer(modifier = Modifier.height(5.dp))

    }
}