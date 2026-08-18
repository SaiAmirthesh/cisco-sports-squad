import data.Roster;
import input.SelectionReader;
import model.Player;
import model.SquadRules;
import model.SquadStats;
import model.ValidationResult;
import validator.SquadValidator;

import java.util.*;

public class Main {

    private static final SquadRules RULES = new SquadRules(7, 1, 2, 2, 4);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] selection = readSelection(scanner);
        SquadValidator validator = new SquadValidator();
        ValidationResult result = validator.validate(Roster.PLAYERS, selection, RULES);
        displayResult(Roster.PLAYERS, selection, result);

        scanner.close();
    }

    private static int[] readSelection(Scanner scanner) {
        System.out.println("SPORTS SQUAD CONSTRAINT CHECKER");
        System.out.println("--------------------------------");
        System.out.println("AVAILABLE PLAYERS");
        System.out.println("--------------------------------");
        printRoster(Roster.PLAYERS);
        System.out.println();
        System.out.println("Enter 9 values (1 = selected, 0 = not selected).");
        System.out.println("Example: 1 1 1 1 1 1 1 0 0");
        System.out.print("> ");

        return SelectionReader.readSelection(scanner, Roster.PLAYERS.length);
    }

    private static void printRoster(Player[] roster) {
        for (Player player : roster) {
            System.out.printf(
                    "%s | %-15s | %-11s | %-6s | %s%n",
                    player.getId(),
                    player.getName(),
                    player.getPosition(),
                    player.getCohort(),
                    player.getAvailability()
            );
        }
    }

    private static void displayResult(Player[] roster, int[] selection, ValidationResult result) {

        System.out.println();
        System.out.println("SELECTED PLAYERS");
        System.out.println("--------------------------------");

        for (int i = 0; i < roster.length; i++) {
            if (selection[i] == 1) {
                Player player = roster[i];

                System.out.printf(
                        "%s | %-15s | %-11s | %-6s | %s%n",
                        player.getId(),
                        player.getName(),
                        player.getPosition(),
                        player.getCohort(),
                        player.getAvailability()
                );
            }
        }

        SquadStats stats = result.getStats();

        System.out.println();
        System.out.println("POSITION COUNTS");
        System.out.println("--------------------------------");
        System.out.println("Goalkeeper : " + stats.getGoalkeepers());
        System.out.println("Defender   : " + stats.getDefenders());
        System.out.println("Forward    : " + stats.getForwards());
        System.out.println("Utility    : " + stats.getUtility());

        System.out.println();
        System.out.println("COHORT COUNTS");
        System.out.println("--------------------------------");
        System.out.println("YEAR_2 : " + stats.getYear2Count());
        System.out.println("YEAR_3 : " + stats.getYear3Count());

        System.out.println();
        System.out.println("RULE RESULT");
        System.out.println("--------------------------------");

        System.out.println(
                ruleState(
                        stats.getTotalPlayers() == RULES.getRequiredSquadSize(),
                        "Squad size"
                )
        );

        System.out.println(
                ruleState(
                        stats.getGoalkeepers() == RULES.getRequiredGoalkeepers(),
                        "Goalkeeper count"
                )
        );

        System.out.println(
                ruleState(
                        stats.getDefenders() >= RULES.getMinimumDefenders(),
                        "Minimum defenders"
                )
        );

        System.out.println(
                ruleState(
                        stats.getForwards() >= RULES.getMinimumForwards(),
                        "Minimum forwards"
                )
        );

        System.out.println(
                ruleState(
                        stats.getUnavailablePlayers().isEmpty(),
                        "Player availability"
                )
        );

        System.out.println(
                ruleState(
                        stats.getYear2Count() <= RULES.getMaximumCohortSize()
                                && stats.getYear3Count() <= RULES.getMaximumCohortSize(),
                        "Cohort limits"
                )
        );

        System.out.println();
        System.out.println("VIOLATIONS");
        System.out.println("--------------------------------");

        if (result.getViolations().isEmpty()) {
            System.out.println("None");
        } else {
            for (String violation : result.getViolations()) {
                System.out.println("- " + violation);
            }
        }

        System.out.println();
        System.out.println("STATUS: " + (result.isValid() ? "VALID" : "INVALID"));
    }

    private static String ruleState(boolean passed, String rule) {
        return (passed ? "[PASS] " : "[FAIL] ") + rule;
    }
}
