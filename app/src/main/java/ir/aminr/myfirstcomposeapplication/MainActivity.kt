package ir.aminr.myfirstcomposeapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ContentView()
        }
    }
}

@Composable
fun ContentView() {
    AnimationCompose()
}

@Composable
fun TextStyle() {
    val fontFamily = FontFamily(
        Font(R.font.font, FontWeight.Light),
        Font(R.font.bold_font, FontWeight.Bold),
    )

    Text(
        text = buildAnnotatedString {
            append("Hello!")
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 22.sp,
                )
            ) {
                append("World!")
            }
            append("Amin :)")

        },
        color = Color.White,
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun AnimationCompose() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }

    val size by animateDpAsState(
        targetValue = sizeState,
//        tween(
//            durationMillis = 2000,
//            delayMillis = 300,
//            easing = FastOutLinearInEasing
//        )

        spring(
            Spring.DampingRatioMediumBouncy
        )
//        keyframes {
//            durationMillis = 5000
//            sizeState at 0 with LinearEasing
//            sizeState * 1.5f at 1000 with FastOutLinearInEasing
//            sizeState * 2f at 5000
//        }
    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text(text = "Increase Size")
        }
    }
}

@Composable
fun SideEffect(backPressedDispatcher: OnBackPressedDispatcher) {
    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //do something
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}


@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                )
            }

        }
    }

}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier
) {

    val color = remember {
        mutableStateOf(Color.Green)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color.value)
        .clickable {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        })
}

@Composable
fun ShowEditText() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {

        var textFieldState by remember {
            mutableStateOf("")
        }

        val scope = rememberCoroutineScope()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            TextField(
                value = textFieldState,
                label = { Text(text = "Enter your name") },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "Hello $textFieldState"
                        )
                    }
                }) {
                    Text(text = "Say Hello")
                }
            }

        }

    }

}

@Composable
fun ShowList() {
    val fontFamily = FontFamily(
        Font(R.font.bold_font, FontWeight.Bold),
        Font(R.font.font, FontWeight.Light),
    )

    LazyColumn() {

        itemsIndexed(
            listOf(
                "This", "is", "jetpack", "compose"
            )
        ) { index, currentItem ->
            Box(modifier = Modifier.clickable {
                Log.i("MYLOG", "ShowList: $index")
            }) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 32.sp,
                                fontFamily = fontFamily,
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(currentItem)
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 22.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Light
                            )
                        ) {
                            append(" with index of $index")
                        }

                    },
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun ConstraintLayout() {
    val constraint = ConstraintSet {

        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")

        constrain(greenBox) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        createHorizontalChain(
            greenBox,
            redBox,
            chainStyle = ChainStyle.Packed
        )
    }

    androidx.constraintlayout.compose.ConstraintLayout(
        constraint,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Green)
                .layoutId("greenBox")
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .layoutId("redBox")
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface {
        ContentView()
    }
}
