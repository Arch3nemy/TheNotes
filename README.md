## The Notes is basically notepad with ability to create and store notes with title, description and date. Used technologies: Jetpack Compose, Dagger2, Room, Clean Architecture, MVI, Kotlin Coroutines, WorkManager. 

### As you can see on the first screen, you can launch app without network available. If you turn on wifi or cellular network, you'll see that the status has changed and shortly after you will be redirected to the home screen.
<img src="https://i.imgur.com/Slw3FBF.jpg" width="200" height = "450" hspace="40"> <img src="https://i.imgur.com/PhCY3u8.jpg" width="200" height = "450" hspace="40"> <img src="https://i.imgur.com/4NzLben.jpg" width="200" height = "450" hspace="40"> 

### If you press "+" button(Floating action button) on the botton right corner, the blank note will be created. If you press on the note from the list on the screen, you will be redirected to "Edit Screen", when you can edit title and description of the note. If you change any character of the title or description, "Save" button will appear. Press it, note will be saved and you will be redirected back to the home screen.
<img src="https://i.imgur.com/qa8dANU.jpg" width="200" height = "450" hspace="40"> <img src="https://i.imgur.com/QC0uN97.jpg" width="200" height = "450" hspace="40"> <img src="https://i.imgur.com/SE2KOsM.jpg" width="200" height = "450" hspace="40">

### "Waiting for Network Screen"(Screenshot 1) will appear only on the first launch. If you already used app, your database has some notes saved and only snackbar will show notification about unavailable network for 3 seconds. 
<img src="https://i.imgur.com/aSXQ3wi.jpg" width="200" height = "450">
