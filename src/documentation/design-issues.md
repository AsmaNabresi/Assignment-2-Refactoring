# Design Issues in Original Code

## 1. Violation of Single Responsibility Principle (SRP)
The EventProcessor class performs too many tasks:
- Validation
- Logging
- Dashboard updating
- Event transformation (encrypt, compress, metadata)
- Persistence (saving to DB)
- Handling event-type-specific logic

This makes the class very large, hard to maintain, and impossible to test correctly.


## 2. Violation of Open/Closed Principle (OCP)
Adding new event types requires modifying the EventProcessor (adding new if/else logic).
Adding new transformations also requires modifying the same class.

The system is NOT extensible.

---

## 3. Violation of Strategy Pattern (Transformations)
Encrypting, compressing, and metadata injection are written inside the processor.
This mixes behaviors with business logic.

Better solution: transformation strategies or decorators.

## 4. Violation of Dependency Inversion Principle (DIP)
EventProcessor depends directly on concrete classes:
- new Database()
- new Dashboard()
- new Logger()

It should depend on interfaces.

## 5. Violation of Separation of Concerns
Event-specific logic (USER, SECURITY, SYSTEM) is mixed with general processing.
Should be moved to standalone handlers.


## 6. Code Duplication (especially in SECURITY logic)
SECURITY events have duplicated logic:
- Extra analysis
- Security monitor alerts

This should be abstracted into a handler.

## 7. Hard-coded String Literals
Event types "USER", "SYSTEM", "SECURITY" are hard-coded.
Better: enum or constant.


## 8. Lack of Scalability
If we add:
- new event type
- new transformation (e.g. "sanitize", "normalize", "encrypt-v2")

We must change EventProcessor → violates maintainability.

## 9. Logging Bug
Logger logs event using event.getId(), but ID is generated at the end.
So logs are always printed as “null”.


