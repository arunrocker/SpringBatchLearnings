--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS university.`student`;
CREATE TABLE university.`student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `student`
--
INSERT INTO university.`student` VALUES (1,'John','Smith','john@gmail.com'),(2,'Sachin','Dave','sachin@gmail.com'),(3,'Peter','Mark','peter@gmail.com'),(4,'Martin','Smith','martin@gmail.com'),(5,'Raj','Patel','raj@gmail.com'),(6,'Virat','Yadav','virat@gmail.com'),(7,'Prabhas','Shirke','prabhas@gmail.com'),(8,'Tina','Kapoor','tina@gmail.com'),(9,'Mona','Sharma','mona@gmail.com'),(10,'Rahul','Varma','rahul@gmail.com');

