# Sports Squad Constraint Checker — Java CLI

## Problem

Build a Java CLI that validates a manually selected squad against a fixed nine-player roster.

Rules:

- exactly 7 players;
- exactly 1 goalkeeper;
- at least 2 defenders;
- at least 2 forwards;
- no unavailable player;
- maximum 4 YEAR_2 players;
- maximum 4 YEAR_3 players.

Report every violation in the required order. The application must validate the selection only and must not select, optimize, rank, or recommend a squad.

## Architecture

```text
Main
  ↓
selection[]
  ↓
Roster / Player[]
  ↓
SquadValidator
  ├── SquadRules
  ├── SquadStats
  └── ValidationResult
  ↓
CLI Report
```

## Development Phases

1. Problem understanding
2. Domain modelling
3. Selection/input handling
4. Aggregation
5. Validation engine
6. CLI report
7. Testing

## Coding Structure: 

Follow a modular, class-based structure where each class has a clear single responsibility and related functionality is kept together.


## Documentation

Each meaningful development iteration is recorded separately under `docs/`.

Keep documentation concise and focused on decisions, changes, implementation notes, and verification.
