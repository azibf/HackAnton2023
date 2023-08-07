from admin.models import BaseModel
from django.db import models
from event.constants import EventConstant


class Event(BaseModel):
    title = models.CharField(max_length=EventConstant.TITLE_LENGTH, null=False)
    description = models.TextField(max_length=EventConstant.DESCRIPTION_LENGTH, null=True, blank=True)

    speaker = models.CharField(max_length=EventConstant.NAME_LENGTH, null=True, blank=True)
    affiliate = models.CharField(max_length=EventConstant.NAME_LENGTH, null=True, blank=True)

    start = models.DateTimeField(null=False)
    end = models.DateTimeField(null=False)

    def __str__(self) -> str:
        return str(self.title)

    class Meta:
        db_table = "event"
