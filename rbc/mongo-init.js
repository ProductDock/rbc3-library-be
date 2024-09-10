db.createUser({
    user: "library",
    pwd: "library",
    roles: [
      {
        role: 'readWrite',
        db: 'library'
      },
    ],
  });
