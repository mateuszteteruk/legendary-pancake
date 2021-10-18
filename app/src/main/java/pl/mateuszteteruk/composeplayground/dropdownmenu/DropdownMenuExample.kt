package pl.mateuszteteruk.composeplayground.dropdownmenu

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Month(val name: String)

private fun getMonths(): List<Month> = listOf(
    Month("January"),
    Month("February"),
    Month("March"),
    Month("April"),
    Month("May"),
    Month("June"),
    Month("July"),
    Month("August"),
    Month("September"),
    Month("October"),
    Month("November"),
    Month("December"),
)

@ExperimentalMaterialApi
@Composable
fun DropdownMenuExample() {
    val months = getMonths()
    var expanded by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf(months.first()) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = selectedMonth.name,
            onValueChange = { },
            label = { Text("Month") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            months.forEach { month ->
                DropdownMenuItem(
                    onClick = {
                        selectedMonth = month
                        expanded = false
                    }
                ) {
                    Text(text = month.name)
                }
            }
        }
    }
}
