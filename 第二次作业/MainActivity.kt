package com.example.zh2207020509no2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zh2207020509no2.ui.theme.ZH2207020509No2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZH2207020509No2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserCardList()
                }
            }
        }
    }
}

data class  User(var userAvtar: Int, var userName:String, var userBio: String)
@Composable
fun UserCard(user: User){
    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .padding(bottom = 100.dp)
        .fillMaxWidth()
        .size(width = 200.dp, height = 100.dp)
        .background(Color.White))
    {
        Box {
            Image(
                painter = painterResource(id = user.userAvtar),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(28.dp)
                    .clip(CircleShape)
            )
        }
        Column {
            Text(text = user.userName, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
            Text(text = user.userBio)
        }
        Text(text = "3' min ago", modifier = Modifier.padding(15.dp), color = Color.Gray)
    }
}


@Composable
fun UserCardList(){
    Box {
        Column {
            val users = listOf<User>(
                User(R.drawable.a, userName = "琳琳", userBio = "简单生活,快乐就好."),
                User(R.drawable.dcd, userName = "张泓", userBio = "学号2207020509"),
                User(R.drawable.b, userName = "aaa", userBio = "dddddddddddddd"),
                User(R.drawable.c, userName = "琳琳", userBio = "简单生活,快乐就好."),

            )
            repeat(users.size){ index->
                UserCard(users[index])
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ZH2207020509No2Theme {
        UserCardList()
    }
}
