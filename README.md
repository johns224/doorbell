# doorbell
Lightweight webserver microservice that plays a doorbell sound when the /doorbell URI is hit.

I initially started this small project using Spring Boot, but the computer I needed to run it on was to old to run it.  
So I ended up writing something that will run on anything since Java 1.6.

By default, will play a doorbell sound whenever http://localhost:9000/doorbell is hit.  
