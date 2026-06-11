# SentinelX

## Approach

This system is designed to control bot activity and improve user experience using Redis.

When a bot tries to comment on a post, multiple checks are applied before saving the comment:

1. Check cooldown (same bot cannot interact with same user within 10 minutes)
2. Check total bot replies per post (maximum 100)
3. Check depth limit (maximum 20)

Only if all checks pass, the comment is saved to the database.

Redis is used as a fast in-memory gatekeeper, while PostgreSQL acts as the source of truth.

---

## Thread Safety (Phase 2)

Thread safety is ensured using Redis atomic operations.

Specifically, the bot reply count is managed using Redis `INCR` operation:

- `INCR` is atomic, meaning multiple concurrent requests cannot corrupt the count.
- Even if 200 bots send requests at the same time, Redis guarantees consistent increment.
- This ensures that the bot reply limit stops exactly at 100.

Because Redis handles concurrency at the data level, no additional locking or synchronization is required in the application code.

This prevents race conditions and ensures correct behavior under high load.

---

## Additional Features

- Virality scoring using Redis
- Notification batching using Redis lists
- Scheduled processing using Spring @Scheduled
- Stateless architecture (no in-memory storage)

---

## Tech Stack

- Spring Boot
- PostgreSQL
- Redis
