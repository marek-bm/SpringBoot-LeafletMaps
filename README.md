# SpringBoot & LifeletMaps
The application shows Covid19 infections data on 17 Mar 2020.
Infection data is displayed on OpenStreet maps using Lifelet.

1. App sends GET request to data source
2. Response is parsed to the Point model
3. Each country, where virus was detected, has its own makrer with coordianted and number of infections
3. Markers are rendered on the Lifelet maps under "/all" endpoint
 