# CURREX - Currency Exchange micro-service

This microservice provides the following API endpoints
which deal with converting foreign currencies to NPL via the
NBP API as it's source of data.

## Get Sell rate for a foreign currency

``GET /sell-rates/{currency}/{date}``

This API takes two positional parameters, currency and date.
The currency has to be a three-character ISO 4217 format code.
The date has to be in "yyyy-mm-dd" format.

### Example cURL command

```bash
curl localhost:8080/sell-rates/usd/2021-11-08
```

## Calculate total purchase amount

``POST /purchase-cost/{date}``

This API takes one positional parameter, date, as well as
request body which has to be in JSON format.
To make this request, `Content-Type` header needs to be set to
`Application/json`. The request body is an array of JSON
objects with attributes
- amount
- currency

The currency has to be a three-character ISO 4217 format code.

### Example cURL command

```bash
curl -XPOST -H "Content-Type: application/json" -d '[{"amount": 10, "currency": "USD"}, {"currency": "INR", "amount": 28}]' localhost:8080/purchase-cost/2021-11-10
```

## Exceptions

This microservice handles one exception - Rate Not Found.
This exception occurs when the rates of one or more foreign currency
cannot be found. The status of this response is `404 NOT FOUND`.

```json
{
  "message": "Could not fetch data for country code asd from 2021-11-10 in table a"
}
```

## Project structure

The project itself is divided into the following parts

Part | description
---|---
advices | The controller advice for when rate not found exception occurs
controllers | The sell rate controller is here
entities | JPA entities to be stored in H2 database
exceptions | Custom exceptions used in this project
nbp | classes that handle calls made to NBP API
pojo | misc POJO classes used throughout the project
repositories | JPA repositories used in this project
