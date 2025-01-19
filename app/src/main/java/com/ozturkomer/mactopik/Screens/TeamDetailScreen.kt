package com.ozturkomer.mactopik.Screens

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ozturkomer.mactopik.components.FixtureIcon
import com.ozturkomer.mactopik.components.LineUpIcon
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.repostitory.TeamDetailViewModel
import com.ozturkomer.mactopik.utils.Fixture
import com.ozturkomer.mactopik.utils.Player

@Composable
fun TeamDetailScreen(viewModel: TeamDetailViewModel = viewModel(), teamId: String) {
    val fixtures = viewModel.fixtures.collectAsState().value
    val players = viewModel.players.collectAsState().value
    val isLoadingPlayers = viewModel.isLoadingPlayer.collectAsState().value
    val isLoadingFixtures = viewModel.isLoadingFixtures.collectAsState().value
    var selectedTab by remember { mutableStateOf("Fikstür") } // Seçilen sekmeyi takip eden durum

    LaunchedEffect(teamId) {
        viewModel.fetchPlayers(teamId)
        viewModel.fetchFixtures(teamId)
    }

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
                .padding(16.dp)
                .animateContentSize() // Geçişler için animasyon
        ) {
            when (selectedTab) {
                "Fikstür" -> FixtureContent(id = teamId, fixtures = fixtures,isLoadingFixtures)
                "Oyuncular" -> PlayersContent(id = teamId, players = players,isLoadingPlayers)
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
            .padding(horizontal = 5.dp)
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
            .padding(16.dp)
    ) {
        Text(
            text = "Fikstür Detayları (ID: ${fixtures.size})",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (isLoading) {
            Loading()
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(fixtures) { fixture ->
                    Card(
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
                    }
                }
            }

        }
    }
}

@Composable
fun PlayersContent(id: String, players: List<Player>, isLoading: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(16.dp)
    ) {
        Text(
            text = "Oyuncu Detayları (ID: $id)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (isLoading) {
            Loading()
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
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
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = player.name,
                                        maxLines = 1,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "ID: ${player.id}",
                                        fontSize = 14.sp,
                                        color = Color.Gray
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

