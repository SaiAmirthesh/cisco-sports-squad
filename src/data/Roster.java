package data;

import model.Availability;
import model.Cohort;
import model.Player;
import model.Position;

public final class Roster {

    private Roster() {
    }

    public static final Player[] PLAYERS = {
        new Player("S01", "Aditi Rao", Position.GOALKEEPER,
                Cohort.YEAR_2, Availability.AVAILABLE),

        new Player("S02", "Bilal Khan", Position.DEFENDER,
                Cohort.YEAR_2, Availability.AVAILABLE),

        new Player("S03", "Chitra Nair", Position.DEFENDER,
                Cohort.YEAR_3, Availability.AVAILABLE),

        new Player("S04", "Deepak Shah", Position.FORWARD,
                Cohort.YEAR_2, Availability.AVAILABLE),

        new Player("S05", "Esha Roy", Position.FORWARD,
                Cohort.YEAR_3, Availability.AVAILABLE),

        new Player("S06", "Farhan Das", Position.UTILITY,
                Cohort.YEAR_2, Availability.AVAILABLE),

        new Player("S07", "Gita Menon", Position.UTILITY,
                Cohort.YEAR_3, Availability.AVAILABLE),

        new Player("S08", "Harish Patel", Position.FORWARD,
                Cohort.YEAR_2, Availability.UNAVAILABLE),

        new Player("S09", "Imani Joseph", Position.GOALKEEPER,
                Cohort.YEAR_3, Availability.AVAILABLE)
    };
}
