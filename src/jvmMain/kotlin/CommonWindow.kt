import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun CommonWindow(
    modifier: Modifier = Modifier,
    isNeedToShowProgress: Boolean = false,
    navigationIcon: @Composable (() -> Unit)? = null,
    additionalContent: @Composable () -> Unit = {},
    maxWidth: Dp,
    cardContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        if (maxWidth > 600.dp) {
            CommonWindowLarge(
                modifier = modifier,
                additionalContent = additionalContent,
                cardContent = cardContent
            )
        } else {
            CommonWindowMedium(
                modifier = modifier,
                additionalContent = additionalContent,
                cardContent = cardContent
            )
        }
    }
}
