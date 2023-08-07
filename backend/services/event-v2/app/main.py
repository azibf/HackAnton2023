from pytz import timezone
from fastapi import FastAPI
from itertools import chain
import os
import datetime, recurring_ical_events
import py_eureka_client.eureka_client as eureka_client

from .entities.event import Event
from .controller.calendar import get_calendar

EUREKA_SERVER = os.environ["EUREKA_SERVER"] if "EUREKA_SERVER" in os.environ else None
if EUREKA_SERVER:
    eureka_client.init(
        eureka_server=EUREKA_SERVER,
        app_name=os.environ["APP_NAME"] if "APP_NAME" in os.environ else "event-v2",
        instance_port=8080,
    )
app = FastAPI()

CALENDAR_URL = os.environ["EVENT_CAL_URL"]
TIMEZONE = os.environ["TIMEZONE"] if "TIMEZONE" in os.environ else "Europe/Moscow"


# Invalidates the cache for a calendar
@app.post("/calendar/invalidate")
def invalidate():
    get_calendar(CALENDAR_URL, force_refresh=True)
    return {"success": "OK"}


@app.get("/health")
def health():
    return {"status": "UP"}


@app.get("/event")
def list_events():
    calendar = get_calendar(CALENDAR_URL)
    events = recurring_ical_events.of(calendar).between(
        datetime.datetime.combine(
            datetime.date.today(), datetime.time(0, 0), tzinfo=timezone(TIMEZONE)
        ),
        datetime.datetime.combine(
            datetime.date.today(), datetime.time(23, 59), tzinfo=timezone(TIMEZONE)
        ),
    )
    all_day_events = filter(
        lambda event: not isinstance(event.get("DTSTART").dt, datetime.datetime), events
    )
    fixed_time_events = sorted(
        filter(
            lambda event: isinstance(event.get("DTSTART").dt, datetime.datetime), events
        ),
        key=lambda event: event.get("DTSTART").dt,
    )
    return list(
        map(Event.from_icalendar, list(chain(all_day_events, fixed_time_events)))
    )


@app.get("/event/{event_id}")
def get_event():
    raise NotImplementedError


@app.post("/event")
def create_event():
    raise NotImplementedError


@app.post("/event/{event_id}")
def update_event():
    raise NotImplementedError


@app.delete("/event/{event_id}")
def delete_event():
    raise NotImplementedError
