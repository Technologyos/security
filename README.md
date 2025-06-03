<h1 align="center">Security Initial Project</h1>

### Setting Up

**Important Note:** To start the service, it is necessary to configure the appropriate profile in the `application.yml` file. The profiles are described below:

**Local:** When using this profile, the service will automatically use the H2 database, so it will not be necessary to start the PostgreSQL database container.

**Dev:** If this profile is configured, you will need to follow the steps outlined to create and start the database container.

By default, the profile is **dev**

### Steps to setting up the database

1. **Create the container:** To create the container, run the following command in the terminal: `docker-compose up -d`. Once the creation is complete, go to the following pgAdmin URL in your browser: [http://localhost:8080/login?next=/](http://localhost:8080/login?next=/).

2. **Enter credentials from the .env file:** Before proceeding, you need to enter your credentials from the `.env` file to access pgAdmin. Look for the `PGA_EMAIL` and `PGA_PASSWORD` variables and enter them during login.

3. **Create the server in pgAdmin:**  In the pgAdmin interface, go to the "Servers" section. Right-click to open a menu, then select "Register" and "Server."

4. **Server configuration:** In the server configuration window, fill in the following details:

   #### General
    - **name:** postgres (name of the PostgreSQL container)

   #### Connection
    - **Host name/address:** postgres (name of the PostgreSQL container)
    - **Post:** 5432
    - **Maintenance database:** security_db
    - **username:** technologyos
    - **password:** YXNhbGF6YXJqQGdtYWlsLmNvbToqR

After completing the details, click "Save" to establish the connection.

**Note:** Remember that these details are stored in the `.env`, file, where your credentials were previously configured.

### Security Services

#### Generate a token

```shell
curl --request POST \
--url http://localhost:8081/api/v1/auth/authenticate \
--header 'Content-Type: application/json' \
--data '{
"username": "asalazarj",
"password": "Temporal@32"
}'
```

#### Validate a token

```shell
curl --request GET \
--url 'http://localhost:8081/api/v1/auth/validate-token?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3MzQyNDExMDIsImV4cCI6MTczNDI0MjkwMiwicm9sZSI6IlJPTEVfQURNSU5JU1RSQVRPUiIsIm5hbWUiOiJBcm1hbmRvIFNhbGF6YXIiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUkVBRF9BTExfUFJPRFVDVFMifSx7ImF1dGhvcml0eSI6IlJFQURfT05FX1BST0RVQ1QifSx7ImF1dGhvcml0eSI6IkNSRUFURV9PTkVfUFJPRFVDVCJ9LHsiYXV0aG9yaXR5IjoiVVBEQVRFX09ORV9QUk9EVUNUIn0seyJhdXRob3JpdHkiOiJESVNBQkxFX09ORV9QUk9EVUNUIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9DQVRFR09SSUVTIn0seyJhdXRob3JpdHkiOiJSRUFEX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiVVBEQVRFX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiRElTQUJMRV9PTkVfQ0FURUdPUlkifSx7ImF1dGhvcml0eSI6IlJFQURfTVlfUFJPRklMRSJ9XX0.DmbN2jiOCrosE1cGV9RQ2QmNDTXbF0sNdxBkeIWU2zE' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3MzQyNDExMDIsImV4cCI6MTczNDI0MjkwMiwicm9sZSI6IlJPTEVfQURNSU5JU1RSQVRPUiIsIm5hbWUiOiJBcm1hbmRvIFNhbGF6YXIiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUkVBRF9BTExfUFJPRFVDVFMifSx7ImF1dGhvcml0eSI6IlJFQURfT05FX1BST0RVQ1QifSx7ImF1dGhvcml0eSI6IkNSRUFURV9PTkVfUFJPRFVDVCJ9LHsiYXV0aG9yaXR5IjoiVVBEQVRFX09ORV9QUk9EVUNUIn0seyJhdXRob3JpdHkiOiJESVNBQkxFX09ORV9QUk9EVUNUIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9DQVRFR09SSUVTIn0seyJhdXRob3JpdHkiOiJSRUFEX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiVVBEQVRFX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiRElTQUJMRV9PTkVfQ0FURUdPUlkifSx7ImF1dGhvcml0eSI6IlJFQURfTVlfUFJPRklMRSJ9XX0.DmbN2jiOCrosE1cGV9RQ2QmNDTXbF0sNdxBkeIWU2zE' \
```

#### Get profile info by token

```shell
curl --request GET \
--url http://localhost:8081/api/v1/auth/profile \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3MzQyNDExMDIsImV4cCI6MTczNDI0MjkwMiwicm9sZSI6IlJPTEVfQURNSU5JU1RSQVRPUiIsIm5hbWUiOiJBcm1hbmRvIFNhbGF6YXIiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUkVBRF9BTExfUFJPRFVDVFMifSx7ImF1dGhvcml0eSI6IlJFQURfT05FX1BST0RVQ1QifSx7ImF1dGhvcml0eSI6IkNSRUFURV9PTkVfUFJPRFVDVCJ9LHsiYXV0aG9yaXR5IjoiVVBEQVRFX09ORV9QUk9EVUNUIn0seyJhdXRob3JpdHkiOiJESVNBQkxFX09ORV9QUk9EVUNUIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9DQVRFR09SSUVTIn0seyJhdXRob3JpdHkiOiJSRUFEX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiVVBEQVRFX09ORV9DQVRFR09SWSJ9LHsiYXV0aG9yaXR5IjoiRElTQUJMRV9PTkVfQ0FURUdPUlkifSx7ImF1dGhvcml0eSI6IlJFQURfTVlfUFJPRklMRSJ9XX0.DmbN2jiOCrosE1cGV9RQ2QmNDTXbF0sNdxBkeIWU2zE' \
```

#### Create a permission

```shell
curl --request POST \
  --url http://localhost:9191/api/v1/permissions \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3NDc3NTgwMzgsImV4cCI6MTc0Nzc1OTgzOCwicm9sZSI6IkFETUlOSVNUUkFUT1IiLCJuYW1lIjoiQXJtYW5kbyBTYWxhemFyIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJFQURfQUxMX0NVU1RPTUVSUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9QRVJNSVNTSU9OUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9PTkVfUEVSTUlTU0lPTiJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9QRVJNSVNTSU9OIn0seyJhdXRob3JpdHkiOiJERUxFVEVfT05FX1BFUk1JU1NJT04ifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU5JU1RSQVRPUiJ9XX0.KHA-FGuIcbipnR4ykDN6kk83wnDYnBkafqcUJS_Z2lg' \
  --header 'Content-Type: application/json' \
  --data '{
	"role": "CUSTOMER",
	"operation": "READ_MY_PROFILE"
}'
```

#### Get permissions

```shell
curl --request GET \
  --url http://localhost:9191/api/v1/permissions \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3NDc3NTgwMzgsImV4cCI6MTc0Nzc1OTgzOCwicm9sZSI6IkFETUlOSVNUUkFUT1IiLCJuYW1lIjoiQXJtYW5kbyBTYWxhemFyIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJFQURfQUxMX0NVU1RPTUVSUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9QRVJNSVNTSU9OUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9PTkVfUEVSTUlTU0lPTiJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9QRVJNSVNTSU9OIn0seyJhdXRob3JpdHkiOiJERUxFVEVfT05FX1BFUk1JU1NJT04ifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU5JU1RSQVRPUiJ9XX0.KHA-FGuIcbipnR4ykDN6kk83wnDYnBkafqcUJS_Z2lg' \
```

#### Get permission

```shell
curl --request GET \
  --url http://localhost:9191/api/v1/permissions/1 \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3NDc3NTgwMzgsImV4cCI6MTc0Nzc1OTgzOCwicm9sZSI6IkFETUlOSVNUUkFUT1IiLCJuYW1lIjoiQXJtYW5kbyBTYWxhemFyIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJFQURfQUxMX0NVU1RPTUVSUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9QRVJNSVNTSU9OUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9PTkVfUEVSTUlTU0lPTiJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9QRVJNSVNTSU9OIn0seyJhdXRob3JpdHkiOiJERUxFVEVfT05FX1BFUk1JU1NJT04ifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU5JU1RSQVRPUiJ9XX0.KHA-FGuIcbipnR4ykDN6kk83wnDYnBkafqcUJS_Z2lg' \
```

#### Delete a permission

```shell
curl --request DELETE \
  --url http://localhost:9191/api/v1/permissions/8 \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3NDc3NTgwMzgsImV4cCI6MTc0Nzc1OTgzOCwicm9sZSI6IkFETUlOSVNUUkFUT1IiLCJuYW1lIjoiQXJtYW5kbyBTYWxhemFyIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJFQURfQUxMX0NVU1RPTUVSUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9QRVJNSVNTSU9OUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9PTkVfUEVSTUlTU0lPTiJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9QRVJNSVNTSU9OIn0seyJhdXRob3JpdHkiOiJERUxFVEVfT05FX1BFUk1JU1NJT04ifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU5JU1RSQVRPUiJ9XX0.KHA-FGuIcbipnR4ykDN6kk83wnDYnBkafqcUJS_Z2lg' \
```

#### Create a customer

```shell
curl --request POST \
--url http://localhost:8081/api/v1/customers \
--header 'Content-Type: application/json' \
--data '{
"name": "Armando Salazar",
"username": "asalazarj",
"password": "Temporal@32",
"repeatedPassword":"Temporal@32"
}'
```

#### get all customers

```shell
curl --request GET \
  --url http://localhost:9191/api/v1/customers \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3NDc2OTA3ODAsImV4cCI6MTc0NzY5MjU4MCwicm9sZSI6IkFETUlOSVNUUkFUT1IiLCJuYW1lIjoiQXJtYW5kbyBTYWxhemFyIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJFQURfQUxMX0NVU1RPTUVSUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9NWV9QUk9GSUxFIn0seyJhdXRob3JpdHkiOiJSRUFEX0FMTF9QRVJNSVNTSU9OUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9PTkVfUEVSTUlTU0lPTiJ9LHsiYXV0aG9yaXR5IjoiQ1JFQVRFX09ORV9QRVJNSVNTSU9OIn0seyJhdXRob3JpdHkiOiJERUxFVEVfT05FX1BFUk1JU1NJT04ifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU5JU1RSQVRPUiJ9XX0.t6BTa4D9gqfGnjq30xJxwPzNDX759I_rgx5Je9_ykDs' \
```

#### logout service

```shell
curl --request POST \
--url http://localhost:8081/api/v1/auth/logout \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2FsYXphcmoiLCJpYXQiOjE3MzUwNjM4NzIsImV4cCI6MTczNTA2NTY3Miwicm9sZSI6IkFETUlOSVNUUkFUT1IiLCJuYW1lIjoiQXJtYW5kbyBTYWxhemFyIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJFQURfQUxMX1BST0RVQ1RTIn0seyJhdXRob3JpdHkiOiJSRUFEX09ORV9QUk9EVUNUIn0seyJhdXRob3JpdHkiOiJDUkVBVEVfT05FX1BST0RVQ1QifSx7ImF1dGhvcml0eSI6IlVQREFURV9PTkVfUFJPRFVDVCJ9LHsiYXV0aG9yaXR5IjoiRElTQUJMRV9PTkVfUFJPRFVDVCJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9BTExfQ0FURUdPUklFUyJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9PTkVfQ0FURUdPUlkifSx7ImF1dGhvcml0eSI6IkNSRUFURV9PTkVfQ0FURUdPUlkifSx7ImF1dGhvcml0eSI6IlVQREFURV9PTkVfQ0FURUdPUlkifSx7ImF1dGhvcml0eSI6IkRJU0FCTEVfT05FX0NBVEVHT1JZIn0seyJhdXRob3JpdHkiOiJSRUFEX01ZX1BST0ZJTEUifSx7ImF1dGhvcml0eSI6IkxPR09VVCJ9LHsiYXV0aG9yaXR5IjoiUkVBRF9BTExfUEVSTUlTU0lPTlMifSx7ImF1dGhvcml0eSI6IlJFQURfT05FX1BFUk1JU1NJT04ifSx7ImF1dGhvcml0eSI6IkNSRUFURV9PTkVfUEVSTUlTU0lPTiJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9BRE1JTklTVFJBVE9SIn1dfQ.R_KEFG13HA8JfZc3mbUXtWryXtBAScwGLvLHsgA-AZM' \
--header 'Content-Type: application/json' \
--data '{
"username": "asalazarj",
"password": "Temporal@32"
}'
```
