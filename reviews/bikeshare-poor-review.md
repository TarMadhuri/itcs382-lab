# AI Review: bikeshare-poor

Tool: ChatGPT / GPT-6
Review date: 2026-10-25
Review basis: Java source code supplied for review.

## Files read

- bikeshare-poor/src/main/java/bikeshare/Main.java

## Findings

| No. | Check | Code location (file:line) | Finding | Severity | Suggested fix |
|---:|---|---|---|---|---|
| 1 | Check 1 | Main.java:46 | `scanner.nextLine()` can throw `NoSuchElementException` when standard input reaches EOF, causing an abrupt exit. | Should fix | Check `scanner.hasNextLine()` before reading input. |
| 2 | Check 1 | Main.java:91-130 | `process()` combines rental and return workflows, fee calculation, receipt printing and file writing, making it difficult to understand and maintain. | Should fix | Split rental and return operations into separate methods and move file saving into a separate class. |
| 3 | Check 1 | Main.java:107-108 | The return operation accepts non-positive ride durations, which can produce zero or negative fees. | Should fix | Reject ride durations less than or equal to zero before calculating the fee. |
| 4 | Check 1 | Main.java:120-124 | If saving a ride fails, the bike has already been marked available and the ride is still reported to the user, although the file was not updated. | Should fix | Handle the save failure before treating the return as successfully completed. |
| 5 | Check 2 | Main.java:11-13 | Public static mutable fields expose the bike list, ride list and ID counter as shared state. | Must fix | Make the fields private and manage them through controlled operations. |
| 6 | Check 2 | Main.java:16-19 | The `Bike` fields are not private, so other code in the enclosing class can directly change bike state. | Should fix | Make the fields private and provide methods for valid state changes. |
| 7 | Check 2 | Main.java:15-27 | The nested `Bike` class and `Main` combine bike data and command-line application responsibilities in one source file. | Should fix | Move `Bike` into its own class. |
| 8 | Check 2 | Main.java:91-105 | `process()` uses a boolean `renting` flag to choose between two different workflows; this is control coupling and procedural cohesion. | Should fix | Replace the flag with separate `rent()` and `returnBike()` methods. |
| 9 | Check 2 | Main.java:55-71 | Member types are represented by integer codes, and the same numeric convention is used later to determine the fee. | Should fix | Use a `MemberType` enum and associate the fee rate with the type. |
| 10 | Check 2 | Main.java:94-124 | The `Main` class directly manages bike state, business rules, receipts and CSV persistence, indicating weak class cohesion. | Should fix | Extract bike operations, fee calculation and file persistence into separate classes. |

## Finding counts

- Check 1: 4 findings
- Check 2: 6 findings

