# Application Duplication and Email Automation System

This system is designed to load two JSON files, compare them for duplicates, and then send emails with the values from the files to different email addresses.

## Features

- **JSON File Loading**: Loads two JSON files for processing.
- **Duplicate Checking**: Compares the loaded files for any duplicate entries.
- **Email Automation**: Sends out emails with specific values extracted from the JSON files.

## How It Works

The application works in several key steps:

1. **Load JSON Files**: The system starts by loading the required JSON files that contain the data to be processed.
2. **Compare for Duplicates**: It then compares the data from these files to identify any duplicates.
3. **Email Preparation and Sending**: For each unique entry, the system prepares an email with relevant information and sends it to designated email addresses.

## Implementation Details

### Dependencies

- Java Mail API for email sending functionality.
- JSON Parser for loading and parsing JSON files.

### Key Components

- `MailSender`: Handles the construction and sending of emails.
- `ApplicationCreation`: Manages the loading of JSON files, duplicate checking, and initiation of the email sending process.

### Mail Sending Process

The `MailSender` class is responsible for setting up email properties, authenticating with the SMTP server, and sending emails with attachments. It uses the Java Mail API to create and send emails that include:

- Email body text with placeholders for dynamic content.
- Attachments, typically documents or other relevant files.

### Email Composition

Emails are composed with a specific structure, including a greeting, body text relevant to the application, and a closing statement. Attachments are added as needed based on the data processed from the JSON files.

## Setup and Configuration

1. **Configure SMTP Settings**: Before running the application, ensure that SMTP settings (username, password, host, port) are correctly configured in the `MailSender` class.
2. **Load JSON Files**: Place the JSON files to be compared in the specified directory.
3. **Run the Application**: Execute the `ApplicationCreation` main method to start the process.

## Usage

To use this system:

1. Place the JSON files in the designated input directory.
2. Ensure all configurations are set (SMTP, file paths).
3. Run the application to process the files and send emails.

## Future Enhancements

- Implement a GUI for easier file management and progress tracking.
- Extend functionality to support different file formats beyond JSON.
- Enhance duplicate detection with more sophisticated algorithms for larger datasets.

## Contributing

Contributions are welcome to improve the application. Please follow the standard pull request process for your contributions.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
