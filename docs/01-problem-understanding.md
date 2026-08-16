# 01 — Problem Understanding

## Goal

Validate a manually selected squad against the fixed nine-player roster.

## Rules

- Squad size = 7
- Goalkeepers = 1
- Defenders >= 2
- Forwards >= 2
- No unavailable players
- YEAR_2 <= 4
- YEAR_3 <= 4

UTILITY players count toward squad size and cohort totals but not defender/forward minimums.

## Violation Order

1. `SQUAD_SIZE_MUST_BE_7`
2. `GOALKEEPER_COUNT_MUST_BE_1`
3. `MINIMUM_DEFENDERS_NOT_MET`
4. `MINIMUM_FORWARDS_NOT_MET`
5. `PLAYER_UNAVAILABLE: <ID>`
6. `COHORT_LIMIT_EXCEEDED: <cohort> has <count>, maximum 4`

## Required Scenarios

- S01–S07 → VALID
- S07 → S08 → unavailable + YEAR_2 violations
- S01–S06 → size violation
- Reset → original valid selection
