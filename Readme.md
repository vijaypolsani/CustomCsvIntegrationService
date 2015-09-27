** Overview:
<b>
This percolate service takes a flat file comma separated and transforms (clean, sort, convert) the data to JSON.
It uses IOC, Apache Commons file utils, Mocks, metrics and Rest endpoints.
</b>
<B> The current service called PercolateService will expose the process as RESTFull. The deployment creates a single jar
Instructions:<br>
1. Make sure you have JDK 1.8. Shld be good with 1.7 but make sure to change POM<br>
2. mvn clean install. This command shld produce a uber jar.<br>
3. java -jar percolate-service-1.0-SNAPSHOT-jar-with-dependencies.jar<br>
4. http://localhost:8080/percolate/test<br>
Send the raw file as 'content to send' via your favorite Rest Service test.<br>
</B>
<br>
Design Requirements:

- Extensible Design
- Unit Test
- Documentation
- Attention to Detail
- Coding Conventions (API, Java Doc)
- tar.gz

Input

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

- Any row containing 5 fields (when split comma separated)
    - Last field (field[4]) is a digit then field[0]=lastname, field[1]=firstname
    - Last field (field[4]) is not a digit then field[0]=firstname, field[1]=lastname
- Any row containing 4 fields (when split comma separated)
    - First field split again using ' ' (space). within the split field[0]=firstname, field[1]=lastname
- Invalid lines field length less than 4, and phone number less than 10 digits.

Considering the ligther validation.

Sample Outpout:
```json

{

    "entries":

[

{

    "lastName": "Annalee",
    "firstName": "Loftis",
    "color": "blue",
    "phoneNumber": "905 329 2054",
    "zipcode": "97296"

},
{

    "lastName": "Aura",
    "firstName": "Eilers",
    "color": "pink",
    "phoneNumber": "489 634 9504",
    "zipcode": "39358"

},
{

    "lastName": "Awong",
    "firstName": "Maurita",
    "color": "blue",
    "phoneNumber": "061 937 1243",
    "zipcode": "15726"

},
{

    "lastName": "Azzie",
    "firstName": "Zabriskie",
    "color": "green",
    "phoneNumber": "110 574 1904",
    "zipcode": "94339"

},
{

    "lastName": "Ballentine",
    "firstName": "Hyo",
    "color": "blue",
    "phoneNumber": "(182)-424-5300",
    "zipcode": "21351"

},
{

    "lastName": "Breland",
    "firstName": "Laurie",
    "color": "aqua marine",
    "phoneNumber": "(087)-853-4995",
    "zipcode": "44359"

},
{

    "lastName": "Butterfield",
    "firstName": "Kelsi",
    "color": "yellow",
    "phoneNumber": "(967)-196-4953",
    "zipcode": "05644"

},
{

    "lastName": "Cadena",
    "firstName": "Clarinda",
    "color": "red",
    "phoneNumber": "(666)-621-7485",
    "zipcode": "09517"

},
{

    "lastName": "Clary",
    "firstName": "Blair",
    "color": "red",
    "phoneNumber": "(463)-118-2451",
    "zipcode": "07256"

},
{

    "lastName": "Darcie",
    "firstName": "Phoenix",
    "color": "pink",
    "phoneNumber": "854 910 7619",
    "zipcode": "55806"

},
{

    "lastName": "Darline",
    "firstName": "Barbeau",
    "color": "blue",
    "phoneNumber": "260 619 7450",
    "zipcode": "11027"

},
{

    "lastName": "Davis",
    "firstName": "Armand",
    "color": "yellow",
    "phoneNumber": "(240)-736-4719",
    "zipcode": "64320"

},
{

    "lastName": "Demming",
    "firstName": "Kent",
    "color": "red",
    "phoneNumber": "(622)-720-0550",
    "zipcode": "37820"

},
{

    "lastName": "Dhillon",
    "firstName": "Beata",
    "color": "pink",
    "phoneNumber": "(843)-661-4252",
    "zipcode": "90022"

},
{

    "lastName": "Ferro",
    "firstName": "Myrtice",
    "color": "green",
    "phoneNumber": "(568)-821-3874",
    "zipcode": "95836"

},
{

    "lastName": "G. Humperdink",
    "firstName": "Englebert",
    "color": "red",
    "phoneNumber": "839 014 8051",
    "zipcode": "36410"

},
{

    "lastName": "Gabler",
    "firstName": "Prince",
    "color": "gray",
    "phoneNumber": "645 200 7094",
    "zipcode": "08291"

},
{

    "lastName": "Gladis",
    "firstName": "Tubb",
    "color": "pink",
    "phoneNumber": "454 934 6454",
    "zipcode": "73149"

},
{

    "lastName": "Haugland",
    "firstName": "Marita",
    "color": "red",
    "phoneNumber": "034 614 7966",
    "zipcode": "21231"

},
{

    "lastName": "Hood",
    "firstName": "Robert",
    "color": "pink",
    "phoneNumber": "(054)-813-6030",
    "zipcode": "47784"

},
{

    "lastName": "Hung",
    "firstName": "Moxley",
    "color": "pink",
    "phoneNumber": "667 132 9000",
    "zipcode": "93381"

},
{

    "lastName": "Johnston",
    "firstName": "James",
    "color": "gray",
    "phoneNumber": "628 102 3672",
    "zipcode": "38410"

},
{

    "lastName": "Kerri",
    "firstName": "Chandler",
    "color": "red",
    "phoneNumber": "818 246 5061",
    "zipcode": "32868"

},
{

    "lastName": "Leedy",
    "firstName": "Magan",
    "color": "blue",
    "phoneNumber": "(896)-391-7308",
    "zipcode": "48680"

},
{

    "lastName": "Liptak",
    "firstName": "Quinton",
    "color": "yellow",
    "phoneNumber": "(653)-889-7235",
    "zipcode": "70703"

},
{

    "lastName": "Magaly",
    "firstName": "Gregor",
    "color": "blue",
    "phoneNumber": "698 513 7428",
    "zipcode": "86286"

},
{

    "lastName": "Mallette",
    "firstName": "Bernie",
    "color": "pink",
    "phoneNumber": "859 924 2843",
    "zipcode": "17300"

},
{

    "lastName": "Mazie",
    "firstName": "Chavis",
    "color": "gray",
    "phoneNumber": "218 097 2208",
    "zipcode": "48843"

},
{

    "lastName": "Mccaster",
    "firstName": "Fatimah",
    "color": "gray",
    "phoneNumber": "(854)-345-7518",
    "zipcode": "57693"

},
{

    "lastName": "Merryman",
    "firstName": "Earlene",
    "color": "blue",
    "phoneNumber": "305 987 8362",
    "zipcode": "86258"

},
{

    "lastName": "Miller",
    "firstName": "Justin",
    "color": "blue",
    "phoneNumber": "(657)-743-7189",
    "zipcode": "58979"

},
{

    "lastName": "Mirian",
    "firstName": "Hankey",
    "color": "aqua marine",
    "phoneNumber": "180 739 1295",
    "zipcode": "22172"

},
{

    "lastName": "Mona",
    "firstName": "Shela",
    "color": "pink",
    "phoneNumber": "986 283 6066",
    "zipcode": "11198"

},
{

    "lastName": "Parrish",
    "firstName": "Theo",
    "color": "green",
    "phoneNumber": "423 334 3136",
    "zipcode": "60128"

},
{

    "lastName": "Patnaude",
    "firstName": "Andy",
    "color": "aqua marine",
    "phoneNumber": "(612)-712-8471",
    "zipcode": "29198"

},
{

    "lastName": "Pollack",
    "firstName": "Len",
    "color": "aqua marine",
    "phoneNumber": "642 573 6473",
    "zipcode": "29868"

},
{

    "lastName": "Reinaldo",
    "firstName": "Vandermeer",
    "color": "gray",
    "phoneNumber": "751 665 5618",
    "zipcode": "76589"

},
{

    "lastName": "Renault",
    "firstName": "Jacques",
    "color": "yellow",
    "phoneNumber": "157 348 8325",
    "zipcode": "00328"

},
{

    "lastName": "Saltsman",
    "firstName": "Sabina",
    "color": "blue",
    "phoneNumber": "(577)-767-9995",
    "zipcode": "81360"

},
{

    "lastName": "Schuetz",
    "firstName": "France",
    "color": "pink",
    "phoneNumber": "(732)-729-4104",
    "zipcode": "09893"

},
{

    "lastName": "Shanika",
    "firstName": "Dodd",
    "color": "pink",
    "phoneNumber": "940 761 0886",
    "zipcode": "82733"

},
{

    "lastName": "Stickle",
    "firstName": "Margarete",
    "color": "aqua marine",
    "phoneNumber": "805 612 0845",
    "zipcode": "92493"

},
{

    "lastName": "Swearingen",
    "firstName": "Kristeen",
    "color": "blue",
    "phoneNumber": "(238)-440-6451",
    "zipcode": "60460"

},
{

    "lastName": "Tillotson",
    "firstName": "Ria",
    "color": "aqua marine",
    "phoneNumber": "196 910 5548",
    "zipcode": "97671"

},
{

    "lastName": "Whipkey",
    "firstName": "Theodora",
    "color": "red",
    "phoneNumber": "808 633 1734",
    "zipcode": "54450"

},

    {
        "lastName": "Won",
        "firstName": "George",
        "color": "aqua marine",
        "phoneNumber": "488 084 5794",
        "zipcode": "97148"
    }

],
"errors":

    [
        1,
        6,
        8,
        16,
        17,
        30,
        38,
        39,
        42,
        44,
        45,
        46,
        48,
        49,
        50,
        57,
        58,
        63
    ]

}
```


NOTE: Google Validation of the Phone Number: Lot of records were invalid. considering US numbers
```json
{
  "entries": [
    {
      "lastName": "Annalee",
      "firstName": "Loftis",
      "color": "blue",
      "phoneNumber": "905 329 2054",
      "zipcode": "97296"
    },
    {
      "lastName": "Darcie",
      "firstName": "Phoenix",
      "color": "pink",
      "phoneNumber": "854 910 7619",
      "zipcode": "55806"
    },
    {
      "lastName": "Darline",
      "firstName": "Barbeau",
      "color": "blue",
      "phoneNumber": "260 619 7450",
      "zipcode": "11027"
    },
    {
      "lastName": "Davis",
      "firstName": "Armand",
      "color": "yellow",
      "phoneNumber": "(240)-736-4719",
      "zipcode": "64320"
    },
    {
      "lastName": "Demming",
      "firstName": "Kent",
      "color": "red",
      "phoneNumber": "(622)-720-0550",
      "zipcode": "37820"
    },
    {
      "lastName": "Dhillon",
      "firstName": "Beata",
      "color": "pink",
      "phoneNumber": "(843)-661-4252",
      "zipcode": "90022"
    },
    {
      "lastName": "Kerri",
      "firstName": "Chandler",
      "color": "red",
      "phoneNumber": "818 246 5061",
      "zipcode": "32868"
    },
    {
      "lastName": "Mallette",
      "firstName": "Bernie",
      "color": "pink",
      "phoneNumber": "859 924 2843",
      "zipcode": "17300"
    },
    {
      "lastName": "Mccaster",
      "firstName": "Fatimah",
      "color": "gray",
      "phoneNumber": "(854)-345-7518",
      "zipcode": "57693"
    },
    {
      "lastName": "Merryman",
      "firstName": "Earlene",
      "color": "blue",
      "phoneNumber": "305 987 8362",
      "zipcode": "86258"
    },
    {
      "lastName": "Miller",
      "firstName": "Justin",
      "color": "blue",
      "phoneNumber": "(657)-743-7189",
      "zipcode": "58979"
    },
    {
      "lastName": "Parrish",
      "firstName": "Theo",
      "color": "green",
      "phoneNumber": "423 334 3136",
      "zipcode": "60128"
    },
    {
      "lastName": "Patnaude",
      "firstName": "Andy",
      "color": "aqua marine",
      "phoneNumber": "(612)-712-8471",
      "zipcode": "29198"
    },
    {
      "lastName": "Saltsman",
      "firstName": "Sabina",
      "color": "blue",
      "phoneNumber": "(577)-767-9995",
      "zipcode": "81360"
    },
    {
      "lastName": "Schuetz",
      "firstName": "France",
      "color": "pink",
      "phoneNumber": "(732)-729-4104",
      "zipcode": "09893"
    },
    {
      "lastName": "Shanika",
      "firstName": "Dodd",
      "color": "pink",
      "phoneNumber": "940 761 0886",
      "zipcode": "82733"
    },
    {
      "lastName": "Stickle",
      "firstName": "Margarete",
      "color": "aqua marine",
      "phoneNumber": "805 612 0845",
      "zipcode": "92493"
    },
    {
      "lastName": "Whipkey",
      "firstName": "Theodora",
      "color": "red",
      "phoneNumber": "808 633 1734",
      "zipcode": "54450"
    }
  ],
  "errors": [
    1,
    2,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12,
    13,
    14,
    16,
    17,
    18,
    20,
    22,
    23,
    24,
    28,
    29,
    30,
    33,
    35,
    36,
    38,
    39,
    40,
    42,
    43,
    44,
    45,
    46,
    47,
    48,
    49,
    50,
    52,
    54,
    55,
    56,
    57,
    58,
    59,
    63
  ]
}
```