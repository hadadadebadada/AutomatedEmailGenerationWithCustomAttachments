# ApplicationAutomation README.md

## Overview

ApplicationAutomation is a Java-based system designed to automate the job application process. It manages job listings from JSON files, generates personalized application PDFs, checks for duplicate applications, and sends applications via email. This README provides an overview of its key components and setup instructions.

## Key Components

### `MailSender`
- **Description**: Handles the construction and sending of emails. It uses the Java Mail API to create emails and SMTP for authentication, ensuring that job applications are sent to the correct recipients with the necessary attachments.
- **Key Methods**:
  - `sendMail(String to, String attachment, String subject)`: Sends an email to the specified recipient with an attached PDF application and a subject.

### `ApplicationCreation`
- **Description**: Manages the loading of job listings from JSON files, performs duplicate checks, and initiates the email sending process. It generates personalized application documents for each job listing.
- **Key Methods**:
  - `createPdf(String fileName)`: Generates a PDF document for the application.
  - `sendingMails()`: Initiates the process of sending out the prepared emails to the respective companies.

### `DubChecker`
- **Description**: Performs checks for duplicate applications by comparing new job listings with previously applied ones. This ensures that multiple applications are not sent to the same company for the same job position.
- **Key Methods**:
  - `printCompany()`: Compares new job listings with existing ones to identify duplicates.
  - `removeDuplicates(ArrayList<T> list)`: Removes duplicates from a list, aiding in the process of identifying new job applications.

### `ScrapyLinkCreator`
- **Description**: *No implementation details provided.* Assumed to be responsible for generating or managing links related to web scraping, potentially for gathering job listings or company information.

## Setup and Usage

1. **Prerequisites**:
   - Java JDK 8 or newer.
   - ~~Access to an SMTP server for sending emails (e.g., Gmail SMTP).~~
   - Access to an SMTP server for sending emails (e.g., Outlook SMTP). Gmail is deprecated, they dont allow thirdparty access anymore.
   - iTextPDF library for PDF creation and manipulation.

2. **Configuration**:
   - Update `MailSender` with your SMTP server details, including username and password.
   - Ensure JSON files with job listings are correctly formatted and accessible to the `ApplicationCreation` component.

3. **Running the Application**:
   - Compile the Java files.
   - Run `ApplicationCreation` to start the application process. This will load job listings, generate PDFs, perform duplicate checks, and send emails.
   - Optionally, run `DubChecker` independently to manually check for duplicates before sending applications.

4. **Adding New Job Listings**:
   - Job listings should be added in JSON format. Follow the structure used in existing `*.json` files for compatibility.

## Note

This README assumes familiarity with Java development and the use of libraries such as iTextPDF and Java Mail API. For detailed documentation on these libraries, refer to their respective official documentation.

## Disclaimer

ApplicationAutomation is intended for personal use and educational purposes. Users are responsible for ensuring that their use of the software complies with terms of service of the platforms they interact with and any applicable laws.
