package pl.mateuszteteruk.composeplayground.dropdownmenu

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun DropdownMenuExample() {
    val months = Month.values().toList()

    var state by rememberSaveable(stateSaver = UiState.Saver) { mutableStateOf(UiState()) }

    ExposedDropdownMenuBox(
        expanded = state.isExpanded,
        onExpandedChange = { state = state.copy(isExpanded = !state.isExpanded) }
    ) {
        TextField(
            readOnly = true,
            value = state.selectedMonth.name,
            onValueChange = { },
            label = { Text("Month") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = state.isExpanded) },
        )
        ExposedDropdownMenu(
            expanded = state.isExpanded,
            onDismissRequest = { state = state.copy(isExpanded = false) }
        ) {
            months.forEach { month ->
                val isCurrentlySelected = month == state.selectedMonth
                DropdownMenuItem(
                    onClick = {
                        state = state.copy(isExpanded = false, selectedMonth = month)
                    }
                ) {
                    Row {
                        if (isCurrentlySelected) {
                            Icon(imageVector = Icons.Outlined.Check, contentDescription = "Selected month")
                        } else {
                            Spacer(modifier = Modifier.width(24.dp))
                        }
                        Text(text = month.name)
                    }
                }
            }
        }
    }
}

enum class Month {
    January,
    February,
    March,
    April,
    May,
    June,
    July,
    August,
    September,
    October,
    November,
    December;
}

private data class UiState(
    val isExpanded: Boolean = false,
    val selectedMonth: Month = Month.January,
) {

    companion object {

        val Saver: Saver<UiState, Any> = run {
            val isExpandedKey = "isExpanded"
            val selectedMonth = "selectedMonth"

            mapSaver(
                save = {
                    mapOf(
                        isExpandedKey to it.isExpanded,
                        selectedMonth to it.selectedMonth,
                    )
                },
                restore = {
                    UiState(
                        isExpanded = it[isExpandedKey] as Boolean,
                        selectedMonth = it[selectedMonth] as Month,
                    )
                }
            )
        }
    }
}
