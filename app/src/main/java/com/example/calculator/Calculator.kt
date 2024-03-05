package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
   onAction: (CalculatorActions) -> Unit
){

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(text = state.number1 + (state.operation ?: "") + state.number2,
                fontSize = 36.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.End,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                maxLines = 2
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(symbol = "AC",
                modifier = Modifier
                    .background(Color.LightGray)
                    .aspectRatio(2f)
                    .weight(2f),
                onClick = {
                    onAction(CalculatorActions.Clear)
                })

            CalculatorButton(symbol = "Del",
                modifier = Modifier
                    .background(Color.LightGray)
                    .aspectRatio(1f)
                    .weight(1f),
                onClick = {
                    onAction(CalculatorActions.Delete)
                })

            CalculatorButton(symbol = "/",
                modifier = Modifier
                    .background(Color.Yellow)
                    .aspectRatio(1f)
                    .weight(1f),
                onClick = {
                    onAction(CalculatorActions.Operation(CalculatorOperation.Division))
                })
        }
    }

}