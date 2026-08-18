# Sports Squad Constraint Checker - Java CLI

## Structure

```text
SportsSquadJavaCLI/
├── src/
│   ├── Main.java
│   ├── model/
│   │   ├── Player.java
│   │   ├── Position.java
│   │   ├── Cohort.java
│   │   ├── Availability.java
│   │   ├── SquadRules.java
│   │   ├── SquadStats.java
│   │   └── ValidationResult.java
│   ├── data/
│   │   └── Roster.java
│   └── validator/
│       └── SquadValidator.java
├── test/
│   └── validator/
│       └── SquadValidatorTest.java
└── README.md
```

## Baseline

```text
1 1 1 1 1 1 1 0 0
```

Expected status:

```text
VALID
```

## S07 -> S08

```text
1 1 1 1 1 1 0 1 0
```

Expected violations:

```text
PLAYER_UNAVAILABLE: S08
COHORT_LIMIT_EXCEEDED: YEAR_2 has 5, maximum 4
```

## Six-player case

```text
1 1 1 1 1 1 0 0 0
```

Expected:

```text
SQUAD_SIZE_MUST_BE_7
```

## Note

This implementation uses the fixed nine-position binary input discussed in the design.

Because selection is represented by roster indexes rather than arbitrary player IDs, the `INVALID_SELECTION_REFERENCE` contract from the original specification is not representable through this particular CLI input format. If that edge case needs to be supported, the input model should be changed to accept player IDs instead of only 0/1 positions.
