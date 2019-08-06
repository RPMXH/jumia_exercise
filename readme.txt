Author: Rafael Marques

Project BootUp:
 - change directory to jumia directory
 - run command: "docker run -p 8080:8080 docker-jumia"

Project Endpoints:
 - localhost:8080/customers -> retrieves JSON data regarding all customers without filters
 - localhost:8080/customers/country={cty} -> retrieves JSON data regarding customers belonging to provided country (Cameroon / Ethiopia / Morocco / Mozambique / Uganda)
 - localhost:8080/customers/state={state} -> retrieves JSON data regarding customers in a specified state (valid / invalid)
 - localhost:8080/customers/p -> retrieves web page regarding all customers without filters
 - localhost:8080/customers/p/country={cty} -> retrieves information regarding customers belonging to provided country (Cameroon / Ethiopia / Morocco / Mozambique / Uganda)
 - localhost:8080/customers/p/state={state} -> retrieves information regarding customers in a specified state (valid / invalid)
