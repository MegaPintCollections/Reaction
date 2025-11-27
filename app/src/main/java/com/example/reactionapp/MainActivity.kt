package com.example.reactionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.reactionapp.ui.theme.ReactionAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


const val RATING_ONE = 0
const val RATING_TWO = 1
const val RATING_THREE = 2
const val RATING_FOUR = 3
const val RATING_FIVE = 4

class MainActivity : ComponentActivity() {
    val rating = MutableStateFlow<Int>(RATING_ONE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReactionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Rating(rating, ::onClickImage)
                }
            }
        }
    }

    fun onClickImage() {
        rating.value = (rating.value + 1) % 5
    }
}

@Composable
fun Rating(rating: StateFlow<Int>, onClick: () -> Unit) {
    val observedRating by rating.collectAsState()
    val image = when(observedRating) {
        RATING_ONE -> painterResource(R.drawable.barron_1)
        RATING_TWO -> painterResource(R.drawable.barron_2)
        RATING_THREE -> painterResource(R.drawable.barron_3)
        RATING_FOUR -> painterResource(R.drawable.barron_4)
        RATING_FIVE -> painterResource(R.drawable.barron_5)
        else -> painterResource(R.drawable.barron_1)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Green)
        .clickable { onClick.invoke() }, contentAlignment = Alignment.Center) {
        Image(painter = image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(text = "${observedRating + 1}/5", fontSize = 50.sp, color = Color.White)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReactionAppTheme {
        Rating(MutableStateFlow(0), {})
    }
}