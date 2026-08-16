package validator;

import model.Availability;
import model.Player;
import model.SquadRules;
import model.SquadStats;
import model.ValidationResult;

import java.util.ArrayList;
import java.util.List;

public class SquadValidator {

    public ValidationResult validate(Player[] roster, int[] selection, SquadRules rules) {

        if (selection.length != roster.length) {
            throw new IllegalArgumentException(
                    "Selection must contain exactly " + roster.length + " values."
            );
        }

        SquadStats stats = new SquadStats();

        for (int i = 0; i < roster.length; i++) {
            if (selection[i] != 0 && selection[i] != 1) {
                throw new IllegalArgumentException(
                        "Selection values must be only 0 or 1."
                );
            }

            if (selection[i] == 1) {
                Player player = roster[i];

                stats.incrementTotalPlayers();
                stats.incrementPosition(player.getPosition());
                stats.incrementCohort(player.getCohort());

                if (player.getAvailability() == Availability.UNAVAILABLE) {
                    stats.addUnavailablePlayer(player.getId());
                }
            }
        }

        List<String> violations = new ArrayList<>();

        // 1. Squad size
        if (stats.getTotalPlayers() != rules.getRequiredSquadSize()) {
            violations.add("SQUAD_SIZE_MUST_BE_" + rules.getRequiredSquadSize());
        }

        // 2. Goalkeepers
        if (stats.getGoalkeepers() != rules.getRequiredGoalkeepers()) {
            violations.add("GOALKEEPER_COUNT_MUST_BE_" + rules.getRequiredGoalkeepers());
        }

        // 3. Defenders
        if (stats.getDefenders() < rules.getMinimumDefenders()) {
            violations.add("MINIMUM_DEFENDERS_NOT_MET");
        }

        // 4. Forwards
        if (stats.getForwards() < rules.getMinimumForwards()) {
            violations.add("MINIMUM_FORWARDS_NOT_MET");
        }

        // 5. Unavailable players
        for (String playerId : stats.getUnavailablePlayers()) {
            violations.add("PLAYER_UNAVAILABLE: " + playerId);
        }

        // 6. Cohort limits — required YEAR_2 then YEAR_3 order
        if (stats.getYear2Count() > rules.getMaximumCohortSize()) {
            violations.add(
                    "COHORT_LIMIT_EXCEEDED: YEAR_2 has " + stats.getYear2Count() + ", maximum " + rules.getMaximumCohortSize()
            );
        }

        if (stats.getYear3Count() > rules.getMaximumCohortSize()) {
            violations.add(
                    "COHORT_LIMIT_EXCEEDED: YEAR_3 has " + stats.getYear3Count() + ", maximum " + rules.getMaximumCohortSize()
            );
        }

        return new ValidationResult(violations.isEmpty(), stats, violations);
    }
}
