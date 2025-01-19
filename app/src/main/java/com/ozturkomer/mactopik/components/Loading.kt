package com.ozturkomer.mactopik.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.fillMaxWidth().padding(148.dp),
        color = Color.Gray,
        strokeWidth = 4.dp

    )
}