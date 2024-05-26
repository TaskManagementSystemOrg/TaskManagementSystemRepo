Task Management Console Application
Overview
This application is designed for a small team of developers to manage tasks related to their software product. It supports multiple teams, each with its own members and task boards. The application tracks various types of tasks such as bugs, stories, and feedback, and provides a rich set of operations for task management.

Features
Teams
Create and manage multiple teams.
Each team has a unique name (5-15 characters).
Teams have members and boards.
Members
Each member has a unique name (5-15 characters).
Members have a list of tasks and an activity history.
Boards
Each board has a unique name within the team (5-10 characters).
Boards contain tasks and an activity history.
Tasks
There are three types of tasks:

Bug

ID, title (10-100 characters), description (10-500 characters), steps to reproduce, priority, severity, status, assignee, comments, and change history.
Priority: High, Medium, Low
Severity: Critical, Major, Minor
Status: Active, Done
Story

ID, title (10-100 characters), description (10-500 characters), priority, size, status, assignee, comments, and change history.
Priority: High, Medium, Low
Size: Large, Medium, Small
Status: Not Done, InProgress, Done
Feedback

ID, title (10-100 characters), description (10-500 characters), rating, status, comments, and change history.
Rating: Integer
Status: New, Unscheduled, Scheduled, Done
Operations
Create and manage teams and members.
Add members to teams.
Create and manage boards within teams.
Create and manage tasks (Bug, Story, Feedback) within boards.
Change task properties (priority, severity, size, status).
Assign and unassign tasks to members.
Add comments to tasks.
List and filter tasks by various criteria.
Use Cases
Use Case 1: Creating a Bug
A developer creates a new Bug with the title "The program freezes when the Log In button is clicked", assigns it High priority, Critical severity, and status Active. Steps to reproduce are added, and the bug is assigned to a senior developer.

Use Case 2: Adding a New Team Member
A developer creates a new team member and adds them to an existing team, then assigns all Critical bugs to the new member.

Use Case 3: Fixing a Bug
A developer fixes a bug, adds a comment about the fix, changes the status to Done, and checks the changes history for verification.

Technical Requirements
Follow OOP best practices including encapsulation, inheritance, and polymorpism.

Example Input and Output
Example 1: Creating a Bug
Input:
Create Bug
Title: The program freezes when the Log In button is clicked
Description: This needs to be fixed quickly!
Priority: High
Severity: Critical
Status: Active
Assignee: JohnDoe
Steps to reproduce:
1. Open the application
2. Click "Log In"

Output:
Bug created with ID 1
