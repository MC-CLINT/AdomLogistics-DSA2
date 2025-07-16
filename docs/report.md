
# Adom Logistics Dispatcher System

### Detailed Report on Algorithm & Data Structure Choices

**Prepared by Group 52 for Adom Logistics Group Project**



## 1. **Introduction**

The **Adom Logistics Dispatcher System** was designed to simulate and manage core operations in a typical logistics company. These include vehicle tracking, driver assignment, delivery monitoring, maintenance scheduling, and fuel efficiency analysis.

This report explains the rationale behind the selection of data structures and algorithms, analyzes their time complexities, and discusses how they model real-world logistics operations.



## 2. **Data Structure Choices & Justifications**

### a. **Vehicle Management – ArrayList with File Storage**

* **Data Structure:**

  * `ArrayList<Vehicle>` used in `FileHandler` and `CLI`.

* **Why ArrayList?**

  * **Dynamic Resizing:** Allows addition/removal of vehicles at runtime.
  * **Ease of Iteration:** Required for operations like list, search, and update.
  * **Sufficient for Small Datasets:** For fleets under a few hundred vehicles, the linear search performance is acceptable.

* **Persistence Layer:**

  * Vehicle data is saved in `vehicles.txt` in a readable, serialized format.
  * Manual file handling allows transparency in debugging and easy migration to a database in future versions.

* **Real-World Reflection:**

  * Mimics how dispatchers maintain vehicle logs in registries or spreadsheets.



### b. **Driver Management – HashMap, ArrayList & Custom Queue**

* **HashMap\<String, Driver>:**

  * Enables O(1) retrieval by unique driver ID.
  * Supports quick updates, infraction tracking, and driver lookup.

* **ArrayList<Driver>:**

  * Maintains a list of all drivers for listing, updates, and replacements.
  * Provides a simple, ordered collection for administrative purposes.

* **DriverQueue (Custom Queue):**

  * Enforces **First-In-First-Out (FIFO)** logic for fair assignment of drivers.
  * Models real-world driver dispatching logic where idle drivers are cycled fairly.

* **Real-World Reflection:**

  * The combination of HashMap and Queue replicates how logistics managers both lookup driver details and rotate driver assignments.



### c. **Delivery Tracking – ArrayList**

* **Why ArrayList?**

  * Provides simple, ordered storage of delivery records.
  * Suitable for sequential operations like list, update, or remove.
* **Real-World Reflection:**

  * Acts as a delivery manifest that is constantly updated.



### d. **Maintenance Records – List<String> inside Vehicle Object**

* **Why List of Strings?**

  * Simple and flexible way to store multiple maintenance records.
  * Allows appending without modifying the underlying structure.

* **Real-World Reflection:**

  * Represents physical maintenance logs recorded for every vehicle.



## 3. **Algorithm Choices & Complexity Analysis**

### a. **Sorting – Insertion Sort on Mileage**

* **Reason for Selection:**

  * Simple and efficient for small datasets (< 500 entries).
  * Maintains stability of records (relative order of equal elements).

* **Use Case:**

  * Sorting vehicles based on mileage for reports.

* **Complexity:**

  * **Best Case (sorted):** O(n)
  * **Average/Worst Case:** O(n²)



### b. **Filtering & Searching – Linear Search**

* **Why Linear Search?**

  * Straightforward to implement.
  * Suitable for modest dataset sizes.
  * Transparent logic, easier to debug or modify.

* **Use Cases:**

  * Searching for drivers, deliveries, maintenance records.
  * Filtering out fuel-inefficient vehicles or efficient fleet candidates.

* **Complexity:**

  * **O(n)** for each search or filter operation.


### c. **Fuel Outlier Detection – Mean-Based Thresholding**

* **Approach:**

  * Calculate average fuel usage.
  * Mark vehicles exceeding 125% of the average as outliers.

* **Why This Approach?**

  * Simple statistical check effective for small data volumes.
  * Easy to explain and understand.


## 4. **System Design & Real-World Mapping**

| Dispatcher Action            | System Feature                            | Data Structure     |
| ---------------------------- | ----------------------------------------- | ------------------ |
| Add new vehicle/driver       | CLI → FileHandler → Storage               | ArrayList, HashMap |
| Assign next available driver | Queue → Dequeue driver                    | Custom DriverQueue |
| Lookup driver by ID          | HashMap lookup                            | HashMap            |
| Track delivery assignments   | CLI linked with FileHandler               | ArrayList          |
| Record vehicle maintenance   | In-vehicle maintenance list + file update | List<String>       |
| Analyze fuel efficiency      | FuelReportAnalyzer with filtering/sorting | ArrayList          |



## 5. **Time Complexity Summary**

| Operation                | Data Structure    | Complexity |
| ------------------------ | ----------------- | ---------- |
| Add Vehicle/Driver       | ArrayList/HashMap | O(1)       |
| Find Vehicle             | ArrayList         | O(n)       |
| Find Driver              | HashMap           | O(1)       |
| Assign Driver            | Queue             | O(1)       |
| Remove Vehicle/Driver    | ArrayList         | O(n)       |
| Filter/Outlier Check     | Linear Search     | O(n)       |
| Sort Vehicles by Mileage | Insertion Sort    | O(n²)      |


## 6. **Scalability Consideration**

While current structures are optimized for simplicity and moderate data sizes, future scalability would require:

* Moving from text files to a **relational database (e.g., SQLite, MySQL)**.
* Using **Binary Search Trees (BST)** or **PriorityQueues** for prioritized dispatch.
* Implementing **HashSet** for fast uniqueness checks.
* Adopting more efficient sorting algorithms like **Merge Sort** or **QuickSort**.



## 7. **Conclusion**

The system’s design aligns with the goal of modeling a real-world dispatcher’s core tasks using straightforward, well-understood data structures and algorithms. The simplicity favors clarity and ease of maintenance, a priority in early-stage software projects.

The design achieves:

* Realistic driver dispatch rotation.
* Practical vehicle and delivery management.
* Basic reporting with meaningful insights on performance.

**Future Enhancements Could Include:**

* Integration with databases for scalable storage.
* Real-time analytics dashboards.
* REST APIs for external access.
* Automated scheduling algorithms for optimized dispatching.


