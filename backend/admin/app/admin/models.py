import uuid

from django.db import models
from django_mysql.models import Model


class BaseModel(Model):
    id = models.CharField(max_length=36, primary_key=True, default=uuid.uuid4, editable=False)
    created_at = models.DateTimeField(auto_now_add=True, editable=False)

    class Meta:
        abstract = True
