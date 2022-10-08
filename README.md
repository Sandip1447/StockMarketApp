# StockMarketApp

Build a simple stock market app with the clean architectural guidelines. learn to use dependency injection with Dagger-Hilt, SOLID principles, CSV parsing with OpenCSV, working with remote APIs using Retrofit, local caching with Room, custom drawing on a canvas using Compose.

## Learn to use

- Dependency injection with Dagger-Hilt
- SOLID principles
- CSV parsing with OpenCSV
- Working with remote APIs using Retrofit
- Local caching with Room
- Custom drawing on a canvas using Compose


## API Reference

Claim your Free API Key
- https://www.alphavantage.co/support/#api-key 


#### Get Listing & Delisting Status

```http
  GET /query?function=LISTING_STATUS
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get Company Overview

```http
  GET /query?function=OVERVIEW
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `api_key` | `string` | **Required**. Your API key        |
| `symbol`  | `string` | **Required**. For fetch details   |

### Screenshot
![Screenshot_20221008_185519](https://user-images.githubusercontent.com/16761010/194709852-e6b3d157-4b9c-4d2a-93ef-2f90cd117c26.png)

