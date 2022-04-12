import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun CommonWindowMedium(
    modifier: Modifier = Modifier,
    additionalContent: @Composable () -> Unit,
    cardContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(
                vertical = 16.dp,
                horizontal = 24.dp,
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HSpacer(16.dp)
        cardContent()
        HSpacer(16.dp)
        additionalContent()
    }
}