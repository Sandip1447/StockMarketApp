# StockMarketApp

Build a simple stock market app with the clean architectural guidelines.

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
![Screenshot_20221104_123443](https://user-images.githubusercontent.com/16761010/199915558-9aef188b-5a0b-4eea-b1c4-1d6155f8f0ae.png)
![Screenshot_20221104_123507](https://user-images.githubusercontent.com/16761010/199915567-eae905fb-cbd7-4f04-960a-d281e63a5a3e.png)

