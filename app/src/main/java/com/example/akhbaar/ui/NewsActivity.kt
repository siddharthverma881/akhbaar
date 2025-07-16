package com.example.akhbaar.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.akhbaar.AkhbaarApplication
import com.example.akhbaar.R
import com.example.akhbaar.data.model.Article
import com.example.akhbaar.databinding.ActivityNewsBinding
import com.example.akhbaar.di.component.DaggerActivityComponent
import com.example.akhbaar.di.module.ActivityModule
import com.example.akhbaar.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installDependencies()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

        registerObserver()

        binding.composeView.setContent {
            InitView()
        }

    }

    @Composable
    private fun InitView(){
        val uiState by viewModel.uiState.collectAsState()
        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState) {
                is UiState.Success -> {
                    val data = (uiState as UiState.Success).data
                    CreateNews(data)
                }

                is UiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UiState.Error -> { }
            }
        }
    }

    @Composable
    private fun CreateNews(data: List<Article>){
        LazyColumn {
            items(data) {article ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp, 5.dp, 10.dp, 5.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color(237, 237, 237))
                ) {
                    if(article.imageUrl.isNullOrEmpty()){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color(237, 237, 237))
                        )
                    } else {
                        AsyncImage(
                            model = article.imageUrl,
                            contentDescription = "News Image"
                        )
                    }
                    Text(
                        text = article.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 5.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Text(
                        text = article.description ?: "",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 5.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3
                    )
                    val name = article.source?.name ?: ""
                    if(name.isNotEmpty()) {
                        Text(
                            text = "($name)",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp)
                        )
                    }
                }
            }
        }
    }

    private fun registerObserver(){
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when(it){
                    is UiState.Success -> {

                    }
                    is UiState.Loading -> {
//                        binding.progressBar.show()
                    }
                    is UiState.Error -> {
//                        binding.progressBar.hide()
                    }
                }
            }
        }
    }

    private fun installDependencies(){
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as AkhbaarApplication).application)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}