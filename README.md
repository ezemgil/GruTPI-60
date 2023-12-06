# Integrative Project - Backend Applications 2023

This repository contains the source code for the development of the backend of a bike rental system for a specific city.

## Project Description

The bike rental system operates under certain assumptions and rules, including:

- **Clients:** Only registered clients can rent bikes.
- **Bikes:** Each bike is picked up from one station and returned to another.
- **Availability:** It is assumed that there is always a bike available at each station.
- **Price Calculation:** The rental price is calculated upon bike return based on specific rules, such as fixed costs, hourly costs, kilometer costs, and discounts on promotional days.
- **Currency:** The client chooses the currency in which they want to see the amount owed when returning the bike.

## Features

### Stations

1. **Get Stations:** Retrieve the list of all available stations in the city.
2. **Nearest Station:** Retrieve data for the station nearest to a location provided by the client.
3. **Add Station:** Add a new station to the system.

### Rentals

4. **Start Rental:** Start renting a bike from a specific station.
5. **Finish Rental:** Finish an ongoing rental, providing data for it and the cost expressed in the currency chosen by the client.
6. **Get Rentals:** Retrieve a list of rentals made by applying at least one filter.

## Roles and Authentication

- **Administrator:** Can add new stations and obtain lists of rentals made.
- **Client:** Can make inquiries about stations, rent bikes, and return bikes.

## Service Organization

![Service Organization](images/microservices.png)
![Service Diagram](images/services.png)

## Database

### Entity Diagram

![Entity Diagram](images/bbdd.png)

### Tables

1. **Stations:**
   - `ID`: Station identifier
   - `NAME`: Station name
   - `CREATION_DATE_TIME`: Date and time of station creation
   - `LATITUDE`: Latitude of station location
   - `LONGITUDE`: Longitude of station location

2. **Rates:**
   - `ID`: Rate identifier
   - `RATE_TYPE`: Rate type (1 - Normal, 2 - Discount)
   - `DEFINITION`: Rate definition ('S' - Day of the week, 'C' - Day, month, and year)
   - `DAY_OF_WEEK`: Day of the week for weekly rate
   - `DAY_OF_MONTH`: Day of the month for daily rate
   - `MONTH`: Month for monthly rate
   - `YEAR`: Year for annual rate
   - `FIXED_RENT_AMOUNT`: Fixed amount for starting the rental
   - `MINUTE_FRACTION_AMOUNT`: Amount per fractionated minute
   - `HOUR_AMOUNT`: Amount per complete hour
   - `KM_AMOUNT`: Amount per kilometer between stations

3. **Rentals:**
   - `ID`: Rental identifier
   - `CLIENT_ID`: Client identifier who made the rental
   - `STATUS`: Rental status (1 - Started, 2 - Finished)
   - `PICKUP_STATION`: ID of the station where the bike was picked up
   - `RETURN_STATION`: ID of the station where the bike was returned
   - `PICKUP_DATE_TIME`: Date and time of bike pickup
   - `RETURN_DATE_TIME`: Date and time of bike return
   - `AMOUNT`: Amount charged for the rental
   - `RATE_ID`: ID of the rate used to calculate the rental amount

## Distance Between Stations

The distance between two stations is simply calculated using the Euclidean distance between the two points, where each degree corresponds to 110000 meters.

## Contributors

- Matías Ezequiel Gil
- Alejandro Axel Molina
