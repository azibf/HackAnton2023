import datetime
import icalendar
import urllib.request

last_refreshed_at = None
calendar = None

ttl = datetime.timedelta(minutes=5)


def should_refresh():
    global last_refreshed_at
    if last_refreshed_at is None or calendar is None:
        return True
    return datetime.datetime.now() - last_refreshed_at > ttl


def get_calendar(calendar_url: str):
    global calendar
    if should_refresh():
        calendar = fetch_and_parse_calendar(calendar_url)
    return calendar


def fetch_and_parse_calendar(calendar_url: str):
    with urllib.request.urlopen(calendar_url) as response:
        calendar = response.read()
    return icalendar.Calendar.from_ical(calendar)
