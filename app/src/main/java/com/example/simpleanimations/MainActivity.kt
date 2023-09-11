package com.example.simpleanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpleanimations.ui.theme.SimpleAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State to represent the current size of the box
            var sizeState by remember {
                mutableStateOf(200.dp)
            }
            // To increase smoother.
            val size by animateDpAsState(
                targetValue = sizeState,
                // tween curves the length of the animation
                tween(
                    durationMillis = 3000,
//                    delayMillis = 300,
//                    easing = LinearOutSlowInEasing
                )
                // bouncing animation
//                spring(
//                    Spring.DampingRatioHighBouncy
//                )
                // keyframes to control on a lower level
//                keyframes {
//                    durationMillis = 5000
//                    // at the first milisecond assign our sizeState to the size of the box
//                    sizeState at 0 with LinearEasing
//                    sizeState * 1.5f at 1000 with FastOutLinearInEasing
//                    sizeState * 2f at 5000
//                }
            )
            val infiniteTransition = rememberInfiniteTransition()
            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    tween(2000),
                    repeatMode = RepeatMode.Reverse
                )
            )
            Box(modifier = Modifier
                .size(size)
                .background(color),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { sizeState += 50.dp }) {
                    Text("Increase Size")
                }
            }
        }
    }
}
