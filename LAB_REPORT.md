# Lab Report, Code Quality and Design Quality

Repository URL:

## Tools

| Item | Version or name |
| --- | --- |
| JDK | |
| Maven | |
| IDE | |
| SonarQube for IDE | |
| AI tool and exact model | |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change:

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | java:S106 | Main.java:9 | Replace this use of System.out by a logger. |
| 2 | java:S106 | Main.java:15 | Replace this use of System.out by a logger. |
| 3 | java:S106 | Main.java:17 | Replace this use of System.out by a logger. |
| 4 | java:S106 | Main.java:22 | Replace this use of System.out by a logger. |
| 5 | java:S2143 | ParcelService.java:1 | Use the java.time API for date and time. |
| 6 | java:S1128 | ParcelService.java:6 | Remove this unused import java.util.Date. |
| 7 | java:S1068 | ParcelService.java:12 | Remove this unused maxParcels private field. |
| 8 | java:S106 | ParcelService.java:30 | Replace this use of System.out by a logger. |
| 9 | java:S4973 | ParcelService.java:42 | Strings and Boxed types should be compared using equals(). |
| 10 | java:S106 | ParcelService.java:67 | Replace this use of System.out by a logger. |
| 11 | java:S106 | ParcelService.java:69 | Replace this use of System.out by a logger. |
| 12 | java:S2095 | ParcelService.java:76 | Use try-with-resources or close this FileWriter in a finally clause. |
| 13 | java:S108 | ParcelService.java:80 | Remove this block of code, fill it in, or add a comment explaining why it is empty. |

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date |  24 October 2026|
| AI tool and exact model | ChatGPT, GPT-6 |
| Commit reviewed |87ca963 (Initial commit) |
| How the AI read the files (attached the files, opened the folder, or pasted the files) |Pasted the Java source code into ChatGPT |
| Output file in the reviews folder |reviews/parcelpoint-review.md |

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | Main.java:9 | Uses System.out for console output | SonarQube | java:S106 | Yes. SonarQube flags the use of standard output instead of a logger. |
| 2 | Main.java:15 | Uses System.out for console output | SonarQube | java:S106 | Yes. SonarQube flags the use of standard output instead of a logger. |
| 3 | Main.java:17 | Uses System.out for console output | SonarQube | java:S106 | Yes. SonarQube flags the use of standard output instead of a logger. |
| 4 | Main.java:22 | Uses System.out for console output | SonarQube | java:S106 | Yes. SonarQube flags the use of standard output instead of a logger. |
| 5 | ParcelService.java:1 | Reported use of the java.time API | SonarQube | java:S2143 | No clear confirmation. The supplied source shows an unused java.util.Date import, but does not confirm this finding at line 1. |
| 6 | ParcelService.java:6 | Unused java.util.Date import | SonarQube | java:S1128 | Yes. The import is not used in the supplied code and can be removed. |
| 7 | ParcelService.java:12 | Unused maxParcels field | SonarQube | java:S1068 | Yes. The field is declared but is not used in the supplied code. |
| 8 | ParcelService.java:30 | Uses System.out for console output | SonarQube | java:S106 | Yes. SonarQube flags the use of standard output instead of a logger. |
| 9 | ParcelService.java:25-27 | getLockers() returns the internal lockers collection directly | AI | Encapsulation; exposing an internal collection | Yes. Callers can modify the internal collection through the returned reference. |
| 10 | ParcelService.java:42 | Compares parcel codes using == | SonarQube | java:S4973 | Yes. String values should be compared using equals() rather than reference equality. |
| 11 | ParcelService.java:61-70 | printParcels(boolean forStaff) changes behavior based on a boolean flag | AI | Logical cohesion; boolean flag controlling behavior | Yes. Separating the two output behaviors would make the method's responsibility clearer. |
| 12 | ParcelService.java:67 | Uses System.out for console output | SonarQube | java:S106 | Yes. SonarQube flags the use of standard output instead of a logger. |
| 13 | ParcelService.java:69 | Uses System.out for console output | SonarQube | java:S106 | Yes. SonarQube flags the use of standard output instead of a logger. |
| 14 | ParcelService.java:76 | FileWriter is not closed | SonarQube | java:S2095 | Yes. The original code does not close the writer; try-with-resources is an appropriate fix. |
| 15 | ParcelService.java:80 | Empty catch block ignores IOException | SonarQube | java:S108 | Yes. The exception is silently ignored, making file-saving failures difficult to detect. |

2.1 Name two problems that only the AI review found.
1) ParcelService.java:25–27 — getLockers() returns the internal lockers list directly. Callers can modify the service's internal collection. Suggested fix: return an unmodifiable view or a defensive copy. (2) ParcelService.java:61–70 — printParcels(boolean forStaff) changes its output behavior depending on a boolean flag, giving one method two distinct responsibilities. Suggested fix: separate the staff listing from the general parcel listing.
2.2 Name one problem that only SonarQube found, or write none.
Main.java:9 — System.out is used for console output. SonarQube reports rule java:S106 and recommends using a logger.

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.
A finding that ParcelService has strong coupling simply because it uses Locker and Parcel would be too vague without identifying a specific interaction and explaining why it is tighter than data coupling. The review should identify the exact code location and the coupling type before reporting it.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | Kept | Console output is needed for the command-line interface. |
| 2 | Kept | Console output is needed for the command-line interface. |
| 3 | Kept | Console output is needed for the command-line interface. |
| 4 | Kept | Console output is needed for the command-line interface. |
| 5 | Kept | The reported date/time issue needs verification because the Date import is unused. |
| 6 | Fixed | Removed the unused `java.util.Date` import. |
| 7 | Fixed | Removed the unused `maxParcels` field. |
| 8 | Kept | Console output is needed for the command-line interface. |
| 9 | Fixed | Replaced `==` with `.equals()` when comparing parcel codes. |
| 10 | Kept | Console output is needed for the command-line interface. |
| 11 | Kept | Console output is needed for the command-line interface. |
| 12 | Fixed | Used try-with-resources to close `FileWriter` automatically. |
| 13 | Fixed | Added an error message to the `IOException` catch block. |

Number of SonarQube issues that remain after your fixes:8

The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
|incorrect string comparison| Replaced == with .equals() to compare parcel codes correctly.| Not committed yet |
| Resource management | Used try-with-resources to close FileWriter automatically and added error handling for IOException. | Not committed yet|

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text 
Commands: store <code> <studentId> <size 1-3>, pickup <code> <days>, list, staff, quit
store P1 6501 2
SMS to 6501: parcel P1 is in locker 2
Locker 2
pickup P1 5
Fee: 30.0
staff
P1 6501 PICKED_UP
Free lockers: 3
quit

Content of parcels.csv:

P1,PICKED_UP

```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
|---|---|---|
| Number of Java files | 1 (`Main.java`) | 9 Java files |
| Main design | Menu, bike data, business rules, and file saving are combined in one class | Responsibilities are separated into `Main`, `BikeShareService`, model classes, and repository classes |
| Shared data | Uses public static fields for bikes, rides, and bike IDs | Uses private fields and controlled access methods |
| Member types | Uses integer codes (`1` for student, `2` for staff) | Uses the `MemberType` enum |
| Method behavior | Uses a boolean `renting` parameter to switch between renting and returning | Uses separate `rent()` and `returnBike()` methods |
| File saving | Saves CSV records directly inside `Main.process()` | Uses the `RideRepository` interface and `CsvRideRepository` implementation |
| Encapsulation | `Bike` fields are not private | Model fields are private |
| Build result | `mvn clean package` — BUILD SUCCESS | `mvn clean package` — BUILD SUCCESS |
| Test performed | Student M001 rented a bike at Library and returned it at Dorm after 15 minutes. Fee: 30 baht. The ride was saved to `rides.csv`. | Student M001 rented at Library and returned at Dorm after 15 minutes. Fee: 30 baht. Staff M002 rented at Canteen and returned at Library after 10 minutes. Fee: 30 baht. |
| AI tool / model / date | ChatGPT / GPT-6 / 2026-10-24 | ChatGPT / GPT-6 / 2026-10-24 |

**Observations:** The poor design combines responsibilities and exposes shared state, making the program harder to maintain and test. The good design improves separation of concerns, encapsulation, and dependency management through an interface. Potential improvements remain, including handling end-of-input gracefully and preventing inconsistent bike state if saving a ride record fails.
| Date | 2026-10-24 | 2026-10-24 |
| AI tool and exact model | ChatGPT / GPT-6 | ChatGPT / GPT-6 |
| Prompt (B or C as given, or your changed prompt in full) | Prompt B — paste the exact prompt used | Prompt C — paste the exact prompt used |
| Builds with mvn package (yes or no) | Yes | Yes |
| Number of classes, interfaces, enums and records | Not counted yet | Not counted yet |
| SonarQube issues, total | Not checked | Not checked |
| SonarQube issues, by rule | Not checked | Not checked |
| AI review findings in Check 1 | Not completed / not recorded | Not completed / not recorded |
| AI review findings in Check 2 | Not completed / not recorded | Not completed / not recorded |
| Review output file in the reviews folder | Not confirmed | Not confirmed |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.
SonarQube results have not been verified, so I cannot confirm a specific rule or quote its exact message. The design of bikeshare-poor combines multiple responsibilities in one class, which may indicate low cohesion and make the code harder to maintain.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.
Yes. In bikeshare-poor, Main.java combines menu handling, bike management, rental and return logic, and CSV file saving in one class. In contrast, bikeshare-good separates these responsibilities into service, model, and repository classes.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?
 would choose bikeshare-good because it uses a MemberType enum and separates business logic from the user interface and file storage. Adding a visitor type with a rate of 4 baht per minute would be easier to implement, test, and maintain. In bikeshare-poor, integer member-type codes and combined responsibilities would make the changes more difficult and increase the risk of errors.

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.
SonarQube checks code quality issues such as bugs, code smells, maintainability problems, and certain security vulnerabilities. In this lab, it can help identify problems in the Java code, such as duplicated code or methods that are too complex. However, it does not fully understand the system requirements or determine whether the design is appropriate for every situation. For example, it may not recognize all the design problems in bikeshare-poor, such as combining menu handling, business logic, and file saving in one class, or decide whether bikeshare-good is easier to extend with a new member type. Therefore, SonarQube results should be considered together with manual review and testing.
5.2 When should you not trust the AI review? Give an example from this lab.
We should not trust an AI review without checking its findings against the actual source code. AI may report a problem that does not exist, miss a real problem, or provide an incorrect file name or line number. For example, the AI review may describe bikeshare-poor as having low cohesion because several responsibilities are combined in Main.java. This is a reasonable design concern, but we should verify the code and evidence before accepting the finding. We should also compare the review with build and test results.

5.3 What will you do differently in Project Phase 2 after this lab?
In Project Phase 2, I will separate responsibilities into appropriate classes, use private fields to protect data, and use clear interfaces to reduce coupling. I will also avoid using integer codes or boolean parameters when enums or separate methods make the design clearer. I will run builds and tests after making changes, use SonarQube to identify potential code quality issues, and treat AI reviews as suggestions that must be verified before applying them.

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| ChatGPT / GPT-6| Code review, code improvement, and report writing|ParcelService.java, bikeshare-poor, bikeshare-good, and LAB_REPORT.md |Ran mvn clean package successfully, tested bike rental and return operations, checked fee calculations and CSV output, and manually reviewed the code. |
