from broadcast.models import Broadcast
from django.contrib import admin


@admin.register(Broadcast)
class BroadcastAdmin(admin.ModelAdmin):
    list_display = ("title", "description", "start")
    search_fields = ("title", "description")
    list_filter = ("start",)

    ordering = ("-start",)
