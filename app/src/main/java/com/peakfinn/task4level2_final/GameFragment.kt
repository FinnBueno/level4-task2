package com.peakfinn.task4level2_final

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.peakfinn.assignment2.data.database.MatchRepository
import com.peakfinn.task4level2_final.util.RoundAction
import com.peakfinn.task4level2_final.util.RoundResult
import com.peakfinn.task4level2_final.data.Match
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ThreadLocalRandom

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private val mainScope = CoroutineScope(Dispatchers.Main)

    private lateinit var matchRepository: MatchRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchRepository = MatchRepository(requireContext())
        setHasOptionsMenu(true)

        scissorButton.setOnClickListener { handleRound(RoundAction.SCISSORS) }
        paperButton.setOnClickListener { handleRound(RoundAction.PAPER) }
        rockButton.setOnClickListener { handleRound(RoundAction.ROCK) }

        calculateStatistics()
    }

    private fun handleRound(playerAction: RoundAction) {
        val computerAction = RoundAction.values()[ThreadLocalRandom.current().nextInt(
            RoundAction.values().size)]

        computerActionImage.setImageResource(computerAction.image)
        playerActionImage.setImageResource(playerAction.image)

        val roundResult = when {
            playerAction.ordinal == computerAction.ordinal -> {
                RoundResult.DRAW
            }
            (playerAction.ordinal + 1) % 3 == computerAction.ordinal -> {
                RoundResult.WON
            }
            else -> {
                RoundResult.LOSE
            }
        }

        matchResult.text = getString(roundResult.textResource)

        val match = Match(roundResult, playerAction, computerAction, System.currentTimeMillis())
        mainScope.launch {
            withContext(Dispatchers.IO) {
                matchRepository.storeMatch(match)
            }
            calculateStatistics()
        }
    }

    private fun calculateStatistics() {
        var won = 0
        var draw = 0
        var lose = 0
        mainScope.launch {
            withContext(Dispatchers.IO) {
                won = matchRepository.getWonMatches()
                draw = matchRepository.getDrawMatches()
                lose = matchRepository.getLostMatches()
            }
            allTimeStatistics.text = "Win: ${won}  Draw: ${draw}  Lose: ${lose}"
        }
    }

    override fun onResume() {
        super.onResume()
        calculateStatistics()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_history -> {
                findNavController().navigate(R.id.fragment_history)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_game, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}