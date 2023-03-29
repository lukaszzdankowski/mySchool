I'm currently working on a much better project. Feel invited:<br>
https://github.com/lukaszzdankowski/charity

<h2>Overview:</h2>
This is a very simple project that enables creating homeworks for students contaning tasks with number value answers. In this web application Teacher is like admin - he controlls all the database operations (except doing actual homework). Student is only allowed to do the homeworks that are assigned to him and to see the score of completed homeworks.<br>
mySchool is a project for educational purpose and it is still beeing improved.

<h2>Technologies used:</h2>
<ul>
<li>Java</li>
<li>Maven</li>
<li>Spring</li>
<li>Hibernate</li>
<li>JPA</li>
<li>MySQL</li>
<li>SQL / JPQL / JPA methods</li>
<li>JSP</li>
</ul>

<h2>Completed features of mySchool:</h2>
✅ Added Task, Exam (template for Homework containing a list of Tasks), Homework, Reply (single Student's reply for task in Homework) Entities.<br>
✅ Added User entity and differentiation between student and teacher roles.<br>
✅ Added CRUD operations for Tasks, Exams, Homeworks and Users including basic validation and complex restrictions for deleting/editing database records that are in use.<br>
✅ Added operation of executing homework.<br>
✅ Added basic authentication via AppUser object in Session.<br>
✅ Added basic authorization for teacher's and student's operations via javax.servlet.Filter.<br>
