# EMS-Innovaccer
An Entry Management System made with Android.<br> Visitor can check-in and check-out with their details. Host can update the details. Messages are generated and sent automatically to the concerned party.

## Functionality
<p align="center">
 <img src="https://github.com/Mohit17067/EMS-Innovaccer/blob/master/app.gif" alt="GIF FOR Project" width="300" height="500" >
</p>

### Main Page
The application starts with a simple UI providing options to the user using `Radio Button`. User has to choose an options and click GO `Button` to proceed.<br>
First to options are for the visitor to check-in and check-out respectively. Third Option is for the host to update its details.

### Visitor Check In
Visitor can check in using this option. All `text-fields` are mandatory and a `toast` is generated if not filled.<br>
After clicking `CHECK IN` button, a mail is generated using `AsyncTask` and `Javafx-mail` library. At the same time, database is updated with the `visitor` details.<br>
Visitor can not checkin again with the same details.

### Visitor Check Out
Visitor can check out using this option. Visitor must be logged in to use this feature. <br>
The UI and the approach is similar to that of check-in. In this case mail is sent to the visitor with all the details. Check-In Time and Check-Out Time are stored in the database with `Java-DateTime` module.

### Update Host Details
This feature can only be used by the host as a password is required in this feature. Details Field and Button are `disabled` initially and are enabled only after correct password is entered. The details for the host are also saved in the database.<br>
The current password for updating host details is - **host**.

**External-File Storage** feature of android is used to store the entire database.<br>

Email Id Made for the Application - **entry.management.software@gmail.com**

PS - Remember current password to update host details - **host**

**The apk for the application is provided in the repository - `ems.apk`**
