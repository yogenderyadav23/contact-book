# contact-book
Contact book app

# All api(s) are secured by Basic authentication, userName and password are mentioned in application property file.

# Application is deployed on cloud: app url: https://contact-book-app-23.herokuapp.com/contact-book/contact

Note: I have commented out caching part. Did not find any add on server for cloud deployement environment.

The app supports below api's for CRUD operation.

1. CREATE CONTACT
METHOD:POST
END POINT: http://<HOST_ADDRESS>/contact-book/contact
SAMPLE REQUEST:
{
"email_id":"ab1145@gmail.com",
"first_name":"abc",
"last_name":"One",
"primary_num":"1234567890",
"secondary_num":"163561313",
"address":{
	
	"addressLine":"C 21, Neta ji Subhash appt, Sec-13",
	"city":"New Delhi",
	"state":"Delhi",
	"country":"India",
	"pincode":"10078"
}
}

2. UPDATE CONTACT : To update the existing contact
METHOD: PUT
END POINT:http://<HOST_ADDRESS>/contact-book/contact
Sample Request:
{
"email_id":"ab1145@gmail.com",
"first_name":"updatedName",
"last_name":"One",
"primary_num":"12345632563",
"secondary_num":"163561313",
"address":{
	
	"addressLine":"C 21, Neta ji Subhash appt, Sec-13",
	"city":"New Delhi",
	"state":"Delhi",
	"country":"India",
	"pincode":"10078"
}
}


3. DELETE CONTACT: To delete the existing contact
METHOD: DELETE
END POINT:http://<HOST_ADDRESS>/contact-book/contact?emailId=ab1145@gmail.com

4. SEARCH CONTACT: To search api on the basis of name, email.
METHOD: GET
END POINT:http://<HOST_ADDRESS>/contact-book/contact?name=abc&emailId=ab11@gmail.com
http://<HOST_ADDRESS>/contact-book/contact?name=abc&pageNo=2

Request Params: name,emailId,pageNo;


Response: Paginated Result:
{
"success": true,
    "data": {
        "totalPages": 4,
        "pageNo": 2,
        "pageSize": 1,
        "list": [
            {
                "email_id": "ab15@gmail.com",
                "first_name": "abc",
                "last_name": "One",
                "primary_num": "1234567890",
                "secondary_num": "163561313",
                "address": {
                    "addressLine": "dahdjas hjdsahd ja jdk ajda d",
                    "city": "New Delhi",
                    "state": "Delhi",
                    "country": "India",
                    "pincode": "10078"
                }
            }
        ]
    }
}







