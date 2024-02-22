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


# Application Automation Documentation

## Key Components

### `MailSender`
Handles the construction and sending of emails, leveraging the Java Mail API for email creation and SMTP authentication.

### `ApplicationCreation`
Manages the loading of JSON files for job listings, performs duplicate checks, and initiates the email sending process through `MailSender`.

## Mail Sending Process

The `MailSender` class is integral to the application, facilitating:

- Configuration of email properties.
- Authentication with SMTP servers.
- Sending of emails, which may include attachments and are formatted with a greeting, body, and closing statement.

## Email Composition

Emails are crafted to include:

- Dynamic content within the body text.
- Attachments based on the requirements and data sourced from JSON files.

## Setup and Configuration

1. **Configure SMTP Settings**: Prioritize the setup of SMTP settings (username, password, host, port) within the `MailSender` class for email functionality.
2. **Load JSON Files**: Ensure the necessary JSON files are placed in the designated directory for processing.
3. **Run the Application**: Initiate the application by executing the `ApplicationCreation` main method, starting the automated process.

## Utility Tools

### ScrapyLinkCreator

#### Description
`ScrapyLinkCreator` is crafted to parse JSON files containing job listings, generating URLs by appending reference numbers to a base URL.

#### Usage

- JSON location: `/home/bruh/IdeaProjects/ApplicationAutomation/src/main/java/argeinput/links5.json`.
- JSON structure: Array named `stellenangebote` with listings having a `refnr` field.
- Execution: Run `ScrapyLinkCreator` to output generated URLs.

#### Key Functionality

- Parses JSON files to read job listings.
- Extracts and appends `refnr` to a base URL, printing the full URLs.

### DubChecker

#### Description
`DubChecker` aims to identify duplicate company names by comparing listings across two JSON files, `new.json` and `all.json`.

#### Usage

- File preparation: Place `all.json` and `new.json` in the specified directory.
- JSON structure: Both files should include an array named `stellenangebote` with `company` fields in listings.
- Execution: Run `DubChecker` to list duplicate company names.

This structured documentation provides a clear overview of each component and utility tool involved in the application, ensuring ease of understanding and use.










## Usage

To use this system:

1. Place the JSON files in the designated input directory.
2. Ensure all configurations are set (SMTP, file paths).
3. Run the application to process the files and send emails.

## Future Enhancements

- Implement a GUI for easier file management and progress tracking. Available now on https://bewerbr.de
- Extend functionality to support different file formats beyond JSON.
- Enhance duplicate detection with more sophisticated algorithms for larger datasets.

## Contributing

Contributions are welcome to improve the application. Please follow the standard pull request process for your contributions.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
