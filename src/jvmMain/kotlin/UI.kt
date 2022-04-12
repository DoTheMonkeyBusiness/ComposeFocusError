import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun UI() {
    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            ColumnUI(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            RowUI(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
private fun RowUI(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
    ) {
        Content()
    }
}

@Composable
private fun ColumnUI(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Content()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Content() {
    val (refF, refS) = remember { FocusRequester.createRefs() }
    var valueFirst by remember { mutableStateOf(TextFieldValue()) }
    var valueSecond by remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        modifier = Modifier.focusOrder(refF) {
            next = refS
        },
        value = valueFirst,
        onValueChange = {
            valueFirst = it
        }
    )
    OutlinedTextField(
        modifier = Modifier.focusOrder(refS),
        value = valueSecond,
        onValueChange = {
            valueSecond = it
        }
    )

    LaunchedEffect(Unit) {
        println("called rquest")
        refF.requestFocus()
    }
}
