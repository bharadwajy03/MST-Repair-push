MINIMUM_SPANNING_TREE — SBSE Assignment

Reproduction steps:
1. Compile manual patched code:
   cd bugs/MINIMUM_SPANNING_TREE
   javac -cp junit-4.10.jar:. Node.java WeightedEdge.java MINIMUM_SPANNING_TREE.java MINIMUM_SPANNING_TREE_TEST.java
   java -cp junit-4.10.jar:. org.junit.runner.JUnitCore MINIMUM_SPANNING_TREE_TEST

2. Run extra tests (if present):
   javac -cp junit-4.10.jar:. *.java
   java -cp junit-4.10.jar:. org.junit.runner.JUnitCore MINIMUM_SPANNING_TREE_TEST MST_EXTRA_TEST

Files of interest:
- bugs/MINIMUM_SPANNING_TREE/MINIMUM_SPANNING_TREE.java      (patched)
- bugs/MINIMUM_SPANNING_TREE/MINIMUM_SPANNING_TREE.java.bak  (original backup)
- SBSE_results/MST_patch.diff                                (manual patch diff)
- _magpie_logs/*                                              (MAGPIE run logs & candidate diff)
- SBSE_logs/*                                                 (compile & test logs)
