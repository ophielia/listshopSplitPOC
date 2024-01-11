package co.touchlab.kampkit.android

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import co.touchlab.kampkit.android.models.BreedViewModel
import co.touchlab.kampkit.android.ui.MainScreen
import co.touchlab.kampkit.android.ui.TagsRvAdapter
import co.touchlab.kampkit.android.ui.theme.KaMPKitTheme
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    private val mainScope = MainScope()

    private lateinit var tagListRecyclerView: RecyclerView
    private lateinit var progressBarView: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val sdk = SpaceXSDK(DatabaseDriverFactory(this))

    private val tagRvAdapter = TagsRvAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaMPKitTheme {
                MainScreen(viewModel)
            }
        }
    }
}
