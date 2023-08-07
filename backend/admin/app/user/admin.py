from django.contrib import admin

from .models import User, Telegram, Team


@admin.register(User)
class UserAdmin(admin.ModelAdmin):
    fields = ("id", "name", "token", "admin", "team")
    readonly_fields = ("id",)

    list_display = ("name", "admin", "team")
    search_fields = ("name", "token")
    list_filter = ("admin", "team")

    ordering = ("name",)


@admin.register(Telegram)
class TelegramAdmin(admin.ModelAdmin):
    list_display = ("user", "admin", "telegram_id", "chat_id")
    search_fields = ("user__name", "telegram_id", "chat_id")
    list_filter = ("user__admin",)

    ordering = ("user__name",)

    @admin.display(description="admin")
    def admin(self, model: Telegram):
        return model.user.admin


@admin.register(Team)
class TeamAdmin(admin.ModelAdmin):
    fields = ("id", "name", "invite")
    readonly_fields = ("id",)

    list_display = ("name", "invite")
    search_fields = ("name",)

    ordering = ("name",)
