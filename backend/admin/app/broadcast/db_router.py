from broadcast.models import Broadcast


class DBRouter:
    models = (Broadcast,)

    def db_for_read(self, model, **hints):
        if model in self.models:
            return "broadcast"
        return None

    def db_for_write(self, model, **hints):
        if model in self.models:
            return "broadcast"
        return None
