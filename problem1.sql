SELECT student.id, student.student_name
FROM student
INNER JOIN class
ON class.class_title = student.class_title
WHERE class.teacher_name = 'Ms. Lovelace';

-- Above we also filter within the INNER JOIN 
-- Aside: JOIN is faster than Query/Subquery (Subquery is more convenient... but JOIN is preferred for readability & maintainability) 

/**
     * Consider the following tables:
     *
     *              class                                  student
     * | id |  teacher_name |class_title|     | id |      student_name |class_title|
     * ----------------------------------     --------------------------------------
     * |1   |'Ms. Lovelace' |'Physics'  |     |1   |'John Stewart'     |'Writing'  |
     * |2   |'Ms. Lovelace' |'Math'     |     |2   |'Stephen Colbert'  |'Physics'  |
     * |3   |'Mr. McCarthy' |'Writing'  |     |3   |'Samantha Bee'     |'Math'     |
     * |4   |'Ms. Goodall'  |'Biology'  |     |4   |'Aasif Mandvi'     |'Writing'  |
     *                                        |5   |'Robert Riggle'    |'Physics'  |
     *                                        |6   |'Jessica Williams' |'Art'      |
     *
     */
/**
         * Problem 1: Write a query that will return the id, and name of each of Ms. Lovelace's students. Notice that
         * Ms. Lovelace teaches two classes, but which classes she teaches aren't known from the data in the student
         * table. This means that you will need a way to combine the data from the two tables (inner join).
         *
         * NOTE: You will need to simultaneously filter those results from the JOIN with a WHERE clause for the
         * Teacher name. For instance:
         * SELECT * FROM table_left INNER JOIN table_right
         * ON table_left.column1 = table_right.column3
         * WHERE table_left.column1 = value;
         *
         *
         * Note: There should not be a wild card (*) in your statement. You will need to specify the column in your
         * statement by writing table.column, because the column names may be ambiguous between class and student.
         */