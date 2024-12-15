import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 数据类定义
data class ListItem(
    val id: Int,
    val name: String,
    val message: String,
    val time: String
)

@Composable
fun ChatItem(
    imageRes: Int,
    name: String,
    message: String,
    time: String,
    onClick: () -> Unit // 增加点击回调参数
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }, // 添加点击事件
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 图片
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 8.dp)
        )

        // 名称和消息信息
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // 消息时间
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp
        )
    }
}



@Preview
@Composable
fun PreviewChatItem() {
    ChatItem(
        imageRes = android.R.drawable.ic_menu_gallery, // 示例图片资源
        name = "张三",
        message = "这是最后一条消息",
        time = "12:30",
        onClick = {
            
        }
    )
}
