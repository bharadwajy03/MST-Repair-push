# 🧩 MST-Repair Project — Automated Software Repair using MAGPIE

## 📖 Overview
This project demonstrates the **automated repair of a software bug** in the `MINIMUM_SPANNING_TREE` algorithm using **MAGPIE** (Metaheuristic Automatic Genetic Programming for Improvement and Evolution).

The repair was performed on the buggy Java implementation of the Minimum Spanning Tree problem, which originally threw a `ConcurrentModificationException`. Using **local search** and **genetic programming** approaches, MAGPIE successfully identified a patch that fixed the issue.

---

## ⚙️ Technologies Used
- **Operating System**: Kali Linux VM  
- **Programming Language**: Java (JUnit for testing)  
- **Repair Framework**: [MAGPIE](https://github.com/anonymous-magpie/magpie)  
- **Python Version**: 3.10+  
- **Git & GitHub**: For version control and project documentation  

---

## 🧪 Experiment Steps
1. **Setup Environment**
   - Installed Python, Java, and MAGPIE dependencies.
   - Configured the `MINIMUM_SPANNING_TREE` scenario file.

2. **Initial Compilation and Testing**
   - Verified compilation using:
     ```bash
     javac -cp junit-4.10.jar:. *.java
     java -cp junit-4.10.jar:. org.junit.runner.JUnitCore MINIMUM_SPANNING_TREE_TEST
     ```
   - All 3 tests initially **failed** with `ConcurrentModificationException`.

3. **Automated Repair using MAGPIE**
   - Executed Local Search:
     ```bash
     PYTHONPATH=. python3 magpie/bin/local_search.py --scenario bugs/MINIMUM_SPANNING_TREE/_magpie/scenario.txt --seed 1
     ```
   - MAGPIE identified a valid patch that removed unsafe concurrent map iteration.

4. **Patch Generated**
   ```diff
   --- before: MINIMUM_SPANNING_TREE.java
   +++ after: MINIMUM_SPANNING_TREE.java
   @@ -24,7 +24,7 @@
                    for (Node node : groupByNode.get(vertex_v)) {
   -                    groupByNode = update(groupByNode, node, vertex_u);
   +                    
                    }

