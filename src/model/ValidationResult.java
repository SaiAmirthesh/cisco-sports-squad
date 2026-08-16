package model;

import java.util.List;

public class ValidationResult {
    private final boolean valid;
    private final SquadStats stats;
    private final List<String> violations;

    public ValidationResult(boolean valid, SquadStats stats, List<String> violations) {
        this.valid = valid;
        this.stats = stats;
        this.violations = List.copyOf(violations);
    }

    public boolean isValid() {
        return valid;
    }

    public SquadStats getStats() {
        return stats;
    }

    public List<String> getViolations() {
        return violations;
    }
}
