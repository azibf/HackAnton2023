from challenge.models import (
    AbstractSubmit,
    Challenge,
    ChallengeSubmit,
    Award,
    AwardSubmit,
)
from django.contrib import admin

from user.models import User, Team


class AbstractChallengeAdmin(admin.ModelAdmin):
    fields = (
        "id",
        "created_at",
        "name",
        "description",
        "weight",
        "team",
        "visible",
        "start",
        "end",
    )
    readonly_fields = (
        "id",
        "created_at",
    )

    list_display = ("name", "weight", "start", "end", "team", "visible")
    search_fields = ("name", "description")
    list_filter = ("team", "visible", "start", "end")

    ordering = ("-start", "end", "name")

    actions = ("set_visible", "set_invisible")

    @admin.action(description="Show selected challenges")
    def set_visible(self, request, queryset):
        queryset.update(visible=True)

    @admin.action(description="Hide selected challenges")
    def set_invisible(self, request, queryset):
        queryset.update(visible=False)


class AbstractSubmitAdmin(admin.ModelAdmin):
    fields = ("created_at", "user_id", "user", "team_id", "team")
    readonly_fields = ("created_at", "user", "team")

    list_display = ("user", "team", "created_at")
    list_filter = ("created_at", "user_id", "team_id")

    ordering = ("-created_at",)

    @admin.display(description="User")
    def user(self, model: AbstractSubmit):
        return User.objects.get(pk=model.user_id)

    @admin.display(description="Team")
    def team(self, model: AbstractSubmit):
        return Team.objects.get(pk=model.team_id)


@admin.register(Challenge)
class ChallengeAdmin(AbstractChallengeAdmin):
    fields = (*AbstractChallengeAdmin.fields, "flag")


@admin.register(Award)
class AwardAdmin(AbstractChallengeAdmin):
    pass


@admin.register(ChallengeSubmit)
class ChallengeSubmitAdmin(AbstractSubmitAdmin):
    fields = (*AbstractSubmitAdmin.fields, "challenge", "challenge_weight")
    readonly_fields = (*AbstractSubmitAdmin.readonly_fields, "challenge_weight")

    list_display = (*AbstractSubmitAdmin.list_display, "challenge")
    search_fields = (
        *AbstractSubmitAdmin.search_fields,
        "challenge__name",
        "challenge__flag",
    )
    list_filter = (*AbstractSubmitAdmin.list_filter, "challenge__team")

    @admin.display(description="Weight")
    def challenge_weight(self, model: AbstractSubmit):
        return model.challenge.weight


@admin.register(AwardSubmit)
class AwardSubmitAdmin(AbstractSubmitAdmin):
    fields = (*AbstractSubmitAdmin.fields, "award", "award_weight")
    readonly_fields = (*AbstractSubmitAdmin.readonly_fields, "award_weight")

    list_display = (*AbstractSubmitAdmin.list_display, "award")
    search_fields = (*AbstractSubmitAdmin.search_fields, "award__name")
    list_filter = (*AbstractSubmitAdmin.list_filter, "award__team")

    @admin.display(description="Weight")
    def award_weight(self, model: AbstractSubmit):
        return model.award.weight
