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

### `DubChecker`

### `ScrapyLinkCreator`


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
