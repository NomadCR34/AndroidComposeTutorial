package ir.aminr.myfirstcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import ir.aminr.myfirstcomposeapplication.ui.theme.MyFirstComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            baseContent()
        }
    }
}


@Composable
fun exampleOfAlign() {
    Column() {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(200.dp)
                .border(
                    border = BorderStroke(
                        1.dp,
                        Color.Gray
                    )
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Item1!",
                Modifier
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                text = "Item2!",
                Modifier
                    .align(Alignment.CenterHorizontally),
            )
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(200.dp)
                .width(200.dp)
                .border(
                    border = BorderStroke(
                        1.dp,
                        Color.Gray
                    )
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Item1!",
                Modifier
                    .align(Alignment.CenterVertically),
            )
        }
    }

}

@Composable
fun baseContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                Color(0xFFf2f2f2)
            )
    ) {


        item {
            Column() {
                ContextCompat.getDrawable(
                    LocalContext.current,
                    R.drawable.happy_meal_small
                )?.let {
                    Image(
                        bitmap = it.toBitmap().asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Happy Meal",
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            color = Color(0xFF3f3f3f)
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 16.dp, end = 16.dp)
                    )

                    Text(
                        text = "$5.99",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 16.dp, end = 16.dp),
                        style = TextStyle(
                            color = Color(0xFF85bb65)
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "800 calories",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp,end = 16.dp)

                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Order Now")
                }


            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyFirstComposeApplicationTheme {
        baseContent()
    }
}