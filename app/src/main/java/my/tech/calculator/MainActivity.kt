package my.tech.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import my.tech.calculator.ui.screens.home.HomeScreen
import my.tech.calculator.ui.theme.MyTechCalculatorTheme

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTechCalculatorTheme {
                HomeScreen()
            }
        }
    }
}