# Assignment1_daa
# Algorithm Benchmarks

This project benchmarks several divide-and-conquer algorithms in Java:
- **MergeSort**
- **QuickSort (randomized pivot)**
- **Deterministic Select (Median-of-Medians)**
- **Closest Pair of Points**

Metrics (comparisons, allocations, recursion depth, runtime) are tracked and written to `results.csv`.

---

## ⚙️ Architecture Notes
- **Metrics**: counts comparisons, allocations, recursion depth, and time.
- **Depth control**: each recursive call increments a counter, and the maximum is tracked (`enterRecursion()` / `leaveRecursion()`).
- **Allocations**: tracked whenever new buffers/arrays are created (e.g., MergeSort temporary buffer).
- **CSV export**: `MetricsWriter` collects results for reproducibility.

---

## 📐 Recurrence Analysis

### MergeSort
- Recurrence: `T(n) = 2T(n/2) + Θ(n)` (merging step).
- By Master Theorem: `a=2, b=2, f(n)=n → T(n) = Θ(n log n)`.
- Depth: Θ(log n), allocations from buffer arrays.

### QuickSort
- Recurrence: `T(n) = T(k) + T(n-k-1) + Θ(n)` with randomized pivot.
- Expected case: Θ(n log n). Worst case: Θ(n²).
- Depth: Θ(log n) expected, Θ(n) worst case. No large allocations (in-place).

### Deterministic Select (Median-of-Medians)
- Recurrence: `T(n) = T(n/5) + T(7n/10) + Θ(n)`.
- By Akra–Bazzi: `T(n) = Θ(n)`.
- Depth: Θ(log n), no big allocations beyond partitions.

### Closest Pair
- Recurrence: `T(n) = 2T(n/2) + Θ(n)` (merge-like combine step).
- Same as MergeSort → `Θ(n log n)`.
- Depth: Θ(log n), allocations minimal.

---

## 📊 Plots

- **Time vs n**:  
  - MergeSort & ClosestPair scale as `n log n`.  
  - QuickSort average matches MergeSort but shows variance.  
  - DeterministicSelect linear.  

- **Depth vs n**:  
  - MergeSort and ClosestPair: log₂(n).  
  - QuickSort: fluctuates (random pivot).  
  - Select: linear passes, but logarithmic depth.  

- **Constant-factor effects**:  
  - MergeSort slower due to extra buffer allocations (cache impact).  
  - QuickSort benefits from cache locality.  
  - Select has larger constants (grouping into 5s).  
  - GC can occasionally spike runtimes.

---

## 📌 Summary
- Theoretical bounds mostly align with measurements:
  - `MergeSort` and `ClosestPair`: `Θ(n log n)` confirmed.  
  - `QuickSort`: expected `Θ(n log n)` but sometimes slower (bad pivots).  
  - `Deterministic Select`: linear but with higher constants.  
- Small mismatches due to caching, memory allocation, and JVM GC effects.
