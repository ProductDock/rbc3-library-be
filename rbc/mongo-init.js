db = db.getSiblingDB('library');

db.createCollection('books');

db.books.insertMany([
  {
    "title": "MongoDB: The Definitive Guide: Powerful and Scalable Data Storage",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author1",
        "fullName": "Kristina Chodorow"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover1.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 3,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["MARKETING"]
  },
  {
    "title": "Software Engineering at Google: Lessons Learned from Programming Over Time",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author2",
        "fullName": "Titus Winters"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover2.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 1,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3", "user1"],
    "usersWhoReserved": ["user4", "user6"],
    "usersWhoSuggested": [],
    "bookCategories": ["SOFTWARE_DEVELOPMENT", "PRODUCT_MANAGEMENT"]
  },
  {
    "title": "Database Internals: A Deep Dive into How Distributed Data Systems Work",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author3",
        "fullName": "Alex Petrov"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover3.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 5,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": [],
    "usersWhoReserved": [],
    "usersWhoSuggested": [],
    "bookCategories": ["SOFTWARE_DEVELOPMENT", "PRODUCT_MANAGEMENT", "PSYCHOLOGY"]
  },
  {
    "title": "Restful web services cookbook",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author4",
        "fullName": "Subbu Allamaraju"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover4.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 2,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3", "user1"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": [],
    "bookCategories": ["DESIGN", "PSYCHOLOGY"]
  },
  {
    "title": "Customer Success: How Innovative Companies Are Reducing Churn and Growing Recurring Revenue",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author5",
        "fullName": "Nick Mehta"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover5.svg",
    "totalNumberOfCopies": 3,
    "numberOfAvailableCopies": 0,
    "bookStatus": "RENTED",
    "usersWhoFavourited": [],
    "usersOnWaitingList": ["user1"],
    "usersWhoRented": ["user2", "user3", "user4"],
    "usersWhoReserved": [],
    "usersWhoSuggested": [],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "Site reliability engineering",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author6",
        "fullName": "Betsy Beyer"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover6.svg",
    "totalNumberOfCopies": 1,
    "numberOfAvailableCopies": 0,
    "bookStatus": "RENTED",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": ["user1", "user2"],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": [],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "Docker: up and running",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author7",
        "fullName": "Karl Matthias"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover7.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 3,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1", "user2"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "Time to think: Listening to Ignite the human mind",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author8",
        "fullName": "Nancy Kline"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover8.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 3,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "Impact Mapping: Making a Big Impact with Software Products and Projects",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author9",
        "fullName": "Gojko Adzic"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover9.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 3,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "Continuous Discovery Habits: Discover Products that Create Customer Value and Business Value ",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author10",
        "fullName": "Teresa Torres"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover10.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 3,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "The Principles of Product Development Flow: Second Generation Lean Product Development",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author11",
        "fullName": "Donald G. Reinertsen"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover11.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 3,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "The Coaching Habit: Say Less, Ask More & Change the Way You Lead",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author12",
        "fullName": "Michael Bungay Stanier"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover12.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 3,
    "bookStatus": "AVAILABLE",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  },
  {
    "title": "Mindwise: Why We Misunderstand What Others Think, Believe, Feel, and Want",
    "description": "This is a test description for the book.",
    "authors": [
      {
        "id": "author13",
        "fullName": "Nicholas Epley"
      }
    ],
    "imageUrl": "/Users/pd/Documents/images/BookCover13.svg",
    "totalNumberOfCopies": 5,
    "numberOfAvailableCopies": 0,
    "bookStatus": "RESERVED",
    "usersWhoFavourited": ["user1"],
    "usersOnWaitingList": [],
    "usersWhoRented": ["user3"],
    "usersWhoReserved": ["user4", "user2", "user6", "user7"],
    "usersWhoSuggested": ["user5"],
    "bookCategories": ["SOFTWARE_DEVELOPMENT"]
  }
]);
