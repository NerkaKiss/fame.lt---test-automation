# Fame.lt Automated Tests

This project contains automated UI tests for the Fame.lt online store. The tests cover essential functionalities such as login, logout, adding items to the cart, searching for products, sorting products, filtering products, and the checkout process.

## Technologies Used

- Java
- Selenium WebDriver
- TestNG

## Prerequisites

- Java installed on your system
- Maven installed
- WebDriver compatible with your browser

## Test Cases

### Login & Logout Tests

#### Positive Test: Login with Valid Credentials

- Expects to see the welcome message "SVEIKI, NERIJUS KIS"
- Checks if the logout button is visible

#### Negative Test: Login with Empty Fields

- Ensures an error message appears when login fields are empty

#### Negative Test: Login with Wrong Password

- Checks if an error message "Identifikavimas nepavyko" is displayed
- Verifies the URL and title remain on the login page

#### Positive Test: Logout

- Ensures that login fields are displayed after logging out

### Cart Tests

#### Positive Test: Add to Cart (Cart Module)

- Adds a product to the cart and verifies the product name, price, and success message

#### Positive Test: Add to Cart (Cart Page)

- Adds a product to the cart and verifies the URL, product name, and price on the cart page

#### Positive Test: Refresh Cart Page

- Ensures that the cart retains its items and details after refreshing the page

#### Positive Test: Checkout

- Adds an item to the cart, proceeds to checkout, and verifies the product details

#### Positive Test: Change Item Quantity

- Changes the quantity of an item in the cart and verifies the total price updates accordingly

#### Positive Test: Delete Item from Cart

- Removes an item from the cart and ensures the cart is empty

### Product Search Tests

#### Positive Tests

- Searches for products using various queries and verifies the search results
    - Exact product name
    - Partial match
    - SKU search
    - Special characters
    - Case-insensitive search
    - Leading/trailing spaces

#### Negative Tests

- Searches for non-existent products and verifies the "no products found" message appears

### Product Sorting Tests

#### Positive Tests

- Sorts products in different ways and verifies the order:
    - Alphabetically (A-Z, Z-A)
    - By price (Lowest to Highest, Highest to Lowest)

### Product Filtering Tests

#### Positive Tests

- Filters products by size and verifies:
    - The product list is not empty
    - The product count decreases after applying the filter
    - The first product matches the selected size
    - The clear filter button is visible

## Running the Tests

1. Clone the repository
2. Open the project in IntelliJ IDEA (or your preferred IDE)
3. Execute the tests using TestNG

## Author

Nerijus Kisieliauskas

