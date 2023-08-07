from admin.models import BaseModel
from broadcast.constants import BroadcastConstant
from django.db import models


class Broadcast(BaseModel):
    title = models.CharField(max_length=BroadcastConstant.TITLE_LENGTH, null=False)
    description = models.TextField(max_length=BroadcastConstant.DESCRIPTION_LENGTH, null=True, blank=True)
    start = models.DateTimeField(null=False)

    def __str__(self) -> str:
        return str(self.title)

    class Meta:
        db_table = "broadcast"
