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