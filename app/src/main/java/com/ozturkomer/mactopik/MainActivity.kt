package com.ozturkomer.mactopik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ozturkomer.mactopik.BottomNavigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContent{
        val navController= rememberNavController()
        Navigation(navController)
    }
}
}
