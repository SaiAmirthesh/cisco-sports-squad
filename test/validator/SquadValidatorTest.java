package validator;

import data.Roster;
import model.Availability;
import model.Cohort;
import model.Player;
import model.Position;
import model.SquadRules;
import model.ValidationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SquadValidatorTest {

    private static final SquadRules RULES = new SquadRules(7, 1, 2, 2, 4);

    private final SquadValidator validator = new SquadValidator();

    @ParameterizedTest(name = "{0}")
    @MethodSource("documentedCases")
    void validates_documented_scenarios(TestCase testCase) {
        assertCase(testCase);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("permutationCases")
    void validates_generated_permutations(TestCase testCase) {
        assertCase(testCase);
    }

    @Test
    void rejectsSelectionWithWrongLength() {
        assertThrows(IllegalArgumentException.class, () ->
                validator.validate(Roster.PLAYERS, new int[] {1, 1, 1}, RULES));
    }

    @Test
    void rejectsSelectionWithNonBinaryValues() {
        assertThrows(IllegalArgumentException.class, () ->
                validator.validate(
                        Roster.PLAYERS,
                        new int[] {1, 1, 1, 1, 1, 1, 1, 2, 0},
                        RULES
                ));
    }

    private void assertCase(TestCase testCase) {
        ValidationResult result = validator.validate(Roster.PLAYERS, testCase.selection(), RULES);

        assertEquals(
                testCase.expectedViolations(),
                result.getViolations(),
                testCase.name()
        );
        assertEquals(
                testCase.expectedViolations().isEmpty(),
                result.isValid(),
                testCase.name()
        );
    }

    private static Stream<TestCase> documentedCases() {
        return Stream.of(
                caseOf(
                        "baseline valid",
                        selectionOf(1, 2, 3, 4, 5, 6, 7)
                ),
                caseOf(
                        "baseline with unavailable player",
                        selectionOf(1, 2, 3, 4, 5, 6, 8),
                        "PLAYER_UNAVAILABLE: S08",
                        "COHORT_LIMIT_EXCEEDED: YEAR_2 has 5, maximum 4"
                ),
                caseOf(
                        "six player selection",
                        selectionOf(1, 2, 3, 4, 5, 6),
                        "SQUAD_SIZE_MUST_BE_7"
                ),
                caseOf(
                        "alternate valid selection",
                        selectionOf(2, 3, 4, 5, 6, 7, 9)
                ),
                caseOf(
                        "all players selected",
                        selectionOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                        "SQUAD_SIZE_MUST_BE_7",
                        "GOALKEEPER_COUNT_MUST_BE_1",
                        "PLAYER_UNAVAILABLE: S08",
                        "COHORT_LIMIT_EXCEEDED: YEAR_2 has 5, maximum 4"
                )
        );
    }

    private static Stream<TestCase> permutationCases() {
        List<TestCase> cases = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            cases.add(generatedCase("single-" + i, i));
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                cases.add(generatedCase("pair-" + i + "-" + j, i, j));
            }
        }

        int[][] triples = {
                {1, 2, 3},
                {1, 2, 4},
                {1, 2, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 2, 8},
                {1, 2, 9},
                {1, 3, 4},
                {1, 3, 5},
                {1, 3, 6}
        };

        for (int[] triple : triples) {
            cases.add(generatedCase(
                    "triple-" + triple[0] + "-" + triple[1] + "-" + triple[2],
                    triple
            ));
        }

        return cases.stream();
    }

    private static TestCase generatedCase(String name, int... selectedPlayers) {
        int[] selection = selectionOf(selectedPlayers);
        return new TestCase(
                name,
                selection,
                expectedViolationsFor(selection)
        );
    }

    private static TestCase caseOf(String name,
                                   int[] selection,
                                   String... expectedViolations) {
        return new TestCase(name, selection, List.of(expectedViolations));
    }

    private static int[] selectionOf(int... selectedPlayers) {
        int[] selection = new int[Roster.PLAYERS.length];

        for (int selectedPlayer : selectedPlayers) {
            selection[selectedPlayer - 1] = 1;
        }

        return selection;
    }

    private static List<String> expectedViolationsFor(int[] selection) {
        int totalPlayers = 0;
        int goalkeepers = 0;
        int defenders = 0;
        int forwards = 0;
        int year2 = 0;
        int year3 = 0;
        List<String> unavailablePlayers = new ArrayList<>();
        List<String> violations = new ArrayList<>();

        for (int i = 0; i < Roster.PLAYERS.length; i++) {
            if (selection[i] != 1) {
                continue;
            }

            Player player = Roster.PLAYERS[i];

            totalPlayers++;

            Position position = player.getPosition();
            if (position == Position.GOALKEEPER) {
                goalkeepers++;
            } else if (position == Position.DEFENDER) {
                defenders++;
            } else if (position == Position.FORWARD) {
                forwards++;
            }

            Cohort cohort = player.getCohort();
            if (cohort == Cohort.YEAR_2) {
                year2++;
            } else if (cohort == Cohort.YEAR_3) {
                year3++;
            }

            if (player.getAvailability() == Availability.UNAVAILABLE) {
                unavailablePlayers.add(player.getId());
            }
        }

        if (totalPlayers != RULES.getRequiredSquadSize()) {
            violations.add("SQUAD_SIZE_MUST_BE_" + RULES.getRequiredSquadSize());
        }

        if (goalkeepers != RULES.getRequiredGoalkeepers()) {
            violations.add("GOALKEEPER_COUNT_MUST_BE_" + RULES.getRequiredGoalkeepers());
        }

        if (defenders < RULES.getMinimumDefenders()) {
            violations.add("MINIMUM_DEFENDERS_NOT_MET");
        }

        if (forwards < RULES.getMinimumForwards()) {
            violations.add("MINIMUM_FORWARDS_NOT_MET");
        }

        for (String playerId : unavailablePlayers) {
            violations.add("PLAYER_UNAVAILABLE: " + playerId);
        }

        if (year2 > RULES.getMaximumCohortSize()) {
            violations.add(
                    "COHORT_LIMIT_EXCEEDED: YEAR_2 has "
                            + year2
                            + ", maximum "
                            + RULES.getMaximumCohortSize()
            );
        }

        if (year3 > RULES.getMaximumCohortSize()) {
            violations.add(
                    "COHORT_LIMIT_EXCEEDED: YEAR_3 has "
                            + year3
                            + ", maximum "
                            + RULES.getMaximumCohortSize()
            );
        }

        return List.copyOf(violations);
    }

    private record TestCase(String name,
                            int[] selection,
                            List<String> expectedViolations) {
    }
}
