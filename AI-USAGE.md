# AI Usage — Sports Squad Constraint Checker

## 1. Purpose

AI was used as a development partner throughout the implementation.

The approach was:

```text
Understand → Propose → Question → Implement → Review → Test → Refine
```

AI was used mainly for coding assistance, design review, edge-case identification, and validation. Final technical decisions and code understanding remained with me.

## 2. Problem Understanding

AI was used to break the problem into:

- roster modelling;
- selection representation;
- squad constraints;
- validation order;
- edge cases;
- required test scenarios.

The core contract is that the application validates the user's selection only. It must not optimize, select, rank, or recommend a squad.

The rules are:

1. exactly 7 distinct players;
2. exactly 1 goalkeeper;
3. at least 2 defenders;
4. at least 2 forwards;
5. no unavailable player;
6. maximum 4 YEAR_2 players;
7. maximum 4 YEAR_3 players.

Violations must be reported in the specified order.

## 3. Technology Decision

### Initial Consideration

Spring Boot was initially considered because it is a familiar technology.

AI review identified that a backend, database, REST API, and persistence layer were unnecessary for this problem.

Other options considered were:

- React + TypeScript;
- HTML + JavaScript;
- Python + Streamlit;
- Java CLI.

### Final Direction

Java CLI implementations were considered.

For the Java implementation, CLI was selected because:

- the core problem is deterministic validation;
- Java is familiar to me;
- no backend is required;
- live modifications can be made quickly;
- validation logic can be tested independently.

The choice prioritized simplicity and technology familiarity.

## 4. Java Architecture Iteration

### Initial Idea

My initial Java approach was:

```text
selection[]
      ↓
HashMap<Integer, Player>
      ↓
iterate selected players
      ↓
update running constraints
      ↓
compare against rules
      ↓
violations[]
```

The selection was represented as:

```text
[1,1,1,1,1,1,1,0,0]
```

where each index corresponded to one roster position.

### Review

I questioned whether a `HashMap<Integer, Player>` was necessary.

The roster is fixed and ordered, so:

```text
selection[i]
    ↓
roster[i]
```

already provides direct lookup.

### Change

The final design uses:

```text
Player[]
```

instead of:

```text
HashMap<Integer, Player>
```

This removes an unnecessary data structure while preserving roster order.

## 5. Rules and Runtime State

### Initial Idea

I proposed a rules object containing the constraints and another object updated while iterating.

### Refinement

The concepts were separated:

### `SquadRules`

Represents what a valid squad **should satisfy**.

### `SquadStats`

Represents what the current selection **actually contains**.

This makes the validation comparison explicit and prevents required values from being mixed with calculated runtime state.

## 6. Final Java Structure

```text
Main
 │
 ├── Roster
 ├── SquadRules
 └── SquadValidator
        │
        ├── SquadStats
        └── ValidationResult
```

Packages:

```text
src/
├── Main.java
├── model/
│   ├── Player.java
│   ├── Position.java
│   ├── Cohort.java
│   ├── Availability.java
│   ├── SquadRules.java
│   ├── SquadStats.java
│   └── ValidationResult.java
├── data/
│   └── Roster.java
└── validator/
    └── SquadValidator.java
```

The implementation follows a modular, class-based structure where each class has a clear responsibility.

## 7. Code Generation and Review

AI was used to assist with drafts for:

- domain classes;
- enums;
- fixed roster data;
- validation logic;
- result modelling;
- CLI reporting;
- test scenarios.

Generated code was reviewed rather than accepted blindly.

For important sections, I asked questions such as:

- What does this condition protect against?
- Why is this data structure required?
- Why is this validation performed before the rules?
- What happens for invalid input?
- Does the violation order match the specification?
- Can the structure be simplified?

For example:

```java
if (selection.length != roster.length) {
    throw new IllegalArgumentException(...);
}
```

was reviewed as an input-shape check that prevents invalid index access during roster traversal.

## 8. Core Algorithm Verification

The validator follows:

```text
selection
    ↓
validate input structure
    ↓
iterate roster
    ↓
calculate SquadStats
    ↓
evaluate rules in required order
    ↓
collect every violation
    ↓
return ValidationResult
```

The rules are evaluated independently rather than stopping after the first failure.

Unavailable players are collected during ordered roster traversal so their violations follow roster order.

Cohort violations are evaluated in:

```text
YEAR_2
YEAR_3
```

order.

## 9. Test and Edge-Case Reasoning

### Baseline

```text
111111100
```

Expected:

```text
VALID
```

### S07 → S08

```text
111111010
```

Expected:

```text
PLAYER_UNAVAILABLE: S08
COHORT_LIMIT_EXCEEDED: YEAR_2 has 5, maximum 4
```

### Six Players

```text
111111000
```

Expected:

```text
SQUAD_SIZE_MUST_BE_7
```

### Alternative Valid Selection

```text
011111101
```

This selects S02, S03, S04, S05, S06, S07, and S09.

Counts:

```text
GK = 1
DEF = 2
FWD = 2
UTILITY = 2
YEAR_2 = 3
YEAR_3 = 4
```

Therefore it is valid.

### Multiple Violations

```text
101111101
```

This selects both S01 and S09, selects unavailable S08, and leaves only one defender.

This verifies that multiple independent violations are reported.

## 10. Boundary Testing

Important boundaries were considered:

```text
Squad size:
6 → invalid
7 → valid
8 → invalid
```

```text
Cohort:
4 → valid
5 → invalid
```

```text
Defenders:
1 → invalid
2 → valid
```

```text
Forwards:
1 → invalid
2 → valid
```

```text
Goalkeepers:
0 → invalid
1 → valid
2 → invalid
```

## 11. AI Output Verification

The verification process was:

```text
AI suggestion
    ↓
Compare with problem contract
    ↓
Check data structures
    ↓
Check edge cases
    ↓
Run/test implementation
    ↓
Modify if necessary
```

Important refinements included:

- removing the unnecessary indexed `HashMap`;
- separating `SquadRules` from `SquadStats`;
- keeping validation logic outside `Main`;
- preserving explicit violation ordering;
- adding input-shape validation;
- adding focused test scenarios.


## 12. Code Ownership

AI was used for assistance, but the final implementation is based on decisions I reviewed and understood.

I focused on being able to explain:

- why the roster is an array;
- why selection uses nine binary positions;
- why `SquadRules` and `SquadStats` are separate;
- why validation is isolated in `SquadValidator`;
- why violations are stored in an ordered list;
- why validation order is explicit;
- why the CLI does not contain business rules;
- how the solution could evolve for a larger roster.

The objective was to use AI while maintaining understanding and control over the resulting code.

## 13. Final Workflow

```text
Problem Statement
      ↓
My Initial Approach
      ↓
AI Design Review
      ↓
Question Assumptions
      ↓
Simplify / Modify Design
      ↓
Implement
      ↓
Generate Test Cases
      ↓
Verify Against Requirements
      ↓
Run Tests
      ↓
Prepare Live Modifications
```

The resulting workflow focuses on correctness, simplicity, maintainability, testing, and the ability to explain and modify the solution.
