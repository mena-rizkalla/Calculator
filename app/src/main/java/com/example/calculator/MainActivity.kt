package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.dao.CalculatorDao
import com.example.calculator.database.CalculatorDatabase
import com.example.calculator.factory.CalculatorViewModelFactory
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                //val viewModel = viewModel<CalculatorViewModel>()

                val dao: CalculatorDao = CalculatorDatabase.getDatabase(this).CalculatorDao()
                val factory = CalculatorViewModelFactory(dao)
                val viewModel = ViewModelProvider(this, factory)[CalculatorViewModel::class.java]

                val state = viewModel.state
                val buttonSpacing = 8.dp
                Calculator(state = state,
                    onAction = viewModel::onAction,
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                        .padding(16.dp)
                )
            }
        }
    }
}

