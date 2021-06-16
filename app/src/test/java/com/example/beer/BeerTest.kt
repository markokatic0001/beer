package com.example.beer

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.beer.ui.beers.BeerListViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class BeerTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val app: BeerApplication = BeerApplication()

    @ExperimentalCoroutinesApi
    @Test
    fun buildOfficeCardsTest() {
        val viewModel = BeerListViewModel(app)
        viewModel.getBeers()
        Truth.assertThat(viewModel.filteredBy).isEqualTo(0)
        Truth.assertThat(viewModel.hasMorePages).isTrue()
        Truth.assertThat(viewModel.progressBarLiveData.value).isEqualTo(View.VISIBLE)
    }
}