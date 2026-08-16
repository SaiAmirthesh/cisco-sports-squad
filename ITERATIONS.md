# Iterations

## 2026-08-16 - Roster preview before selection

### Change
- Printed the full fixed roster before the CLI asks for the 9-value selection input.

### Implementation
- Added `printRoster(Player[] roster)` in `src/Main.java`.
- Reused the existing `Roster.PLAYERS` data so the preview matches validation input order exactly.
- Kept validation logic unchanged in `SquadValidator`.

### Verification
- The CLI now shows all available players before the input prompt.
- The selection flow and validation output remain unchanged.

## 2026-08-16 - Test case generation matrix

### Change
- Added a 60-case test matrix derived from `docs/05-testing.md` and expanded with binary selection permutations.

### Implementation
- Captured the baseline scenarios from the testing notes.
- Added additional selection vectors covering 7-player, 8-player, 6-player, 2-player, 1-player, and empty selections.
- Included permutations where slot 7 is zero and cases that exercise unavailable-player and cohort-limit paths.

### Verification
- The matrix covers the documented baseline cases.
- The set reaches 60 distinct selection inputs and spans the main rule families.

## 2026-08-16 - JUnit test suite under `test/`

### Change
- Added JUnit test cases under `test/validator/SquadValidatorTest.java`.

### Implementation
- Created 5 explicit tests from the documented scenarios in `docs/05-testing.md`.
- Added a generated permutation block that brings the suite to 60 selection cases.
- Added guard tests for invalid selection length and non-binary input values.
- Marked `test/` as a test source in `.idea/sportsquad.iml`.

### Verification
- The test code follows the existing `SquadValidator` and roster structure.
- The suite covers valid selections, unavailable players, size violations, role minimums, and cohort limits.

## 2026-08-16 - JUnit build configuration

### Change
- Added Maven configuration so JUnit 5 tests compile and run from the project root.

### Implementation
- Added `pom.xml` with `junit-jupiter` as a test dependency.
- Configured Maven to use `src/` for main sources and `test/` for test sources.
- Corrected the IntelliJ module so `test/` is treated as a test source folder.
- Updated `README.md` to document the Maven compile, test, and run workflow.

### Verification
- The project now has a declared JUnit dependency and explicit source directories.
- The README matches the configured build layout.
