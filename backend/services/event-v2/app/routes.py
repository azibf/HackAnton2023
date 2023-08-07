from fastapi import APIRouter
import os

from .entities.event import Event
from .controller.events import get_events_for_today
from .controller.calendar import get_calendar

router = APIRouter()


CALENDAR_URL = os.environ["EVENT_CAL_URL"]
TIMEZONE = os.environ["TIMEZONE"] if "TIMEZONE" in os.environ else "Europe/Moscow"


@router.get("/event")
def list_events():
    calendar = get_calendar(CALENDAR_URL)
    events = get_events_for_today(calendar, TIMEZONE)
    return {
        "data": {
            "content": list(
                map(
                    Event.from_icalendar,
                    sorted(
                        events, key=lambda event: event.get("DTSTART").dt, reverse=True
                    ),
                )
            )
        }
    }


@router.get("/event/{event_id}")
def get_event(event_id: str):
    calendar = get_calendar(CALENDAR_URL)
    events = get_events_for_today(calendar, TIMEZONE)
    event = next(filter(lambda event: event.get("UID") == event_id, events), None)
    return {"data": Event.from_icalendar(event)}


@router.post("/event")
def create_event():
    raise NotImplementedError


@router.post("/event/{event_id}")
def update_event():
    raise NotImplementedError


@router.delete("/event/{event_id}")
def delete_event():
    raise NotImplementedError
