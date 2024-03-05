package com.demo.news.presentation.newslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.demo.news.domain.model.NewsItem
import com.demo.news.presentation.theme.Gray40
import com.demo.news.presentation.theme.NewsDemoTheme
import com.demo.news.presentation.theme.PrimaryTextColor
import com.demo.news.presentation.theme.SecondaryTextColor
import com.demo.news.presentation.theme.cornerRadius
import com.demo.news.presentation.theme.dividerHeight
import com.demo.news.presentation.theme.paddingIntermediate
import com.demo.news.presentation.theme.paddingLarge
import com.demo.news.presentation.theme.paddingMedium

/**
 * news list item
 *
 * @param newsItem
 * @param onItemClick
 *
 * This compose view will show the news item
 */
@Composable
fun NewsListItem(
    newsItem: NewsItem,
    onItemClick: (NewsItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = paddingLarge,
                top = paddingLarge,
                end = paddingLarge,
                bottom = paddingIntermediate
            )
            .clickable {
                onItemClick(newsItem)
            }
            .semantics {

            }
    ) {
        Column(modifier = Modifier.background(color = Color.White)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(newsItem.enclosureLink)
                    .crossfade(true).build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(cornerRadius))
                    .fillMaxWidth()
                    .wrapContentSize()
                    .aspectRatio(16 / 9f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingLarge),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = newsItem.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = PrimaryTextColor
                )
                Divider(
                    color = Gray40,
                    thickness = dividerHeight,
                    modifier = Modifier.padding(top = paddingMedium, bottom = paddingIntermediate)
                )
                Text(
                    text = newsItem.author,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodySmall,
                    color = SecondaryTextColor
                )

            }
        }
    }
}

/**
 * Main news list item preview
 *
 */
@Composable
@Preview
fun MainNewsListItemPreview() {
    NewsDemoTheme {
        NewsListItem(
            NewsItem(
                "https://live-production.wcms.abc-cdn.net.au/48b8c0c2b02226569b74ca56e4dd59bc?impolicy=wcms_crop_resize&cropH=1687&cropW=2998&xPos=0&yPos=13&width=862&height=485",
                "Test Title",
                "Test Author"
            )
        ) {}
    }
}
