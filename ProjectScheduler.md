Here are a few example inputs you can use with the modified `ProjectScheduler` Java application. Each example includes the number of tasks, their durations, dependencies, and the starting task. The expected output is also provided for each case.

### Test Case 1: Simple Linear Dependency

**Inputs:**
```
Enter the number of tasks:
3
Enter task ID and duration for task 1:
1 3
Enter task ID and duration for task 2:
2 2
Enter task ID and duration for task 3:
3 4
Enter the number of dependencies:
2
Enter dependency (taskID dependentOnTaskID):
2 1
Enter dependency (taskID dependentOnTaskID):
3 2
Enter the starting task ID:
1
```

**Expected Output:**
```
Earliest time all tasks will be completed: 9
Latest time all tasks will be completed: 9
```

**Explanation:**
- Task 1: Duration 3 (EST = 0, EFT = 3)
- Task 2: Duration 2 (EST = 3, EFT = 5)
- Task 3: Duration 4 (EST = 5, EFT = 9)
- The longest path determines the earliest and latest completion times.

### Test Case 2: Branching Dependencies

**Inputs:**
```
Enter the number of tasks:
4
Enter task ID and duration for task 1:
1 2
Enter task ID and duration for task 2:
2 3
Enter task ID and duration for task 3:
3 1
Enter task ID and duration for task 4:
4 4
Enter the number of dependencies:
3
Enter dependency (taskID dependentOnTaskID):
2 1
Enter dependency (taskID dependentOnTaskID):
3 1
Enter dependency (taskID dependentOnTaskID):
4 2
Enter the starting task ID:
1
```

**Expected Output:**
```
Earliest time all tasks will be completed: 8
Latest time all tasks will be completed: 8
```

**Explanation:**
- Task 1: Duration 2 (EST = 0, EFT = 2)
- Task 2: Duration 3 (EST = 2, EFT = 5)
- Task 3: Duration 1 (EST = 2, EFT = 3)
- Task 4: Duration 4 (EST = 5, EFT = 9)
- The longest path determines the earliest and latest completion times.

### Test Case 3: Multiple Paths

**Inputs:**
```
Enter the number of tasks:
5
Enter task ID and duration for task 1:
1 3
Enter task ID and duration for task 2:
2 2
Enter task ID and duration for task 3:
3 4
Enter task ID and duration for task 4:
4 1
Enter task ID and duration for task 5:
5 3
Enter the number of dependencies:
4
Enter dependency (taskID dependentOnTaskID):
2 1
Enter dependency (taskID dependentOnTaskID):
3 1
Enter dependency (taskID dependentOnTaskID):
4 2
Enter dependency (taskID dependentOnTaskID):
5 3
Enter the starting task ID:
1
```

**Expected Output:**
```
Earliest time all tasks will be completed: 10
Latest time all tasks will be completed: 10
```

**Explanation:**
- Task 1: Duration 3 (EST = 0, EFT = 3)
- Task 2: Duration 2 (EST = 3, EFT = 5)
- Task 3: Duration 4 (EST = 3, EFT = 7)
- Task 4: Duration 1 (EST = 5, EFT = 6)
- Task 5: Duration 3 (EST = 7, EFT = 10)
- Task 5 has the longest path.

### Test Case 4: Complex Dependencies

**Inputs:**
```
Enter the number of tasks:
6
Enter task ID and duration for task 1:
1 1
Enter task ID and duration for task 2:
2 2
Enter task ID and duration for task 3:
3 3
Enter task ID and duration for task 4:
4 4
Enter task ID and duration for task 5:
5 2
Enter task ID and duration for task 6:
6 1
Enter the number of dependencies:
5
Enter dependency (taskID dependentOnTaskID):
2 1
Enter dependency (taskID dependentOnTaskID):
3 1
Enter dependency (taskID dependentOnTaskID):
4 2
Enter dependency (taskID dependentOnTaskID):
5 4
Enter dependency (taskID dependentOnTaskID):
6 5
Enter the starting task ID:
1
```

**Expected Output:**
```
Earliest time all tasks will be completed: 12
Latest time all tasks will be completed: 12
```

**Explanation:**
- Task 1: Duration 1 (EST = 0, EFT = 1)
- Task 2: Duration 2 (EST = 1, EFT = 3)
- Task 3: Duration 3 (EST = 1, EFT = 4)
- Task 4: Duration 4 (EST = 3, EFT = 7)
- Task 5: Duration 2 (EST = 7, EFT = 9)
- Task 6: Duration 1 (EST = 9, EFT = 10)
- The longest path determines the earliest and latest completion times.

### Test Case 5: No Dependencies

**Inputs:**
```
Enter the number of tasks:
3
Enter task ID and duration for task 1:
1 2
Enter task ID and duration for task 2:
2 3
Enter task ID and duration for task 3:
3 1
Enter the number of dependencies:
0
Enter the starting task ID:
1
```

**Expected Output:**
```
Earliest time all tasks will be completed: 3
Latest time all tasks will be completed: 3
```

**Explanation:**
- Since there are no dependencies, tasks can be completed in parallel.
- The longest task duration determines the earliest and latest completion times.

These test cases cover various scenarios including simple linear dependencies, branching dependencies, multiple paths, complex dependencies, and no dependencies. They should help in validating that the `ProjectScheduler` implementation correctly handles different project scheduling scenarios.