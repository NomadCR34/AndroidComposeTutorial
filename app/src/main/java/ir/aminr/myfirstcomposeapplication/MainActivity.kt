package ir.aminr.myfirstcomposeapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Blue)
            .fillMaxHeight(0.5f)
            .width(300.dp)
            .border(2.dp, Color.Magenta )
            .padding(8.dp)
            .border(5.dp, Color.White)
    ) {
        Text(
            text = "Hello",
            modifier = Modifier
                .padding(8.dp),
            color = colorResource(id = R.color.white)
        )

        Spacer(modifier = Modifier.height(52.dp))

        Text(
            text = "World",
            modifier = Modifier
                .offset(8.dp, 20.dp),
            color = colorResource(id = R.color.white)
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
