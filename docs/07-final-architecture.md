# 07 — Final Architecture

```text
                 Main
                  │
                  ▼
             selection[]
                  │
                  ▼
              Player[]
                  │
                  ▼
          SquadValidator
            /         \
           ▼           ▼
     SquadRules    SquadStats
           \           /
            ▼         ▼
          ValidationResult
                  │
                  ▼
              CLI Report
```

## Core Decision

Separate required constraints (`SquadRules`) from calculated current values (`SquadStats`) and keep validation logic inside `SquadValidator`.
