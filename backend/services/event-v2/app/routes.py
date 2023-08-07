from pytz import timezone
from fastapi import APIRouter
from itertools import chain
import os
import datetime, recurring_ical_events

from .entities.event import Event
from .controller.calendar import get_calendar

router = APIRouter()


CALENDAR_URL = os.environ["EVENT_CAL_URL"]
TIMEZONE = os.environ["TIMEZONE"] if "TIMEZONE" in os.environ else "Europe/Moscow"


@router.get("/event")
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
    return {
        "data": {
            "content": list(
                map(
                    Event.from_icalendar, list(chain(all_day_events, fixed_time_events))
                )
            )
        }
    }


@router.get("/event/{event_id}")
def get_event():
    raise NotImplementedError


@router.post("/event")
def create_event():
    raise NotImplementedError


@router.post("/event/{event_id}")
def update_event():
    raise NotImplementedError


@router.delete("/event/{event_id}")
def delete_event():
    raise NotImplementedError
