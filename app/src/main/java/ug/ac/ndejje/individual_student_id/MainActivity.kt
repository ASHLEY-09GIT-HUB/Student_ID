package ug.ac.ndejje.individual_student_id

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ug.ac.ndejje.individual_student_id.ui.theme.Individual_student_IDTheme

val Maroon = Color(0xFF7D161A)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Individual_student_IDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        StudentIdCard()
                    }
                }
            }
        }
    }
}

@Composable
fun StudentIdCard() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .aspectRatio(1.58f),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Faded Watermarks in Background
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(bottom = 20.dp)
                    .alpha(0.06f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ndu_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(240.dp)
                        .offset(x = (-60).dp, y = 40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ndu_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(240.dp)
                        .offset(x = 60.dp, y = 40.dp)
                )
            }

            Column(modifier = Modifier.fillMaxSize()) {
                // Top Maroon Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                        .background(Maroon)
                ) {
                    // Uganda Flag (Top Right)
                    UgandaFlag(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .size(width = 80.dp, height = 50.dp)
                    )
                }

                // Bottom Info Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(55.dp))

                    Text(
                        text = stringResource(id = R.string.student_name),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )

                    Text(
                        text = stringResource(id = R.string.programme),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    Text(
                        text = stringResource(id = R.string.reg_number),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.issue_date),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = stringResource(id = R.string.expiry_date),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Barcode(modifier = Modifier.padding(bottom = 12.dp))
                }
            }

            // University Logo (Top Left)
            Surface(
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp)
                    .size(100.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ndu_logo),
                    contentDescription = stringResource(id = R.string.ndu_logo_description),
                    modifier = Modifier.padding(10.dp)
                )
            }

            // Student Photo (Center Overlap)
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 30.dp)
            ) {
                // Dark outer ring
                Surface(
                    modifier = Modifier.size(145.dp),
                    shape = CircleShape,
                    color = Maroon,
                    border = BorderStroke(2.dp, Color.White)
                ) {}

                // Photo with white border
                Surface(
                    modifier = Modifier
                        .size(135.dp)
                        .align(Alignment.Center),
                    shape = CircleShape,
                    border = BorderStroke(4.dp, Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ashley),
                        contentDescription = stringResource(id = R.string.student_photo_description),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun UgandaFlag(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(4.dp))
        .border(1.dp, Color.White, RoundedCornerShape(4.dp))) {
        val colors = listOf(Color.Black, Color.Yellow, Color.Red, Color.Black, Color.Yellow, Color.Red)
        colors.forEach { color ->
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color))
        }
    }
}

@Composable
fun Barcode(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.85f)
            .height(45.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(100) { index ->
            val barWidth = when {
                index % 13 == 0 -> 4.dp
                index % 7 == 0 -> 3.dp
                index % 3 == 0 -> 2.dp
                else -> 1.5.dp
            }
            if (index % 2 == 0) {
                Box(modifier = Modifier
                    .width(barWidth)
                    .fillMaxHeight()
                    .background(Color.Black))
            } else {
                Spacer(modifier = Modifier.width(1.5.dp))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
fun FinalIdCardPreview() {
    Individual_student_IDTheme {
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            StudentIdCard()
        }
    }
}
