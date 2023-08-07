from typing import Union, Optional
from dataclasses import dataclass
import datetime


@dataclass
class Event(dict):
    id: str
    title: str
    description: Optional[str]

    speaker: Optional[str]
    affiliate: Optional[str]

    start: str
    end: str

    @staticmethod
    def from_icalendar(event):
        start: Union[datetime.datetime, datetime.date] = event["DTSTART"].dt
        end: Union[datetime.datetime, datetime.date] = event["DTEND"].dt
        info = list(
            map(lambda s: s.strip(), str(event.get("LOCATION")).split(","))
            if event.get("LOCATION")
            else []
        )
        return Event(
            id=str(event.get("UID")),
            title=str(event.get("SUMMARY")),
            description=str(event.get("DESCRIPTION"))
            if event.get("DESCRIPTION")
            else None,
            speaker=info[0] if len(info) > 0 else None,
            affiliate=info[1] if len(info) > 1 else None,
            start=str(start),
            end=str(end),
        )
