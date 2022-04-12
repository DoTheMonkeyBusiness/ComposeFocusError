import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UI() {
    Contentt()
//    BoxWithConstraints {
//        if (maxWidth < 600.dp) {
//            ColumnUI(
//                modifier = Modifier.fillMaxSize()
//            )
//        } else {
//            RowUI(
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
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

@Composable
private fun Contentt() {
    var isNeedToShowProgress: Boolean by remember { mutableStateOf(false) }

    Surface {
        BoxWithConstraints {
            CommonWindow(
                isNeedToShowProgress = isNeedToShowProgress,
                additionalContent = {
                    CommonAdditionalBlock(
                        description = "Don't have an account?",
                        action = "Sign up",
                        onActionClick = {
                        }
                    )
                },
                maxWidth = maxWidth,
            ) {
                LoginBlock()
            }
        }
    }
}

@Composable
private fun LoginBlock(
) {
    CommonHeaderBlock(
        title = "Sign in",
        description = "Login to manage your account",
    )
    HSpacer(16.dp)
    InputsBlock(
        loginField = TextFieldValue(),
        passwordField = TextFieldValue(),
        onLoginChanged = {  },
        onPasswordChanged = {  },
    )
    HSpacer(8.dp)
    ActionsBlock(
        isRememberEnabled = false,
        isRememberClicked = {  },
        onForgotPasswordClicked = {
        },
    )
    HSpacer(24.dp)
    ButtonsBlock(
        onSignInClick = {  },
        onSkipSignInClick = {  }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputsBlock(
    loginField: TextFieldValue,
    passwordField: TextFieldValue,
    onLoginChanged: (TextFieldValue) -> Unit,
    onPasswordChanged: (TextFieldValue) -> Unit,
) {
    val (emailRef, passwordRef) = FocusRequester.createRefs()

    OutlinedTextField(
        modifier = Modifier.focusOrder(emailRef) {
            next = passwordRef
        },
        value = loginField,
        onValueChange = onLoginChanged,
    )
    HSpacer(8.dp)
    OutlinedTextField(
        modifier = Modifier.focusOrder(passwordRef),
        value = passwordField,
        onValueChange = onPasswordChanged,
    )

    LaunchedEffect(Unit) {
        emailRef.requestFocus()
    }
}

@Composable
private fun ButtonsBlock(
    onSignInClick: () -> Unit,
    onSkipSignInClick: () -> Unit,
) {
    Button(
        onClick = onSignInClick,
    ) {
        Text("Sign in")
    }
    HSpacer(6.dp)
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Or",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1,
    )
    HSpacer(6.dp)
    Button(
        onClick = onSkipSignInClick,
    ) {
        Text("Sign in")
    }
}

@Composable
private fun ActionsBlock(
    isRememberEnabled: Boolean,
    isRememberClicked: (Boolean) -> Unit,
    onForgotPasswordClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        AACheckBoxWithText(
            checked = isRememberEnabled,
            onCheckedChange = isRememberClicked,
            text = "Remember me",
        )
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            style = MaterialTheme.typography.body2,
            onClick = {
                onForgotPasswordClicked()
            }
        )
    }
}

@Composable
fun HSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun AACheckBoxWithText(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean = true,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(paddingValues = paddingValues)
    ) {
        Checkbox(
            modifier = Modifier.size(24.dp),
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
internal fun CommonHeaderBlock(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1
        )
        HSpacer(16.dp)
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        )
    }
}

@Composable
internal fun CommonAdditionalBlock(
    modifier: Modifier = Modifier,
    description: String,
    action: String,
    onActionClick: () -> Unit,
) {
    val annotatedText = buildAnnotatedString {
        append(description)
        append(" ")

        pushStringAnnotation(
            tag = action,
            annotation = action
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary,
            )
        ) {
            append(action)
        }
        pop()
    }
    ClickableText(
        modifier = modifier,
        text = annotatedText,
        style = MaterialTheme.typography.body2
            .copy(color = MaterialTheme.colors.onSurface),
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = action,
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onActionClick()
            }
        },
    )
}