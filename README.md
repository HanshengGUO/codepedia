# Codepedia: An online knowledge base for developers
Welcome to Codepedia, a knowledge base designed specifically for developers. This multi-faceted tool is an open-source full stack project providing a robust platform for the developer community to share, learn, and collaborate on technical content.
## Overview
Codepedia aims to facilitate the creation, storage, and management of rich-text technical notes. It features an intuitive user interface that allows users to effortlessly navigate through the platform, create and modify notes, and manage categories.
## Features
* 📝 **Rich-Text Display**: Codepedia supports a rich-text editor enabling seamless text formatting. This allows you to add style, emphasis, and personal touch to your notes, making them more engaging and readable.
* 📚 **Ebook Admin**: As an Ebook Admin, you have the power to control the content on Codepedia. This includes the ability to modify, add, or delete ebooks using a fully functional editor, ensuring that the platform hosts top-notch, relevant content.
* 👥 **User Center**: The User Center is where you can manage all the users on Codepedia. You can add or delete users, overseeing the community to maintain a safe, productive environment for knowledge exchange.
* 📂 **Category Admin**: As a Category Admin, you can modify the categories and assign the ebooks to relevant categories. This ensures content is well-organized and easily accessible, enhancing the user experience on Codepedia.
* 📊 **Web Dashboard**: Codepedia includes a web dashboard which presents visit statistics. You can track key metrics like daily reads and likes, providing valuable insights into user engagement and content popularity.

## Tech Stack
Codepedia is a full stack project with a primary focus on backend development. The technology stack used in this project is as follows:
### Front-end
* **Vue 3**, **TypeScript** and **Ant Design** are used to build the layout of website.
### Back-end
* **Java** & **Spring Boot** 2: The back-end is developed using Java and Spring Boot 2.
* Spring Boot Features: Aspect-Oriented Programming (AOP), interceptors, filters, asynchronous programming, scheduled tasks, and WebSocket are employed to enhance the functionality of the back-end.
* **Redis**: Redis is used as a cache to manage user login sessions and improve the performance of the application.
* **RabbitMQ**: RabbitMQ is utilized to decouple the "like" process, preventing excessive coupling that could lead to system crashes.
* **MyBatis** & **MySQL**: MyBatis, a powerful persistence framework, is used in combination with the MySQL database to store user and ebook data.
### Engineering Management
* Logging, Configuration Files, Hot Deployment, Git, Maven: These tools and practices are employed to manage the engineering aspects of the project efficiently.
* Unified Backend API Response Design, Encapsulated Unified Request Response Parameters, Utility Class Encapsulation: These techniques are used to streamline the development process and maintain a consistent codebase.
### Deployment
* Cloud Deployment: Codepedia is deployed on the cloud, leveraging cloud-based MySQL and Redis services for enhanced scalability and performance.
