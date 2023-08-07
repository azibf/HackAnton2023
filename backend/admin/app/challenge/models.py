from admin.models import BaseModel
from challenge.constants import ChallengeConstant
from django.db import models
from django_mysql.models import Bit1BooleanField


class AbstractChallenge(BaseModel):
    name = models.CharField(max_length=ChallengeConstant.NAME_LENGTH, unique=True, null=False)
    description = models.TextField(max_length=ChallengeConstant.DESCRIPTION_LENGTH, null=True, blank=True)
    weight = models.FloatField(null=False)
    team = Bit1BooleanField(null=False)
    visible = Bit1BooleanField(null=False)
    start = models.DateTimeField(null=True, blank=True)
    end = models.DateTimeField(null=True, blank=True)

    class Meta:
        abstract = True

    def __str__(self) -> str:
        return str(self.name)


class AbstractSubmit(BaseModel):
    user_id = models.CharField(null=False, max_length=36)
    team_id = models.CharField(null=True, max_length=36, blank=True)

    class Meta:
        abstract = True


class Challenge(AbstractChallenge):
    flag = models.CharField(max_length=ChallengeConstant.FLAG_LENGTH, null=True, blank=True)

    class Meta:
        db_table = "challenge"


class Award(AbstractChallenge):
    class Meta:
        db_table = "award"


class ChallengeSubmit(AbstractSubmit):
    challenge = models.ForeignKey(Challenge, on_delete=models.CASCADE, related_name="submits")

    def __str__(self) -> str:
        return str(self.challenge.name)

    class Meta:
        db_table = "challenge_submit"


class AwardSubmit(AbstractSubmit):
    award = models.ForeignKey(Award, on_delete=models.CASCADE, related_name="submits")

    def __str__(self) -> str:
        return str(self.award.name)

    class Meta:
        db_table = "award_submit"
