from event.models import Event


class DBRouter:
    models = (Event,)

    def db_for_read(self, model, **hints):
        if model in self.models:
            return "event"
        return None

    def db_for_write(self, model, **hints):
        if model in self.models:
            return "event"
        return None
