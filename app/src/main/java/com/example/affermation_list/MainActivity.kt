package com.example.affermation_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.affermation_list.model.Affirmation
import com.example.affermation_list.ui.theme.Affermation_listTheme
import androidx.compose.ui.tooling.preview.Preview
import com.example.affermation_list.data.Datasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Affermation_listTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}
@Composable
fun AffirmationCard(affirmation: Affirmation,
                    modifier: Modifier = Modifier){
    Card(modifier = Modifier){
        Column {
            Image(painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop)
            Text(text = stringResource(id = affirmation.stringResourceId),
                modifier=Modifier
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineSmall)
        }
    }
}
@Composable
fun AffirmationList(affiermationList: List<Affirmation>,
                    modifier: Modifier=Modifier){
    LazyColumn(modifier = modifier){
        items(affiermationList) {affiermation ->
            AffirmationCard(affirmation = affiermation, modifier = Modifier.padding(8.dp))
        }
    }
    


}
@Composable
fun AffirmationsApp() {
    AffirmationList(affiermationList = Datasource().loadAffirmations())
}

@Preview
@Composable
private fun AffirmationCardPreview(){
    AffirmationCard(Affirmation(R.string.affirmation1,R.drawable.image1))
}