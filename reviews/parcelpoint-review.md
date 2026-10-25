# AI Review: ParcelPoint

Tool: ChatGPT / GPT-6
Review date: 2026-10-25
Review basis: Java source code supplied for review.

## Files read

- parcelpoint/src/main/java/parcelpoint/Main.java
- parcelpoint/src/main/java/parcelpoint/ParcelService.java
- parcelpoint/src/main/java/parcelpoint/Parcel.java
- parcelpoint/src/main/java/parcelpoint/Locker.java

## Findings

| No. | Check | Code location (file:line) | Finding | Severity | Suggested fix |
|---:|---|---|---|---|---|
| 1 | Check 1 | Main.java:11-12 | The input is split and indexed without checking whether the command contains enough arguments, so malformed input can cause an exception. | Should fix | Validate the command and its argument count before accessing array elements. |
| 2 | Check 1 | Main.java:12-13 | The command loop parses user input directly, and invalid numeric input can cause `NumberFormatException`. | Should fix | Catch invalid numeric input and display a helpful error message. |
| 3 | Check 1 | ParcelService.java:11 | `maxParcels` is declared but not used. | Note | Remove the unused field or enforce the intended parcel limit. |
| 4 | Check 1 | ParcelService.java:5 | The `java.util.Date` import is unused. | Note | Remove the unused import. |
| 5 | Check 1 | ParcelService.java:21-23 | `getLockers()` returns the mutable internal collection directly, allowing callers to change the service's collection. | Should fix | Return a defensive copy or an unmodifiable view. |
| 6 | Check 1 | ParcelService.java:61-70 | `printParcels(boolean forStaff)` combines two output behaviors in one method and branches on a boolean flag. | Should fix | Separate the staff listing from the general parcel listing. |
| 7 | Check 1 | ParcelService.java:76-82 | File-writing errors are caught and printed, but the caller is not told that saving failed and may continue as if the operation succeeded. | Should fix | Return or propagate the save result so the caller can handle failure. |
| 8 | Check 2 | Main.java:6-29 | `main()` handles command input, parsing, dispatch and output, indicating procedural cohesion. | Should fix | Extract command parsing and handling into a separate component. |
| 9 | Check 2 | ParcelService.java:25-35 | `storeParcel()` updates locker and parcel state, prints an SMS message and saves a file, combining several responsibilities and indicating procedural cohesion. | Should fix | Separate storage rules, notification output and persistence. |
| 10 | Check 2 | ParcelService.java:38-48 | `pickUp()` updates locker and parcel state, saves the file and calculates the fee, combining multiple steps and indicating procedural cohesion. | Should fix | Separate pickup state changes, persistence and fee calculation. |
| 11 | Check 2 | ParcelService.java:61-70 | `printParcels(boolean forStaff)` has logical cohesion because it performs different kinds of listing based on a flag. | Should fix | Provide separate methods for each listing behavior. |
| 12 | Check 2 | Parcel.java:9 | The `status` field is public, allowing code outside the class to change it directly. | Should fix | Make `status` private and expose controlled state-changing methods. |
| 13 | Check 2 | ParcelService.java:25-35 | `ParcelService` directly prints a notification and writes a CSV file in addition to managing parcel operations, indicating weak class cohesion. | Should fix | Move notification and file persistence responsibilities into separate components. |
| 14 | Check 2 | Main.java:23-25 | `Main` depends on the concrete `ParcelService` API and calls through it to obtain lockers and inspect their state for the staff display. | Note | Provide a focused service operation for the staff summary instead of exposing locker details to the UI. |

## Finding counts

- Check 1: 7 findings
- Check 2: 7 findings

