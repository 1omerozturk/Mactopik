package com.ozturkomer.mactopik.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ozturkomer.mactopik.components.Loading
import com.ozturkomer.mactopik.components.Sports_and_outdoors
import com.ozturkomer.mactopik.repostitory.MatchViewModel
import com.ozturkomer.mactopik.utils.Match
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen() {
    val viewModel = MatchViewModel()
    MatchScreen(viewModel)
}

@Composable
fun MatchScreen(viewModel: MatchViewModel) {
    val matches = viewModel.matches.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
         Text("Maçlar", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray, textAlign = TextAlign.Center)
        Icon(Sports_and_outdoors,"Ball", tint = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .size(50.dp)
        )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if(!isLoading){

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)) {
            items(matches) { match ->
                MatchCard(match)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        }
        else{
            Loading()
        }
    }
}

@Composable
fun MatchCard(match: Match) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(20))
            .padding(vertical = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(match.homeLogo),
                contentDescription = "Home Logo",
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(maxLines = 1, overflow = TextOverflow.Ellipsis,
                    text = match.homeTeam, fontSize = 14.sp)

                  }

            Text(text = "-", fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, color = Color.Gray)


            Spacer(modifier = Modifier.width(12.dp))


                Image(
                    painter = rememberAsyncImagePainter(match.awayLogo),
                    contentDescription = "Away Logo",
                    modifier = Modifier.size(30.dp),
                    contentScale = ContentScale.Crop
                )
            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(maxLines = 1, overflow = TextOverflow.Ellipsis,text = match.awayTeam, fontSize = 14.sp)

            }
        }
        MatchDateText(matchDate = match.date, matchTime = match.time)
        Spacer(modifier = Modifier.height(5.dp))

    }
}

@Composable
fun MatchDateText(matchDate: String, matchTime: String) {

    // Mevcut tarih bilgisi
    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)

    // Tarih formatı (match.date ile uyumlu olacak şekilde güncellenmeli)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Örneğin: "2024-01-15"
    val matchLocalDate = try {
        LocalDate.parse(matchDate, formatter)
    } catch (e: Exception) {
        null // Geçersiz tarih formatı durumunda
    }

    // Tarihi kontrol et
    val displayDate = when (matchLocalDate) {
        today -> "Bugün"
        tomorrow -> "Yarın"
        else -> matchDate // Orijinal tarih
    }

    // Metni göster
    Text(
        text = "$displayDate - $matchTime",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier.padding(top = 2.dp)
    )
}
