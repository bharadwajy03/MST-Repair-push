# Group Report — MINIMUM_SPANNING_TREE Bug Repair

## Summary
We were assigned the MINIMUM_SPANNING_TREE bug from the automated software engineering repository. The program crashed with `java.util.ConcurrentModificationException` during its MST construction. We analyzed the code, implemented a correct manual repair, ran automated repair tools (MAGPIE), compared results, and packaged evidence.

## Symptom & Diagnosis
Stack trace: `java.util.ConcurrentModificationException` at `MINIMUM_SPANNING_TREE.minimum_spanning_tree` (line 26). Root cause: code iterated over a `Set` while simultaneously modifying it inside the loop (calls to `update()`), causing the iterator to throw.

## Manual Fix
The original logic attempted to merge node groups by repeatedly updating sets while iterating through them. Our manual fix:
- Create a merged `Set<Node>` containing both groups.
- Update the `groupByNode` map so every node in the merged component points to the same `merged` set.
This prevents concurrent modification and ensures consistent component mapping.

Patch: `SBSE_results/MST_patch.diff`.

## Automated Repair (MAGPIE)
We ran MAGPIE Local Search and Genetic Programming on a copy of the original buggy code. MAGPIE's best candidate was a `SrcmlStmtDeletion` that **removed the inner update call inside the per-node loop**. This prevented the `ConcurrentModificationException` but broke algorithm correctness: best fitness = 66.67 (partial success). The MAGPIE diff and logs are in `_magpie_logs/`.

## Validation
- Manual patch: All original unit tests pass (OK — 3 tests) and extra edge-case tests (disconnected graphs, duplicate weights) passed.
- MAGPIE candidate: improved test pass rate but left at least one failing test (demonstrating a partial/brittle repair).

## Conclusion & Lessons
- Automated repair can suggest quick fixes that remove exceptions but may not preserve program semantics. Manual reasoning produced a correct, robust repair.
- For future work, replacing the current set-based union with a union-find (disjoint set) with path compression is recommended to improve performance and clarity.

## Evidence included
- Manual diff: `SBSE_results/MST_patch.diff`
- MAGPIE logs and diffs: `_magpie_logs/*`
- Compile/test logs: `SBSE_logs/*`
