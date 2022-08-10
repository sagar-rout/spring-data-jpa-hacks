# Best practices

1. Always prefer fetch in LAZY mode over EAGER. Use JOIN FETCH to read children in LAZY mode.
2. Use DTO projection over entity projection when no write operation needed.
3. In case of read operation use `@Transactional(readOnly=true)` to disable hibernate dirty checking.
4. Don't use save method to update already managed hibernate object.
5. Check Query performance always using explain analyze plan. Be careful with `analyze` part for write operation like UPDATE and DELETE.
6. Use db-util to check no. of calls we are making in progress. Refer CFD.
7. Use JPQL or native queries to perform delete by some other queries 
```sql
DELETE from table where table.some_id = 'some_id';
```
if some_id has 100 objects associated, it can load up to 100 objects in hibernate memory to pass through hibernate entity listener
so use JPQL/HQL or derived queries. Be aware that your hibernate listener will it work (for e.g. message sending).
8. With CascadeType.ALL and SELECT call will make all JOIN in case of manually providing ID.
