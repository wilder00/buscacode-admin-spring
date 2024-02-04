# Spring Data JPA Naming Convention

In Spring Data JPA, you can use method names following a specific naming convention to automatically generate queries. Here is a list of some common keywords and patterns used in method names:

### Keywords for Query Methods:

1. `findBy`: Used to indicate the properties that should be used in the query.
   - Example: `findByUsername(String username)` - finds entities by the `username` property.

2. `getBy`: Similar to `findBy`, used to indicate the properties for the query.
   - Example: `getByUsername(String username)` - gets entities by the `username` property.

3. `readBy`: Similar to `findBy` and `getBy`.

4. `queryBy`: Similar to `findBy`, `getBy`, and `readBy`.

5. `countBy`: Used to count the number of entities based on the specified criteria.
   - Example: `countByUsername(String username)` - counts entities by the `username` property.

6. `deleteBy`: Used to delete entities based on the specified criteria.
   - Example: `deleteByUsername(String username)` - deletes entities by the `username` property.

7. `removeBy`: Similar to `deleteBy`.

### Logical Operators:

1. `And`: Connects two conditions with logical AND.
   - Example: `findByUsernameAndEmail(String username, String email)` - finds entities where both username and email match.

2. `Or`: Connects two conditions with logical OR.
   - Example: `findByUsernameOrEmail(String username, String email)` - finds entities where either username or email matches.

3. `Between`: Finds entities where a property is between two values.
   - Example: `findBySalaryBetween(int minSalary, int maxSalary)` - finds entities with salaries between `minSalary` and `maxSalary`.

### Ordering Results:

1. `OrderBy`: Used to specify the order in which results should be returned.
   - Example: `findByUsernameOrderByCreatedAtDesc(String username)` - finds entities by username and orders the results by `createdAt` in descending order.

### Limiting Results:

1. `First`, `Top`, `First3`, `Top5`, etc.: Limits the number of results returned.
   - Example: `findFirst3ByOrderByCreatedAtDesc()` - finds the first three entities ordered by `createdAt` in descending order.

### Distinct:

1. `Distinct`: Specifies that only distinct results should be returned.
   - Example: `findDistinctByUsername(String username)` - finds distinct entities by username.

### Custom Queries:

1. `@Query`: Use the `@Query` annotation to define custom queries using JPQL.
   - Example: `@Query("SELECT u FROM User u WHERE u.username = :username")` - defines a custom JPQL query to find entities by username.
