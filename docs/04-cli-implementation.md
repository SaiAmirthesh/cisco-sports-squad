# 04 — CLI Implementation

## Input

Use a nine-element selection array:

```text
1 = selected
0 = not selected
```

## Report

The CLI should display:

- selected players;
- position counts;
- cohort counts;
- violations;
- overall status.

## Design Rule

`Main` handles interaction only. Business rules remain inside `SquadValidator`.
