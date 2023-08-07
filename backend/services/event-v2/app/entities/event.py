from typing import Optional
from dataclasses import dataclass
import datetime


@dataclass
class Event:
    title: str
    description: Optional[str]

    speaker: Optional[str]
    affiliate: Optional[str]

    start: datetime.datetime
    end: datetime.datetime
