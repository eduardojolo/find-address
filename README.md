# find-address
&nbsp;&nbsp;&nbsp;&nbsp;It is a microservice to search for an address given a postal code. It is meant to be a simple way to work with many external providers as it has no database but consumes REST APIs that have the necessary information.</br>
&nbsp;&nbsp;&nbsp;&nbsp;Find-address continuously verifies the best REST provider to use based on the availability and response time. It also has an internal cache instance to avoid unnecessary requests and to achieve better perfomance.
