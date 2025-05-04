# Test framework for Booking.com 
## Checklist
### *Login*
Open https://account.booking.com/sign-in
1. Login without email: click on button "Continue with email" without entering email -> receiving an error message "Enter your email address"
2. Login with incorrect email: click on button "Continue with email" with incorrect email -> receiving an error message "Make sure the email address you entered is correct."
3. Login with correct registered or unregistered email: click on button "Continue with email" with correct email -> redirection to https://account.booking.com/otp/email-code

### *Searching properties*
Open https://www.booking.com/
1. Do search with correct data: enter city and data and click on "Search" button -> redirection to https://www.booking.com/searchresults 
2. Do search without entering city and dates: just click on "Search" button -> alert appears in "city" field
3. Do search without entering city: enter city and click on "Search" button -> redirection to https://www.booking.com/searchresults
4. Do search without entering city: enter dates and click on "Search" button -> alert appears in "city" field
5. Select max number of adults -> limit of 30 is reached
6. Select min number of adults -> limit of 1 is reached
7. Select max number of children -> limit of 10 is reached
8. Select min number of children -> limit of 0 is reached
9. Select max number of children and check amount of 'select children age' input -> 10 inputs

### *Searching results*
Open https://www.booking.com/, do search with correct data
1. Filter by min and max price per night -> results only with price in this range
2. Filter by rate -> results only with rates higher than selected
3. Sort by acsdending price -> results are sorted from lower price to higher
4. Sort by descending price -> results are sorted from higher price to lower
5. Sort by acsdending rate -> results are sorted from lower rate to higher
6. Sort by descending price -> results are sorted from higher rate to lower
