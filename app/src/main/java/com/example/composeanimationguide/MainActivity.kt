package com.example.composeanimationguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composeanimationguide.ui.theme.ComposeAnimationGuideTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationGuideTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    var isVisible by remember {
                        mutableStateOf(false)
                    }
                    var isRound by remember {
                        mutableStateOf(false)
                    }
                    Button(onClick = {
                        isVisible = !isVisible
                        isRound = !isRound
                    }) {
                        Text(text = "Toggle")
                    }
//                    val transition = updateTransition(
//                        targetState = isRound,
//                        label = null
//                    )
//                    val borderRadius by transition.animateInt(
//                        transitionSpec = { tween(2000) },
//                        label = "borderRadius",
//                        targetValueByState = { isRound ->
//                            if (isRound) 100 else 0
//                        }
//                    )
//                    val color by transition.animateColor(
//                        transitionSpec = { tween(1000) },
//                        label = "color",
//                        targetValueByState = { isRound ->
//                            if (isRound) Color.Green else Color.Red
//                        }
//                    )
//                    val borderRadius by animateIntAsState(
////                        targetValue = if (isRound) 100 else 0,
//                        targetValue = if (isRound) 40 else 20,
////                        animationSpec = tween(
////                            durationMillis = 3000,
////                            delayMillis = 500
////                        )
//                        animationSpec = spring(
//                            dampingRatio = Spring.DampingRatioHighBouncy,
//                            stiffness = Spring.StiffnessVeryLow
//                        )
//                    )

//                    val transition = rememberInfiniteTransition()
//                    val color by transition.animateColor(
//                        initialValue = Color.Red,
//                        targetValue = Color.Green,
//                        animationSpec = infiniteRepeatable(
//                            animation = tween(2000),
//                            repeatMode = RepeatMode.Reverse
//                        )
//                    )
//                    Box(
//                        modifier = Modifier
//                            .size(200.dp)
////                            .clip(RoundedCornerShape(borderRadius))
////                            .background(
////                                Color.Red
////                            )
//                            .background(
//                                color
//                            )
//                    )
//                    AnimatedVisibility(
//                        visible = isVisible,
//                        // enter and exit to change the animation of enter and exit
//                        enter =
//                        slideInHorizontally() + fadeIn(),
////                        exit = ,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//                        Box(modifier = Modifier.background(Color.Red))
//                    }

                    AnimatedContent(
                        targetState = isVisible,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        content = { isVisible ->
                            if (isVisible) {
                                Box(modifier = Modifier.background(Color.Green))
                            } else {
                                Box(modifier = Modifier.background(Color.Red))
                            }
                        },
                        transitionSpec = {
                            // enter animation + exit animation
//                            fadeIn() with fadeOut()
                            slideInHorizontally(
                                initialOffsetX = {
//                                    -it /2
                                    if(isVisible)it else -it
                                }
                            ) with slideOutHorizontally(
                                targetOffsetX = {
//                                    it/2
                                    if(isVisible) -it else it
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}