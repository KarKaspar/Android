package com.example.greetings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MessageView() {
    val messages = listOf(
        DataItemModel(text = stringResource(R.string.hello_there), color = Color(0xFF4CAF50)),
        DataItemModel(text = stringResource(R.string.welcome_to_android_development), color = Color(0xFF9E9E9E)),
        DataItemModel(text = stringResource(R.string.are_you_ready_to), color = Color(0xFFFFEB3B)),
        DataItemModel(text = stringResource(R.string.start_exploring), color = Color(0xFFF44336)),
        DataItemModel(text = stringResource(R.string.let_s_go), color = Color(0xFF9C27B0)),
    )

    Column  {
        for (dataItem in messages) {
            MyTextView(text = dataItem.text, color = dataItem.color)
        }
    }

}


