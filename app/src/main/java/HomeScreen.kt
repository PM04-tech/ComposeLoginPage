import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeloginpage.R
import com.example.composeloginpage.member.Profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val userList = listOf(
        Profile("Superman", "Hero"),
        Profile("Batman", "Hero"),
        Profile("Wonder Woman", "Hero"),
        Profile("Spider-Man", "Hero"),
        Profile("Iron Man", "Hero"),
        Profile("Hulk", "Hero"),
        Profile("Thor", "Hero"),
        Profile("Captain America", "Hero"),
        Profile("Black Panther", "Hero")
    )


    Scaffold(topBar = {
        TopAppBar(title = { Text("Superheroes List") }, navigationIcon = {
            IconButton(onClick = {
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true } // Remove Home from backstack
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = ""
                )
            }
        })
    }, content = { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) // Respect scaffold padding
                .background(Color(0xFFE0F7FA))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(userList) { user ->
                MessageCard(user)
            }
        }
    })
}


@Composable
fun MessageCard(user: Profile) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB2EBF2) // Soft teal card color
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(modifier = Modifier.padding(all = 16.dp)) {
            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = user.name)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = user.destination)
            }

        }
    }

}

