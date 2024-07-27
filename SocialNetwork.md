### Code Explanation

#### 1. **Class and Data Structures**

- **`SocialNetwork` Class:**
  - **`friendsMap`:** A `Map` where the key is a person's name and the value is a `Set` of friends' names. This represents the friendships in the network.

#### 2. **Methods**

- **`addFriend(String person1, String person2)`:**
  - Adds a bidirectional friendship between `person1` and `person2` in the `friendsMap`.
  - Uses `computeIfAbsent` to initialize the friend lists if they don't already exist.

- **`getFriends(String person)`:**
  - Retrieves the set of friends for the specified person from `friendsMap`.

- **`findCommonFriends(String person1, String person2)`:**
  - Finds and returns the intersection of the friends of `person1` and `person2`.

- **`findNthConnection(String start, String end)`:**
  - Uses Breadth-First Search (BFS) to find the shortest path (in terms of the number of connections) from `start` to `end`.
  - Returns the number of connections or `-1` if no path exists.

#### 3. **Main Method**

- **User Interaction:**
  - Displays a menu and prompts the user to select an option.
  - Handles adding friends, finding friends, common friends, and nth connections based on user input.
  - Uses `Scanner` to read user inputs and perform corresponding operations.

### Sample Input and Output

#### Input Scenario

1. **Adding Friends:**
   - Add the following connections:
     - Alice and Bob
     - Bob and Janice
     - Janice and Karen

2. **Finding All Friends of a Person:**
   - Find friends of Alice.

3. **Finding Common Friends:**
   - Find common friends between Alice and Bob.

4. **Finding nth Connection:**
   - Find the nth connection between Alice and Janice.
   - Find the nth connection between Alice and Bob.
   - Find the nth connection between Alice and Karen.
   - Find the nth connection between Alice and Dave (who is not in the network).

#### Example Execution

**Sample Input:**

```
1
Alice
Bob
2
Alice
3
Alice
Bob
4
Alice
Janice
Alice
Bob
Alice
Karen
Alice
Dave
5
```

**Explanation of Input:**

1. **Option 1: Add friends**
   - Adds Alice-Bob, Bob-Janice, Janice-Karen to the network.

2. **Option 2: Find all friends**
   - Finds friends of Alice, which are [Bob].

3. **Option 3: Find common friends**
   - Finds common friends between Alice and Bob, which are [] (no common friends).

4. **Option 4: Find nth connection**
   - Finds the nth connection between:
     - Alice and Janice (2 connections: Alice -> Bob -> Janice)
     - Alice and Bob (1 connection: Alice -> Bob)
     - Alice and Karen (3 connections: Alice -> Bob -> Janice -> Karen)
     - Alice and Dave (no connection: -1)

**Sample Output:**

```
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 1
Enter the first person: Alice
Enter the second person: Bob
Friendship added.
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 1
Enter the first person: Bob
Enter the second person: Janice
Friendship added.
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 1
Enter the first person: Janice
Enter the second person: Karen
Friendship added.
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 2
Enter the person whose friends you want to find: Alice
Friends of Alice: [Bob]
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 3
Enter the first person: Alice
Enter the second person: Bob
Common friends: []
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 4
Enter the start person: Alice
Enter the end person: Janice
The nth connection is: 2
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 4
Enter the start person: Alice
Enter the end person: Bob
The nth connection is: 1
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 4
Enter the start person: Alice
Enter the end person: Karen
The nth connection is: 3
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 4
Enter the start person: Alice
Enter the end person: Dave
No connection found.
1. Add a friend
2. Find all friends of a person
3. Find common friends between two people
4. Find nth connection between two people
5. Exit
Choose an option: 5
Exiting...
```

### Summary

- **Adding Friends:** Creates bidirectional relationships between users.
- **Finding Friends:** Lists friends of a specified user.
- **Finding Common Friends:** Identifies mutual friends between two users.
- **Finding nth Connection:** Determines the shortest path in terms of connections between two users.

This approach ensures that all interactions are handled dynamically based on user input, providing a comprehensive way to manage and query a social network.