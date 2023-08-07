from pytz import timezone
from icalendar import Calendar
import datetime
import recurring_ical_events


def today_tz(tzname: str):
    return datetime.datetime.now(timezone(tzname)).date()


def get_events_for_today(calendar: Calendar, tzname: str):
    today = today_tz(tzname)
    events = recurring_ical_events.of(calendar).between(
        datetime.datetime.combine(today, datetime.time(0, 0), tzinfo=timezone(tzname)),
        datetime.datetime.combine(
            today, datetime.time(23, 59), tzinfo=timezone(tzname)
        ),
    )
    return sorted(
        # Filter out all-day events, since TG bot always tries to show time for events
        filter(
            lambda event: isinstance(event.get("DTSTART").dt, datetime.datetime), events
        ),
        key=lambda event: event.get("DTSTART").dt,
    )
