package com.peakfinn.task4level2_final

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.peakfinn.assignment2.data.database.MatchRepository
import com.peakfinn.task4level2_final.adapter.HistoryAdapter
import com.peakfinn.task4level2_final.data.Match
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val matches = arrayListOf<Match>()
    private lateinit var matchRepository: MatchRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchRepository = MatchRepository(requireContext())
        setHasOptionsMenu(true)
        loadMatchesFromDatabase()

        matchHistoryList.adapter = HistoryAdapter(matches)
        matchHistoryList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun loadMatchesFromDatabase() {
        matches.clear()
        mainScope.launch {
            withContext(Dispatchers.IO) {
                matches.addAll(matchRepository.getAllMatches())
            }
            matchHistoryList.adapter?.notifyDataSetChanged()
        }
    }

    private fun clearHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                matchRepository.clear()
            }
            loadMatchesFromDatabase()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.back_history -> {
                findNavController().navigate(R.id.fragment_game)
                true
            }
            R.id.action_history -> {
                clearHistory()
                Snackbar.make(matchHistoryList, "Successfully cleared game history", Snackbar.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_history, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}