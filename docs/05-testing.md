# 05 — Testing

## Required Tests

### Baseline

S01–S07:

```text
VALID
7 players
GK=1
DEF=2
FWD=2
UTILITY=2
YEAR_2=4
YEAR_3=3
```

### S07 → S08

Expected:

```text
PLAYER_UNAVAILABLE: S08
COHORT_LIMIT_EXCEEDED: YEAR_2 has 5, maximum 4
```

### Six Players

Expected:

```text
SQUAD_SIZE_MUST_BE_7
```

### Cohort Boundary

```text
4 → valid
5 → violation
```

Also test multiple violations and invalid references where supported.
