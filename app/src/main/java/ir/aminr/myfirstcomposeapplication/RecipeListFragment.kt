package ir.aminr.myfirstcomposeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment

/**
 * use old views with compose
 *
 * */

class RecipeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * For new way
        * */
//        val view = ComposeView(requireContext())
//        view.apply {
//            setContent {
//                Text(text = "Hey this is Compose!")
//            }
//        }

        /*
        * For using old views wih compose
        * */
        val view = inflater.inflate(
            R.layout.fragment_recipe_list,
            container,
            false
        )
        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "This is Composable inside fragment!")
                Spacer(modifier = Modifier.padding(8.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(8.dp))
                val customView = HorizontalDottedProgress(requireContext())
                AndroidView(factory = { customView })

            }
        }

        return view
    }
}