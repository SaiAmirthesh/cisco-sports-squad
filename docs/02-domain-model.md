# 02 — Domain Model

## Initial Idea

Use a binary selection array and a `HashMap<Integer, Player>`.

```text
[1,1,1,1,1,1,1,0,0]
```

## Refinement

Use:

- `Player[]` for the fixed ordered roster;
- `int[]` for selection;
- `SquadRules` for required constraints;
- `SquadStats` for calculated values.

## Change

The HashMap was removed because the roster is fixed, ordered, and directly indexable.

```text
selection[i] == 1
        ↓
roster[i]
```

This is simpler for the current problem.
