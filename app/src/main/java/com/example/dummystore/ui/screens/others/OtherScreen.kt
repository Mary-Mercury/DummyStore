package com.example.dummystore.ui.screens.others

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OtherScreen(
    navController: NavController,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthCard(
            onClick = { navController.navigate("auth") }
        )
        Spacer(modifier = Modifier.padding(15.dp))
        SelectCity()
        Spacer(modifier = Modifier.padding(15.dp))
        HelpParameters()
    }
}

@Composable
fun AuthCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                minLines = 2,
                fontSize = 20.sp,
                text = "Получайте бонусы, сохраняйте заказы."
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                onClick = onClick
            ) {
                Text(text = "Войти")
            }
        }
    }
}

@Composable
fun AuthedCard(
    firstName: String,
    secondName: String,
    email: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                text = "$firstName $secondName"
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                fontSize = 20.sp,
                text = email
            )
        }
    }
}

@Composable
fun SelectCity() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(text = "Новороссийск", fontSize = 20.sp)
        }
    }
}

@Composable
fun HelpParameters() {
    val enumData = enumValues<OtherScreenSrc>().toList()

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            CardArea {
                enumData.forEachIndexed { index, screen ->
                    ItemCard(
                        othersScreen = screen,
                        onClick = {

                        }
                    )
                    if (index < enumData.size-1) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardArea(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(0.3.dp)
    ) {
        Column {
            content()
        }
    }
}

@Composable
fun ItemCard(
    othersScreen: OtherScreenSrc,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = othersScreen.title,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}
