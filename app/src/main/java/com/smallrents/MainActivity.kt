package com.smallrents

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.evaluateCubic
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.smallrents.ui.theme.SmallRentsTheme
import com.smallrents.ui.theme.nunitoFamily
import com.smallrents.ui.theme.robotoFamily
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmallRentsApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallRentsApp(viewModel: RegistrationViewModel = RegistrationViewModel()) {
    SmallRentsTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = nunitoFamily,
                                        fontStyle = FontStyle.Italic,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 25.sp
                                    )
                                ) {
                                    append("small")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = nunitoFamily,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 27.sp,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                ) {
                                    append("Rents")
                                }
                            }
                        )
                    }
                )
            }

        ) { innerPadding ->

            val uiState = viewModel.uiState.collectAsState().value
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(top = 30.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Cadastro",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Normal,
                    )
                }


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.name,
                    onValueChange = { viewModel.onUsernameChange(it) },
                    label = { Text("Nome") },
                    placeholder = { Text("Israel Durra") },
                    singleLine = true
                )


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.gender,
                    onValueChange = { viewModel.onGenderChange(it) },
                    label = { Text("Sexo") },
                    placeholder = { Text("Masculino") },
                    singleLine = true
                )


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.birthDate,
                    onValueChange = { viewModel.onBirthDateChange(it) },
                    label = { Text("Data de nascimento") },
                    placeholder = { Text("13/01/1995") },
                    singleLine = true
                )


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.cpf,
                    onValueChange = { viewModel.onCpfChange(it) },
                    label = { Text("CPF") },
                    placeholder = { Text("417.899.618-58") },
                    singleLine = true
                )


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.cep,
                    onValueChange = { viewModel.onCepChange(it) },
                    label = { Text("CEP") },
                    placeholder = { Text("04444-000") },
                    singleLine = true
                )


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.street,
                    onValueChange = { viewModel.onStreetChange(it) },
                    label = { Text("Logradouro") },
                    placeholder = { Text("Av. Miguel Yunes") },
                    singleLine = true
                )


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.number,
                    onValueChange = { viewModel.onNumberChange(it) },
                    label = { Text("Numero") },
                    placeholder = { Text("300") },
                    singleLine = true
                )


                TextField(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    value = uiState.complement,
                    onValueChange = { viewModel.onComplementChange(it) },
                    label = { Text("Complemento") },
                    placeholder = { Text("Torre A Ap. 101") },
                    singleLine = true
                )

                Button(
                    onClick = { viewModel.register() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            start = 30.dp,
                            end = 30.dp
                        )
                ) {
                    Text("Registrar")
                }
            }
        }
    }
}

@Preview(
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE, name = "Light Mode"
)
@Preview(
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun SmallRentsAppPreview() {
    SmallRentsApp()
}
