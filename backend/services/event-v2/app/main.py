from fastapi import FastAPI
from contextlib import asynccontextmanager
import os
import py_eureka_client.eureka_client as eureka_client

from .routes import router
from .controller.calendar import get_calendar


@asynccontextmanager
async def lifespan(_: FastAPI):
    client = None
    EUREKA_SERVER = (
        os.environ["EUREKA_SERVER"] if "EUREKA_SERVER" in os.environ else None
    )
    if EUREKA_SERVER:
        client = eureka_client.EurekaClient(
            eureka_server=EUREKA_SERVER,
            app_name=os.environ["APP_NAME"] if "APP_NAME" in os.environ else "event-v2",
            instance_port=8080,
        )
        await client.start()
    yield
    if client is not None:
        await client.stop()


app = FastAPI(lifespan=lifespan)
app.include_router(router, prefix="/api/v1")

CALENDAR_URL = os.environ["EVENT_CAL_URL"]


@app.get("/health")
def health():
    return {"status": "UP"}


# Invalidates the cache for a calendar
@app.post("/calendar/invalidate")
def invalidate():
    get_calendar(CALENDAR_URL, force_refresh=True)
    return {"success": "OK"}
