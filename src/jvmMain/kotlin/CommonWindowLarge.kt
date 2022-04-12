import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun CommonWindowLarge(
    modifier: Modifier = Modifier,
    additionalContent: @Composable () -> Unit,
    cardContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HSpacer(16.dp)
        Card(
            modifier = Modifier.width(360.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                cardContent()
            }
        }
        HSpacer(16.dp)
        Box(
            modifier = Modifier.width(360.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            additionalContent()
        }
    }
}