# Instruction about how to run the receipt-processor application

## Prerequisites

- Ensure you have [Docker](https://www.docker.com/get-started) installed on your machine.
- Verify the installation by running:
  ```sh
  docker --version
  ```
  
## Building docker image
  ```sh
    docker build -t receipt-processor .
  ```

## Running the docker image
  ```sh
    docker run -d -p 8080:8080 --name receipt-processor-container receipt-processor
  ```

## Use the following url to access the application
  ```text
  http://localhost:8080
  ```