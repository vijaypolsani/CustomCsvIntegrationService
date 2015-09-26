** Overview:
<b>
This percolate service takes a flat file comma separated and transforms (clean, sort, convert) the data to JSON.
It uses IOC, Apache Commons file utils, Mocks, metrics and Rest endpoints.
</b>

** Design Requirements:
- Extensible Design
- Unit Test
- Documentation
- Attention to Detail
- Coding Conventions (API, Java Doc)
- tar.gz

** Input
The order and format of these lines vary in 3 separate ways. The 3 different formats are as follows:
1) Lastname, Firstname, (703)-742-0996, Blue, 10013
2) Firstname Lastname, Red, 11237, 703 955 0373
3) Firstname, Lastname, 10013, 646 111 0101, Green

- INVALID LINES and should not interfere with the processing of subsequent valid lines.
- A line should be considered invalid if its PHONE NUMBER does not contain the proper NUMBER of DIGITS.

Output:
- File 2 space indentation
- Keys sorted alphabetically ASCENDING (lastname, firstname)
- entries, errors (bad phone, bad zipcode - email to Danny han)

Algorithm:
The parsing algorithm:
1. Any row containing 5 fields (when split comma separated)
    1. Last field (field[4]) is a digit then field[0]=lastname, field[1]=firstname
    2. Last field (field[4]) is not a digit then field[0]=firstname, field[1]=lastname
2. Any row containing 4 fields (when split comma separated)
    1. First field split again using ' ' (space). within the split field[0]=firstname, field[1]=lastname
3. Invalid lines field length less than 4, and phone number less than 10 digits.
