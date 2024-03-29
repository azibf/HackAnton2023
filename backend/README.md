# CTF Platform Backend

## Quickstart

### Requirements

-   JDK 17
-   Docker
-   Docker Compose

### Documentation

Import `postman.json` file into Postman to get the current api

### Configuration

Copy env file and replace variables with your preferred values:

```bash
cp deploy/config/.env.sample deploy/config/.env
```

### Run in production environment

Build docker images:

```bash
cd services && ./gradlew jibDockerBuild
```

Run images:

```bash
cd deploy && docker-compose up -d
```

## Notes

-   When registering a new user for Telegram, the user must send a token to the bot to link an account. The token is provided from an ID card.
