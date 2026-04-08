package ug.ac.ndejje.studentcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ug.ac.ndejje.studentcard.ui.theme.StudentCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enables edge-to-edge display to use the full screen real estate
        enableEdgeToEdge()
        setContent {
            StudentCardTheme {
                // Scaffold provides the standard Material Design layout structure
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Root container with padding to avoid overlapping system bars
                    Box(modifier = Modifier.padding(innerPadding)) {
                        StudentCard()
                    }
                }
            }
        }
    }
}

@Composable
fun StudentCard() {
    // Custom University Color
    val nduMaroon = Color(0xFF6C171C)

    /* 1. The external container (The Card)
       Used ElevatedCard to provide a physical card-ish look with shadows.
       The aspect ratio 1.58f is the standard CR80 (credit card size) ratio.
    */
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .aspectRatio(1.58f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.3f)
        )
    ) {
        /* 2. The Master (Box)
           We use a Box because we need to layer elements on top of each other:
           Bottom Layer: Background colors and watermarks
           Middle Layer: Logos and Flag
           Top Layer: Profile photo and Student Details
        */
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {


            // Maroon Header Background (Top 25% of the card)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .background(nduMaroon)
            )

            // Maroon Footer Strip (Thin decorative line at the bottom)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(12.dp)
                    .background(nduMaroon)
            )

            // Watermark Layer: Large, faint logos in the background
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Watermark (Offset partially off-screen)
                Image(
                    painter = painterResource(id = R.drawable.ndejje_badge),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .offset(x = (-80).dp, y = 30.dp),
                    alpha = 0.10f
                )
                // Right Watermark (Offset partially off-screen)
                Image(
                    painter = painterResource(id = R.drawable.ndejje_badge),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .offset(x = 80.dp, y = 30.dp),
                    alpha = 0.10f
                )
            }

            // University Badge (Top Left)
            Image(
                painter = painterResource(id = R.drawable.ndejje_badge),
                contentDescription = "University Logo",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 12.dp, top = 10.dp)
                    .size(78.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )

            // National Flag (Top Right)
            Image(
                painter = painterResource(id = R.drawable.ug_flag),
                contentDescription = "Uganda Flag",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 12.dp, top = 12.dp)
                    .width(75.dp)
                    .height(45.dp)
            )

            // MAIN STUDENT DATA (The Info Column)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Creates space to push content below the maroon header
                Spacer(modifier = Modifier.fillMaxHeight(0.12f))

                // Profile Picture with double border effect
                Box(
                    modifier = Modifier.size(95.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.older),
                        contentDescription = "Student Photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(2.dp, nduMaroon, CircleShape) // Maroon Outer Ring
                            .padding(2.dp)
                            .border(3.dp, Color.White, CircleShape)  // White Inner Ring
                            .padding(3.dp)
                            .clip(CircleShape)
                    )
                }

                // Student Identification Information
                Text(
                    text = stringResource(id = R.string.student_name),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Registration and Program Details
                Row {
                    Text(text = "Programme: ", style = MaterialTheme.typography.labelMedium, color = Color.Black)
                    Text(text = stringResource(id = R.string.programme), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color.Black)
                }

                Row {
                    Text(text = "Registration Number: ", style = MaterialTheme.typography.labelMedium, color = Color.Black)
                    Text(text = stringResource(id = R.string.reg_number), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color.Black)
                }

                // Decorative Divider
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color.LightGray.copy(alpha = 0.5f)
                )

                // Dates Section (Issue and Expiry)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row {
                        Text(text = "Issue: ", style = MaterialTheme.typography.labelSmall, color = Color.Black)
                        Text(text = stringResource(id = R.string.issue_date), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    Row {
                        Text(text = "Expiry: ", style = MaterialTheme.typography.labelSmall, color = Color.Black)
                        Text(text = stringResource(id = R.string.expiry_date), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Pushes the barcode to the bottom

                // Authentication (Barcode)
                Image(
                    painter = painterResource(id = R.drawable.barcode),
                    contentDescription = "Barcode",
                    modifier = Modifier
                        .width(250.dp)
                        .height(28.dp),
                    contentScale = ContentScale.Crop
                )

                // Final breathing room before the bottom maroon strip
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview(showBackground = true, name = "University ID Card")
@Composable
fun StudentCardPreview() {
    StudentCardTheme {
        StudentCard()
    }
}