package ru.shmakova.vkcup.ui.tagitem

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.repeatOnLifecycle
import ru.shmakova.vkcup.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import ru.shmakova.vkcup.R
import ru.shmakova.vkcup.data.TagItem

@Composable
fun TagsScreen(modifier: Modifier = Modifier, viewModel: TagItemViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val state by produceState<TagItemUiState>(
        initialValue = TagItemUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = STARTED) {
            viewModel.uiState.collect {
                value = it
            }
        }
    }
    if (state is TagItemUiState.Success) {
        TagsScreen(
            items = (state as TagItemUiState.Success).data,
            onSave = viewModel::toggleTagItem,
            modifier = modifier.verticalScroll(rememberScrollState())
        )
    }
}

@Composable
internal fun TagsScreen(
    items: List<TagItem>,
    onSave: (item: TagItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.header_title),
                color = MaterialTheme.colorScheme.onBackground,
            )
            Button(
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                onClick = { },
                contentPadding = PaddingValues(horizontal = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
            ) {
                Text(
                    stringResource(id = R.string.header_button),
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        FlowRow(
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp
        ) {
            items.forEach {
                Tag(it, onSave = onSave)
            }
        }
        Spacer(Modifier.size(20.dp))
        if (items.any { it.isEnabled }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .height(80.dp),
                    contentPadding = PaddingValues(horizontal = 50.dp),
                    onClick = { }
                ) {
                    Text(
                        stringResource(id = R.string.continue_button),
                        fontSize = 18.sp,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}

@Composable
internal fun Tag(
    item: TagItem,
    onSave: (item: TagItem) -> Unit,
) {
    val animatedButtonColor = animateColorAsState(
        targetValue = if (item.isEnabled) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.secondary,
        animationSpec = tween(100, 0, FastOutLinearInEasing)
    )
    val textColor = if (item.isEnabled) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondary
    }
    return Row(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = { onSave(item) })
            .background(animatedButtonColor.value)
            .padding(start = 12.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            item.name,
            fontSize = 16.sp,
            color = textColor
        )
        if (item.isEnabled) {
            Image(
                painterResource(id = R.drawable.ic_check),
                contentDescription = "",
                modifier = Modifier.padding(start = 23.dp)
            )
        } else {
            Image(
                painterResource(id = R.drawable.ic_divider),
                contentDescription = "",
                modifier = Modifier.padding(start = 14.dp, end = 8.dp)
            )
            Image(
                painterResource(id = R.drawable.ic_plus),
                contentDescription = ""
            )
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        TagsScreen(
            listOf(
                TagItem(name = "Compose"),
                TagItem(name = "Room", isEnabled = true),
                TagItem(name = "Kotlin")
            ),
            onSave = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        TagsScreen(
            listOf(
                TagItem(name = "Compose"),
                TagItem(name = "Room", isEnabled = true),
                TagItem(name = "Kotlin")
            ),
            onSave = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun TagPreview() {
    MyApplicationTheme {
        Tag(TagItem(name = "Compose"), onSave = {})
    }
}
