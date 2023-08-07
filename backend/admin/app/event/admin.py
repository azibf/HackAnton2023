from django.contrib import admin

from event.models import Event


@admin.register(Event)
class EventAdmin(admin.ModelAdmin):
    fields = ("title", "description", "speaker", "affiliate", "start", "end")
    list_display = ("title", "speaker", "affiliate", "start", "end")
    search_fields = ("title", "description", "speaker", "affiliate")
    list_filter = ("start", "end")

    ordering = ("-start",)
