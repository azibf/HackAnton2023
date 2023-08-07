import uuid

from admin.models import BaseModel
from django.db import models
from django_mysql.models import Bit1BooleanField
from user.constants import UserConstant


class User(BaseModel):
    name = models.CharField(max_length=UserConstant.NAME_LENGTH, unique=True, null=False)
    token = models.CharField(max_length=UserConstant.TOKEN_LENGTH, unique=True, null=False, db_index=True)
    admin = Bit1BooleanField(default=False, null=False)

    team = models.ForeignKey("Team", on_delete=models.SET_NULL, null=True, blank=True)

    def __str__(self) -> str:
        return str(self.name)

    class Meta:
        db_table = "user"


class Telegram(BaseModel):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="telegrams")
    telegram_id = models.CharField(max_length=UserConstant.TELEGRAM_ID_LENGTH, null=False)
    chat_id = models.CharField(max_length=UserConstant.TELEGRAM_ID_LENGTH, unique=True, null=False)

    def __str__(self) -> str:
        return f"{self.user.name} [{self.chat_id}]"

    class Meta:
        db_table = "telegram"


class Team(BaseModel):
    name = models.CharField(max_length=UserConstant.NAME_LENGTH, unique=True, null=False)
    invite = models.CharField(max_length=40, default=uuid.uuid4, null=False)

    def __str__(self) -> str:
        return str(self.name)

    class Meta:
        db_table = "team"
