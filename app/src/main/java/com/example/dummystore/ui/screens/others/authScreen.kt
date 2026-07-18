package com.example.dummystore.ui.screens.others

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AuthScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var userLogin by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    var loginError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().padding().padding(20.dp),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.weight(1f).padding(5.dp),
                onClick = {
                    //Просто заглушка
                }
            ) {
                Text(
                    text = "login by ID"
                )
            }

            Button(
                modifier = Modifier.weight(1f).padding(5.dp),
                onClick = {
                    //Просто заглушка
                }
            ) {
                Text(
                    text = "login by password"
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = userLogin,
            onValueChange = {
                userLogin = it
            },
            label = { Text(text = "input your login") },
            placeholder = { Text(text = "ivan ivanov") },
            singleLine = true,
            supportingText = {
                if (loginError) {
                    Text(text = "your login is incorrect")
                }
            },
            isError = loginError
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = userPassword,
            onValueChange = {
                userPassword = it
            },
            label = { Text(text = "input your passoword") },
            placeholder = { Text(text = "more than 6 symbols") },
            singleLine = true,
            supportingText = {
                if (passwordError) {
                    Text(text = "your password is incorrect")
                }
            },
            isError = passwordError
        )

        TextButton(
            onClick = {}
        ) {
            Text(
                modifier = Modifier,
                text = "Forgot password?",
                fontSize = 12.sp
            )
        }

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            onClick = {
                //TODO
            }
        ) {
            Text(text = "Login")
        }
    }
}
