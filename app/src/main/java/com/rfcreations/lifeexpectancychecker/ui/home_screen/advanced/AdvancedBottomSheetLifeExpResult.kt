package com.rfcreations.lifeexpectancychecker.ui.home_screen.advanced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.GenerativeModel
import com.rfcreations.lifeexpectancychecker.R
import com.rfcreations.lifeexpectancychecker.util.LifeExpectancyCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedBottomSheetLifeExpResult(
    generativeModel: GenerativeModel,
    selectedCountry: String,
    genderSelection: String,
    smokeSelection: String,
    socialClassSelection: String,
    educationSelection: String,
    relationShipStatus: String,
    diabeticSelection: String,
    jobSelection: String,
    bmiSelection: String,
    exerciseSelection: String,
    alcoholConsumption: String,
    dietQuality: String,
    stressLevel: String,
    sleepQuality: String,
    accessToHealthcare: String,
    environmentalConditions: String,
    geneticPredisposition: String,
    modifier: Modifier = Modifier,
    toggleShowResultSheet: () -> Unit,
) {
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var aiResponse by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val lifeExpectancy =
        LifeExpectancyCalculator.calculateAdvancedLifeExpectancy(
            context,
            selectedCountry,
            genderSelection,
            smokeSelection,
            socialClassSelection,
            educationSelection,
            relationShipStatus,
            diabeticSelection,
            jobSelection,
            bmiSelection,
            exerciseSelection,
            alcoholConsumption,
            dietQuality,
            stressLevel,
            sleepQuality,
            accessToHealthcare,
            environmentalConditions,
            geneticPredisposition
        )

    ModalBottomSheet(
        onDismissRequest = toggleShowResultSheet,
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        dragHandle = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier.height(20.dp))
            Row(
                modifier,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { toggleShowResultSheet() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.inverseSurface.copy(
                            0.3f
                        )
                    ),
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    Icon(Icons.Outlined.Close, contentDescription = null, modifier.size(72.dp))
                }
            }
            Column(modifier = modifier.padding(horizontal = 8.dp)) {

                Text(
                    text = stringResource(R.string.results),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.your_country, selectedCountry),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = stringResource(
                        R.string.average_life_expectancy_years,
                        lifeExpectancy.second
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(R.string.life_expectancy_years, lifeExpectancy.first),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.how_to_improve_life_expectancy),
                    modifier,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )

                Spacer(modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.step_to_improve_life_exp),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            if (aiResponse.isEmpty()) {
                OutlinedButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .align(Alignment.End),
                    onClick = {
                        CoroutineScope(IO).launch {
                            try {
                                isLoading = true
                                val prompt = """
        I need an advice, am a native of $selectedCountry ($genderSelection).
        This is my lifestyle and parameters
        Smoke ->  $smokeSelection
        social status -> $socialClassSelection
        education status -> $educationSelection
        relationship -> $relationShipStatus
        diabetic status -> $diabeticSelection,
        job status -> $jobSelection
        bmi -> $bmiSelection
        exercise frequency -> $exerciseSelection
        alcohol consumption rate -> $alcoholConsumption
        dietQuality -> $dietQuality
        stressLevel -> $stressLevel,
        sleep quality-> $sleepQuality
        access to healthcare -> $accessToHealthcare
        environmental conditions,
        geneticPredisposition -> $geneticPredisposition
    """.trimIndent()
                                aiResponse = generativeModel.generateContent(prompt).text.toString()
                                isLoading = false
                            } catch (e: Exception) {
                                isLoading = false
                                aiResponse = e.message.toString()
                            }
                        }
                    }
                ) {
                    Text(text = "Get AI Opinion")
                }
            }
            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            if (aiResponse.isNotEmpty()) {
                BoldTextFromApi(text = aiResponse)
            }
        }
    }
}

@Composable
fun BoldTextFromApi(text: String) {
    val parts = text.split("**") // Split the text by "**"

    // Iterate through parts, apply bold style to text between "**"
    val annotatedString = buildAnnotatedString {
        parts.forEachIndexed { index, part ->
            if (index % 2 == 0) {
                // Plain text
                append(part)
            } else {
                // Bold text
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(part)
                }
            }
        }
    }

    // Render the annotated string
    Text(text = annotatedString)
}
