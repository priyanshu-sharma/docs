What are slowly changing dimensions?

When organising a datawarehouse into Kimball-style star schemas, you relate fact records to a specific dimension record with its related attributes. But what if the information in the dimension changes? Do you now associate all fact records with the new value? Do you ignore the change to keep historical accuracy? Or do you treat facts before the dimension change differently to those after?

It is this decision that determines whether to make your dimension a slowly changing one. There are several different types of SCD depending on how you treat incoming change.

What are the types of SCD?

Very simply, there are 6 types of Slowly Changing Dimension that are commonly used, they are as follows:

Type 0 – Fixed Dimension
No changes allowed, dimension never changes
Type 1 – No History
Update record directly, there is no record of historical values, only current state
Type 2 – Row Versioning
Track changes as version records with current flag & active dates and other metadata
Type 3 – Previous Value column
Track change to a specific attribute, add a column to show the previous value, which is updated as further changes occur
Type 4 – History Table
Show current value in dimension table but track all changes in separate table
Type 6 – Hybrid SCD
Utilise techniques from SCD Types 1, 2 and 3 to track change
In reality, only types 0, 1 and 2 are widely used, with the others reserved for very specific requirements. Confusingly, there is no SCD type 5 in commonly agreed definitions.