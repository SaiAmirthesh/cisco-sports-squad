# 03 — Validation Architecture

## Classes

### Player

Stores player identity and attributes.

### SquadRules

Stores required constraints.

### SquadStats

Stores actual values calculated from the selection.

### SquadValidator

Calculates statistics and evaluates every rule in the required order.

### ValidationResult

Contains status, statistics, and violations.

### Main

Handles CLI input and output.

## Flow

```text
selection[]
    ↓
roster traversal
    ↓
SquadStats
    ↓
SquadRules comparison
    ↓
ordered violations
    ↓
ValidationResult
```
