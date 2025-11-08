# Individual Report — Yerraguntla Bharadwaj

**Bug:** MINIMUM_SPANNING_TREE — ConcurrentModificationException during MST computation.

**Why chosen:** Graph algorithms expose non-trivial correctness issues — the bug required reasoning about mutation during iteration and component merging.

**Manual approach:** 
- Diagnosed that `groupByNode` sets were mutated while iterating.
- Implemented a merged-set approach where the union of components is stored in a single `Set<Node>` and every node in the merged component is mapped to that same set. This avoids concurrent modification and ensures correct future comparisons.

**Automated approach:** 
- Ran MAGPIE (Local Search + GP). MAGPIE produced a best candidate that deleted the inner update call, which improved test results but did not fully repair the bug (best fitness 66.67). This shows MAGPIE can find short patches but may produce semantically incorrect edits.

**Commands & evidence:**
- Manual compile & test:
  javac -cp junit-4.10.jar:. Node.java WeightedEdge.java MINIMUM_SPANNING_TREE.java MINIMUM_SPANNING_TREE_TEST.java
  java -cp junit-4.10.jar:. org.junit.runner.JUnitCore MINIMUM_SPANNING_TREE_TEST

- Files submitted: `bugs/MINIMUM_SPANNING_TREE/*`, `_magpie_logs/*`, `SBSE_results/MST_patch.diff`, `SBSE_logs/*`.

**Reflection:** The manual fix demonstrates algorithmic understanding and preserves program correctness. MAGPIE provided useful insight (it identified the inner update as the cause of the exception), but required human judgement to produce a robust fix.

Signed — Yerraguntla Bharadwaj
