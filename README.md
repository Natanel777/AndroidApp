# Rick and Morty Project

![App Screenshot](screenShots/rickandmortyinfo.jpg) <!-- Replace with an actual screenshot of your app -->

Rick and Morty Project is an Android app that shows you lists of seasons, every season contains
the character belongs them and in every character you have details about them (the information shows up helps to rick and morty database api).

## Features

- **Seasons Overview**: Explore all the seasons of the popular animated show "Rick and Morty."
  The "Rick and Morty Project" app provides you with an overview of all the seasons from the TV show "Rick and Morty." You can learn about each season's unique storylines, characters, and memorable episodes. Dive into the fantastic world of science fiction and humor created by Justin Roiland and Dan Harmon.


- **Character Insights**: Discover the characters introduced in each season.

  Explore the diverse cast of characters that appear in each season. Learn about their roles, status, origin, Get insights into the quirky personalities that make "Rick and Morty" so captivating.


- **User-Friendly Interface**: Navigate seasons and characters effortlessly.

  The app's user-friendly interface makes it easy to browse through the seasons and character profiles. Whether you're catching up or revisiting your favorite moments, the intuitive design ensures a seamless exploration of the "Rick and Morty" universe.

## Technologies Used
- Fully written in Kotlin language.
- Built on MVVM architecture pattern.
- Implements Android Data Binding for efficient UI updates and data binding.
- Uses Android Architecture Components, specifically ViewModel and LiveData.
- Uses Retrofit for making API calls, handling endpoints, and networking.
- Utilizes Picasso for efficient image loading and caching.
- Implements Room and SQLite for local data storage and management.
- Utilizes Kotlin Coroutines for handling asynchronous tasks and background operations.

### Screenshots
| [![Screen1](https://raw.githubusercontent.com/Natanel777/AndroidApp/main/screenShots/screenshot%202023-08-10%20173951.png)]() | [![Screen2](https://raw.githubusercontent.com/Natanel777/AndroidApp/main/screenShots/screenshot%202023-08-10%20173913.png)]() | [![Screen3](https://raw.githubusercontent.com/Natanel777/AndroidApp/main/screenShots/screenshot%202023-08-10%20173818.png)]() | [![Screen4](https://raw.githubusercontent.com/Natanel777/AndroidApp/main/screenShots/screenshot%202023-08-10%20173732.png)]() |
|:-----------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|


## Usage

**Running the App**: After completing everything, you can build and run the app on an Android emulator or a physical device:

- Make sure your emulator/device is connected and recognized by Android Studio.
- Click the "Run" button in Android Studio to build and launch the app.
- The app will load the seasons, and characters.

**Using the App**:

- On the first run, the app will fetch data from the API and store it in the local database for future use. This ensures smoother and faster access to data on subsequent runs.
- On subsequent runs, the app will retrieve data directly from the local database, providing a quicker and offline-friendly experience.
- If you wish to refresh the data and check for updates, there's a "Update The App" button available. Pressing this button will delete the local database and retrieve fresh data from the API.
- The "Update The App" option is especially useful to ensure you're always up-to-date with the latest seasons and character details.

 **Deleting the Database**:

  - The app provides a "Update The App" button that allows you to clear the local database. This can be useful if you want to fetch fresh data from the API, update your data, or troubleshoot any issues related to local data.


**Exploring the App**: Once the app is up and running, you can:
  - View a list of all the seasons and their details.
  - Navigate to each season's character guide to learn about individual characters.
  - Explore character profiles for each season's cast.
  - Enjoy all the information about the character that the app provides you.

